package com.HyperSync.hypersync.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.R;
import com.HyperSync.hypersync.SignInActivity;
import com.HyperSync.hypersync.databinding.FragmentProfileBinding;
import com.HyperSync.hypersync.ui.adminTool.AdminTool;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    TextView textView,Name,email,contact,id,organisation;
    Button btn;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Name = binding.textView10;
        email = binding.profileEmail;
        contact = binding.profileContactNumber;
        id = binding.id;
        organisation = binding.profileOrganization;
        textView = binding.adminTool;
        btn = binding.button;


        SharedPreferences sh = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh.getString("object", "");
        Employee empl = gson.fromJson(json, Employee.class);

        Name.setText("Name :"+empl.getUserName());
        email.setText("Email :"+empl.getEmail());
        contact.setText("Phone :"+empl.getPhone());
        id.setText("ID :"+empl.getId());
        organisation.setText("Organisation :"+empl.getCompany());

        if (empl.getWorker().equals("admin")) {
            textView.setText("AdminTool");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdminTool.class);
                    startActivity(intent);
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sh = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sh.edit();
                ed.clear();
                ed.apply();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });

        return root;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}