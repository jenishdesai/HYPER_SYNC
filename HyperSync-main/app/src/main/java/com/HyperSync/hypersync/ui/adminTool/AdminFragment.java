package com.HyperSync.hypersync.ui.adminTool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.HyperSync.hypersync.Constants.Category;
import com.HyperSync.hypersync.Constants.DatabaseConstants;
import com.HyperSync.hypersync.Employee;
import com.HyperSync.hypersync.model.Worker;
import com.HyperSync.hypersync.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {


    private Button addAnAdminBtn;
    RecyclerView recyclerView;
    private FirebaseDatabase db;
    private AdminRVAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 3. create an adapter
        mAdapter = new AdminRVAdapter(getContext(), new ArrayList<>());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        addAnAdminBtn = view.findViewById(R.id.btnAdmin);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addAnAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddEmptyDialogFragment fragment = new AddEmptyDialogFragment();
                Bundle args = new Bundle();
                args.putString(Category.KEY, Category.ADMIN);
                fragment.setArguments(args);
                fragment.show(getChildFragmentManager(), "dialog");

            }
        });
        getAdminData();
    }

    private void getAdminData() {
        FirebaseDatabase.getInstance().getReference("Emails").orderByChild("worker").equalTo("admin")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Worker> admins = new ArrayList<>();
                        for (DataSnapshot object : snapshot.getChildren()) {
                            admins.add(object.getValue(Worker.class));
                        }
                        System.out.println("Size: "+admins.size());
                        mAdapter.updateData(admins);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}

