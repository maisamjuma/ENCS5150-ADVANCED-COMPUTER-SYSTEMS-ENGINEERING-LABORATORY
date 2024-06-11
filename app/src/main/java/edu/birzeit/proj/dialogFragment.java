package edu.birzeit.proj;

import android.content.Intent;
import android.os.Bundle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class dialogFragment extends DialogFragment {
    SharedPrefManager sharedPrefManager_for_extra;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate custom layout for the dialog
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.activity_dialog_fragment, null);
        builder.setView(rootView);
        sharedPrefManager_for_extra=SharedPrefManager.getInstance(getActivity());

        // Find buttons within the inflated view
        Button buttonOK = rootView.findViewById(R.id.ok);
        Button buttonCancel = rootView.findViewById(R.id.cancel);
        CheckBox checkBox= rootView.findViewById(R.id.checkBox);
        CheckBox checkBox2= rootView.findViewById(R.id.checkBox2);
        CheckBox checkBox3= rootView.findViewById(R.id.checkBox3);
        List<String> clickedCheckBoxTexts = new ArrayList<>();

        // Set click listeners for the buttons
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the presence of the text of checkBox1 in the list
                String checkBox1Text = checkBox.getText().toString();
                if (clickedCheckBoxTexts.contains(checkBox1Text)) {
                    clickedCheckBoxTexts.remove(checkBox1Text);
                } else {
                    clickedCheckBoxTexts.add(checkBox1Text);
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the presence of the text of checkBox2 in the list
                String checkBox2Text = checkBox2.getText().toString();
                if (clickedCheckBoxTexts.contains(checkBox2Text)) {
                    clickedCheckBoxTexts.remove(checkBox2Text);
                } else {
                    clickedCheckBoxTexts.add(checkBox2Text);
                }
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the presence of the text of checkBox3 in the list
                String checkBox3Text = checkBox3.getText().toString();
                if (clickedCheckBoxTexts.contains(checkBox3Text)) {
                    clickedCheckBoxTexts.remove(checkBox3Text);
                } else {
                    clickedCheckBoxTexts.add(checkBox3Text);
                }
            }
        });

// Set click listener for the OK button
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String text : clickedCheckBoxTexts) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(text);
                }
                String clickedCheckBoxTextsString = stringBuilder.toString();
                sharedPrefManager_for_extra.writeString("extra", clickedCheckBoxTextsString);

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