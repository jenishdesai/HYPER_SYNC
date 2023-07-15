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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFragment extends Fragment {



    private Button addAnEmployeeBtn;
    RecyclerView recyclerView;
    private EmployeeRVAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 3. create an adapter
        mAdapter = new EmployeeRVAdapter(getContext(),new ArrayList<>());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        addAnEmployeeBtn = view.findViewById(R.id.btnEmployee);

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addAnEmployeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEmptyDialogFragment fragment = new AddEmptyDialogFragment();
                Bundle args = new Bundle();
                args.putString(Category.KEY, Category.EMPLOYEE);
                fragment.setArguments(args);
                fragment.show(getChildFragmentManager(), "dialog");
            }
        });
        getEmployeeData();
    }


    private void getEmployeeData() {
        FirebaseDatabase.getInstance().getReference("Emails").orderByChild("worker").equalTo("employee")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Worker> Employees = new ArrayList<>();
                        for (DataSnapshot object : snapshot.getChildren()) {
                            Employees.add(object.getValue(Worker.class));
                        }
                        mAdapter.updateData(Employees);
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


}