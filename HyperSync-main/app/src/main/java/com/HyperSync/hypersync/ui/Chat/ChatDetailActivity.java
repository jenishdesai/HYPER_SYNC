package com.HyperSync.hypersync.ui.Chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.R;
import com.HyperSync.hypersync.ui.Chat.Adapter.ChatAdapter;
import com.HyperSync.hypersync.ui.Chat.Models.MessageModel;
import com.HyperSync.hypersync.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String receiveId = getIntent().getStringExtra("userId");
        String Username = getIntent().getStringExtra("Username");
        String profilepic = getIntent().getStringExtra("profilepic");

        SharedPreferences sh = getSharedPreferences("Data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh.getString("object", "");
        Employee empl = gson.fromJson(json, Employee.class);

        binding.Username.setText(Username);
        Picasso.get().load(profilepic).placeholder(R.drawable.profile).into(binding.profilepic);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessageModel> messageModels = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(messageModels,this);
        binding.chattingRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chattingRecyclerView.setLayoutManager(layoutManager);

        final String senderRoom = senderId + receiveId;
        final String receiverRoom = receiveId + senderId;

        database.getReference("Data").child(empl.getCompany()).child("Chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    MessageModel model = snapshot1.getValue(MessageModel.class);
                    messageModels.add(model);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String message =  binding.etMessage.getText().toString();
              final MessageModel model = new MessageModel(senderId,message);
              model.setTimestamp(new Date().getTime());
              binding.etMessage.setText("");

                database.getReference("Data").child(empl.getCompany()).child("Chats").child(senderRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      database.getReference("Data").child(empl.getCompany()).child("Chats").child(receiverRoom).push().setValue(model);
                  }
              });
            }
        });
    }
}