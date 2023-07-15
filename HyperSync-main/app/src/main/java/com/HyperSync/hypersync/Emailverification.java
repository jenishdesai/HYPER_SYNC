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
import com.google.firebase.auth.FirebaseUser;

public class Emailverification extends AppCompatActivity {
    Button proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        proceed = findViewById(R.id.proceed);

        String email = getIntent().getStringExtra("email");
        String uID = getIntent().getStringExtra("uID");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mauth = FirebaseAuth.getInstance();
                FirebaseUser user = mauth.getCurrentUser();
                user.reload();
                if(user.isEmailVerified()) {
                    Intent intent = new Intent(Emailverification.this, DetailActivity.class);
                    intent.putExtra("email",email);
                    intent.putExtra("uID",uID);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "First, verify your mail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}