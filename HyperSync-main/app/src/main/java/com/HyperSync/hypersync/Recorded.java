package com.HyperSync.hypersync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Recorded extends AppCompatActivity {

    TextView gotoSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gotoSignIn = findViewById(R.id.gotoSignIn);

        gotoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recorded.this,SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}