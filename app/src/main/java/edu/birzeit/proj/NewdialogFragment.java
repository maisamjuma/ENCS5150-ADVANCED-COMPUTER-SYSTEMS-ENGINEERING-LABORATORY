package edu.birzeit.proj;

import android.os.Bundle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewdialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate custom layout for the dialog
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.activity_newdialog_fragment, null);
        builder.setView(rootView);

        // Find buttons within the inflated view
        Button buttonOK = rootView.findViewById(R.id.ok);
        Button buttonCancel = rootView.findViewById(R.id.cancel);

        // Set click listeners for the buttons
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle OK button click
                dismiss(); // Close the dialog
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Cancel button click
                dismiss(); // Close the dialog
            }
        });

        return builder.create();
    }
}
