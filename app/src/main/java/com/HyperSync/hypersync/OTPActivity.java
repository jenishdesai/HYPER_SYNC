package com.HyperSync.hypersync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class OTPActivity extends AppCompatActivity {

    Button proceed;
    TextView Otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String OTP = "1234";

        proceed = findViewById(R.id.proceed);
        Otp = findViewById(R.id.etOTP);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = Otp.getText().toString();

                if(otp.equals(OTP)) {

                    String email = getIntent().getStringExtra("email");
                    String password = getIntent().getStringExtra("password");
                    String company = getIntent().getStringExtra("company");

                    FirebaseAuth mauth = FirebaseAuth.getInstance();

                    mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(OTPActivity.this, DetailActivity.class);

                                intent.putExtra("email", email);
                                intent.putExtra("uID", mauth.getCurrentUser().getUid());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OTPActivity.this, SignUpActivity.class);
                            }
                        }

                    });
                }
                else
                    Toast.makeText(getApplicationContext(), "Incorrect OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}