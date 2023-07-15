package com.HyperSync.hypersync;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.HyperSync.hypersync.model.Worker;
import com.HyperSync.hypersync.ui.Chat.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.HyperSync.hypersync.databinding.ActivityHomePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HomePage extends AppCompatActivity {

    private ActivityHomePageBinding binding;
    private FirebaseDatabase db ;
    ImageView chat_button;
    FirebaseAuth mauth;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_project, R.id.navigation_attendance,R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        String email = getIntent().getStringExtra("email");
        Connect(email);

        chat_button = findViewById(R.id.chat_page);

        chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Connect(String email){

        String b64email = Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
        String uID = getIntent().getStringExtra("uID");

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = db.getReference("Emails").child(b64email);

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Worker worker2 = snapshot.getValue(Worker.class);

                String company = worker2.getCompany();

                DatabaseReference ref = db.getReference("Data").child(company).child("Employee").child(uID);


                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Employee empl = snapshot.getValue(Employee.class);

                        SharedPreferences sh = getSharedPreferences("Data",MODE_PRIVATE);
                        SharedPreferences.Editor ed= sh.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(empl);
                        ed.putString("object",json);
                        ed.apply();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}