package com.HyperSync.hypersync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GettingStarted extends AppCompatActivity {

    Button getStarted;
    CheckBox chkbox;
    TextView termsConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        getStarted = findViewById(R.id.GetStarted);
        termsConditions = findViewById(R.id.termsConditions);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkbox = findViewById(R.id.checkBox);
                if(chkbox.isChecked()) {
                    Intent intent = new Intent(GettingStarted.this, SignInActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "First, Accept our Terms and Conditions !", Toast.LENGTH_SHORT).show();

            }
        });

        termsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this,TermsConditions.class);
                startActivity(intent);
            }
        });
    }
}