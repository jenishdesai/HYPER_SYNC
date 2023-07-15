package com.HyperSync.hypersync.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import com.HyperSync.hypersync.model.poll;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class PollDialogFragment extends DialogFragment {

    EditText poll,option1,option2,option3,option4;
    Button btn,btn2;
    FirebaseDatabase db;

    @NonNull
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poll_dialog, container, false);

        poll = view.findViewById(R.id.poll);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);
        btn = view.findViewById(R.id.post);
        btn2 = view.findViewById(R.id.Discard);

        btn.setText("Submit");

        SharedPreferences sh = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh.getString("object", "");
        Employee empl = gson.fromJson(json, Employee.class);

        db = FirebaseDatabase.getInstance();

        db.getReference("Data").child(empl.getCompany()).child("Polls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(empl.getWorker().equals("employee")){
                    poll.setFocusable(false);
                    option1.setFocusable(false);
                    option2.setFocusable(false);
                    option3.setFocusable(false);
                    option4.setFocusable(false);
                }

                poll Poll = snapshot.getValue(poll.class);
                poll.setText(Poll.getText());
                option1.setText(Poll.getOption1());
                option2.setText(Poll.getOption2());
                option3.setText(Poll.getOption3());
                option4.setText(Poll.getOption4());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(empl.getWorker().equals("admin")) {

            btn.setText("Post");
            btn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    String Text = poll.getText().toString();
                    String Option1 = option1.getText().toString();
                    String Option2 = option2.getText().toString();
                    String Option3 = option3.getText().toString();
                    String Option4 = option3.getText().toString();

                    poll Poll = new poll(Text, Option1, Option2, Option3, Option4);

                    db.getReference("Data").child(empl.getCompany()).child("Polls").setValue(Poll);

                    getDialog().dismiss();
                }
            });
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return view;
    }
}
