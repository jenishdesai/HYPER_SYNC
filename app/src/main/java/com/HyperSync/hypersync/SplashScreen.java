package com.HyperSync.hypersync;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td = new Thread(){
            public void run(){
                try {
                    sleep(2600);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent;
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    if(mAuth.getCurrentUser()!=null)
                        intent = new Intent(SplashScreen.this,HomePage.class);
                    else
                        intent = new Intent(SplashScreen.this,GettingStarted.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        td.start();
    }
}