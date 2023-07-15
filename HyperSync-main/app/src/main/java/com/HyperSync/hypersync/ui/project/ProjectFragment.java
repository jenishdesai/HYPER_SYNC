package com.HyperSync.hypersync.ui.project;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.R;
import com.HyperSync.hypersync.model.Worker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends Fragment {

    ProjectRVadapter mAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 3. create an adapter
         mAdapter = new ProjectRVadapter(getContext(),new ArrayList<>());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getProject();

        return view;

    }


    private void getProject() {
        SharedPreferences sh = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh.getString("object", "");
        Employee empl = gson.fromJson(json, Employee.class);

        String uID = FirebaseAuth.getInstance().getUid();

        FirebaseDatabase.getInstance().getReference("Data").child(empl.getCompany()).child("Employee").child(uID).child("Projects")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<projectData> Project = new ArrayList<>();
                        for (DataSnapshot object : snapshot.getChildren()) {
                            Project.add(object.getValue(projectData.class));
                        }
                        mAdapter.updateData(Project);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}