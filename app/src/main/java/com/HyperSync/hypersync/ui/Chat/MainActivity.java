package com.HyperSync.hypersync.ui.Chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.HyperSync.hypersync.databinding.ActivityMainBinding;
import com.HyperSync.hypersync.ui.Chat.Adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);

    }
}