package com.HyperSync.hypersync;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SignUpActivity extends AppCompatActivity {

    Button signUp;
    TextView registerCompany, alreadyRegister;
    EditText Email,Password,RePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUp = findViewById(R.id.btnSignIn);
        registerCompany = findViewById(R.id.tvRegisterCompany);
        alreadyRegister = findViewById(R.id.tvAlreadyRegister);

        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        RePassword = findViewById(R.id.etPasswordAgain);

        signUp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String repassword = RePassword.getText().toString();
                String b64email = Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference("Emails").child(b64email);
                Email email2 = new Email(email,"44444","ABCDE","true");
                ref.setValue(email2);

                if (password.equals(repassword)) {

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Email email1 = snapshot.getValue(Email.class);
                                String company = email1.getCompany();
                                Intent intent = new Intent(SignUpActivity.this,OTPActivity.class);
                                intent.putExtra("email",email);
                                intent.putExtra("password",password);
                                intent.putExtra("company",company);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Your email is not registered with any organisation !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                    Password.setText("");
                    RePassword.setText("");
                }
            }
        });

        registerCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignUpAdminActivity.class);
                startActivity(intent);
            }
        });

        alreadyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}