package com.HyperSync.hypersync.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.widget.EditText;

import com.HyperSync.hypersync.R;

public class HolidayDialogFragment extends DialogFragment {

    EditText editText;
    DatePickerDialog.OnDateSetListener setListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireContext()).setView(R.layout.fragment_holiday_dialog).create();

    }
}