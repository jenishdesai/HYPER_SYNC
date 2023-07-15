package com.HyperSync.hypersync.ui.adminTool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    final private static int NO_OF_ITEMS = 2;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new AdminFragment();
            case 1:
                return new EmployeeFragment();
        }
        return new Fragment();
    }



    @Override
    public int getItemCount() {
        return NO_OF_ITEMS;
    }
}
