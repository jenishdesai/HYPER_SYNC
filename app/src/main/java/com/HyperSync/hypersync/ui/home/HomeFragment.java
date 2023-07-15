package com.HyperSync.hypersync.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.HyperSync.hypersync.Dataadapter;
import com.HyperSync.hypersync.Fragment.AnnouncementDialogFragment;
import com.HyperSync.hypersync.Fragment.PollDialogFragment;
import com.HyperSync.hypersync.Fragment.HolidayDialogFragment;
import com.HyperSync.hypersync.R;
import com.HyperSync.hypersync.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    LinearLayout announcement, poll, declare_holiday;

    String[] city = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m"};
    RecyclerView list;
    Dataadapter dataadapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        announcement = view.findViewById(R.id.llAnnouncement);
        poll = view.findViewById(R.id.llPoll);
        declare_holiday = view.findViewById(R.id.llDeclaration);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnnouncementDialogFragment fragment = new AnnouncementDialogFragment();

                fragment.show(getChildFragmentManager(),"Dialog");

            }
        });

        poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PollDialogFragment fragment = new PollDialogFragment();

                fragment.show(getChildFragmentManager(),"Dialog");

            }
        });

        declare_holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HolidayDialogFragment fragment = new HolidayDialogFragment();

                fragment.show(getChildFragmentManager(),"Dialog");

            }
        });

        list = view.findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(getContext(),1));
       list.setAdapter(new Dataadapter(getActivity(),city));





    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}