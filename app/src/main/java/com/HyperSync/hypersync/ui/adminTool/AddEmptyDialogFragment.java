package com.HyperSync.hypersync.ui.adminTool;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.HyperSync.hypersync.R;

public class AddEmptyDialogFragment extends DialogFragment {




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setView(R.layout.layout_dialog)
//                .setPositiveButton(, (dialog, which) -> {} )
                .create();
    }


}