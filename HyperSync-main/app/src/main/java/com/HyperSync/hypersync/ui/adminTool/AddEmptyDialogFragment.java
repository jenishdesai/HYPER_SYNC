package com.HyperSync.hypersync.ui.adminTool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.HyperSync.hypersync.Constants.Category;
import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.R;
import com.HyperSync.hypersync.model.Worker;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AddEmptyDialogFragment extends DialogFragment {
    String employeeCategory;
    EditText Email, id;
    FirebaseDatabase db;
    Button btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            employeeCategory = args.getString(Category.KEY);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog, container, false);

        Email = view.findViewById(R.id.editText_username);
        id = view.findViewById(R.id.editTextName);
        btn = view.findViewById(R.id.BtnSubmit);


        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String id = AddEmptyDialogFragment.this.id.getText().toString();
                String b64email = Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));

                SharedPreferences sh = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sh.getString("object", "");
                Employee empl = gson.fromJson(json, Employee.class);

                Worker worker3 = new Worker(email, id, empl.getCompany(), employeeCategory);

                db = FirebaseDatabase.getInstance();

                db.getReference("Emails").child(b64email).setValue(worker3);

                getDialog().dismiss();
            }
        });

        return view;
    }

}