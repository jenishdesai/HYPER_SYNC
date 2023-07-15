package com.HyperSync.hypersync;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.HyperSync.hypersync.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DetailActivity extends AppCompatActivity {

    Button forward;
    EditText Firstname,Lastname,Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Firstname = findViewById(R.id.txtFirstName);
        Lastname = findViewById(R.id.txtLastName);
        Phone = findViewById(R.id.etMobileNumber);

        forward = findViewById(R.id.btnContinue);

        forward.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("email");
                String b64email = Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref1 = db.getReference("Emails").child(b64email);
                ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Email email1 = snapshot.getValue(Email.class);
                        String company = email1.getCompany();
                        String id = email1.getId();
                        String Admin = email1.getAdmin();

                        String firstname = Firstname.getText().toString();
                        String lastname = Lastname.getText().toString();
                        String phone = Phone.getText().toString();
                        String uID = getIntent().getStringExtra("uID");

                        FirebaseDatabase hypersync = FirebaseDatabase.getInstance();
                        DatabaseReference ref = hypersync.getReference("Data");

                        Employee empl = new Employee(firstname,lastname,phone,email,id,Admin);
                        ref.child(company).child("Employee").child(uID).setValue(empl);

                        Intent intent = new Intent(DetailActivity.this, HomePage.class);
                        intent.putExtra("uID",uID);
                        startActivity(intent);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}