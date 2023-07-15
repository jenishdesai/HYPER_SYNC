package com.HyperSync.hypersync.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AnnouncementDialogFragment extends DialogFragment {
    private EditText text;
    private Button btn,btn2;

    @NonNull
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcement_dialog, container, false);

        text = view.findViewById(R.id.announcement);
        btn = view.findViewById(R.id.post);
        btn2 = view.findViewById(R.id.Discard);

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String Text = text.getText().toString();
                FirebaseDatabase db;
                db = FirebaseDatabase.getInstance();

                SharedPreferences sh = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sh.getString("object", "");
                Employee empl = gson.fromJson(json, Employee.class);

                db.getReference("Data").child(empl.getCompany()).child("Anonuncements").push().setValue(Text);

                getDialog().dismiss();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return view;
    }
}