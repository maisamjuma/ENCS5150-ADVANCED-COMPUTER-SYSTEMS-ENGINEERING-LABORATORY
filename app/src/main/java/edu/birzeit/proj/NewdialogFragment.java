package edu.birzeit.proj;

import android.database.Cursor;
import android.os.Bundle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewdialogFragment extends DialogFragment {
    SharedPrefManager sharedPrefManager3;
    SharedPrefManager sharedPrefManager4;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        sharedPrefManager3=SharedPrefManager.getInstance(getActivity());
        sharedPrefManager4=SharedPrefManager.getInstance(getActivity());

        // Inflate custom layout for the dialog
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.activity_newdialog_fragment, null);
        builder.setView(rootView);

//        // Find buttons within the inflated view
        EditText Email_user = rootView.findViewById(R.id.Email_user);
        EditText Fname = rootView.findViewById(R.id.FnameUser);
        EditText Lname = rootView.findViewById(R.id.LnameUser);
        EditText Phone = rootView.findViewById(R.id.PhoneUser);
        EditText password = rootView.findViewById(R.id.Pass_user);
        EditText Conf_pass = rootView.findViewById(R.id.Conf_pass_user);
        EditText Gender = rootView.findViewById(R.id.Gender);
        Button buttonOK = rootView.findViewById(R.id.ok);
        Button buttonCancel = rootView.findViewById(R.id.cancel);


        String emailUSER=sharedPrefManager3.readString("Email_user","");
        Email_user.setText(emailUSER);
        Email_user.setEnabled(false);
        DataBaseHelper dataBaseHelperUser = new
                DataBaseHelper(getActivity(), "User1", null, 1);
        Cursor allUserCursor = dataBaseHelperUser.SearchforUser(emailUSER);

        if (allUserCursor != null && allUserCursor.moveToFirst()) {
            Fname.setText(allUserCursor.getString(4));
            Lname.setText(allUserCursor.getString(5));
            String pass=sharedPrefManager4.readString("Pass_User","");
            password.setText(pass);
            String phoneNumber = allUserCursor.getString(2);
            String phoneis = phoneNumber.substring(2);
            Phone.setText(phoneis);
            Gender.setText(allUserCursor.getString(3));
            Gender.setEnabled(false);
        }
        // Set click listeners for the buttons
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = Fname.getText().toString().trim();
                String lastName = Lname.getText().toString().trim();
                String pass_user= password.getText().toString().trim();
                String Conf_pass_user = Conf_pass.getText().toString().trim();
                String phoneNumber = Phone.getText().toString().trim();

                if (firstName.isEmpty()) {
                    Fname.setError("the First name is Empt");
                } else if (firstName.length() < 3) {
                    Fname.setError("The First name is too short");
                }
                else if (!firstName.matches("[a-zA-Z]+")) {
                    Fname.setError("The First name should be letters");
                } else {

                    if (lastName.isEmpty()) {
                        Lname.setError("The Last name is Empty");
                    } else if (lastName.length() < 3) {
                        Lname.setError("The Last name is too short");
                    } else if (!lastName.matches("[a-zA-Z]+")) {
                        Lname.setError("The Last name should be letters");
                    } else {
                        if (pass_user.isEmpty()) {
                            password.setError("The Password is  Empty");
                        } else if (pass_user.length() < 8 || !pass_user.matches(".*[a-zA-Z].*")
                                || !pass_user.matches(".*\\d.*")) {
                            password.setError("The Passowrd should " +
                                    "be at least 8 characters include at least 1  character and 1 number");
                        } else {
                            if (!(pass_user.equals(Conf_pass_user))) {
                                Conf_pass.setError("The Passowrd Confirm is different");
                            } else {
                                if (phoneNumber.isEmpty()) {
                                    Phone.setError("the Phone is Empty");
                                } else if (phoneNumber.length() != 8 ) {
                                    Phone.setError("the phone should be 10 digits");
//                            text_error.setText("The phone should be 10 digites");
                                } else if (!(phoneNumber.matches("\\d+"))) {
                                    Phone.setError("the phone should be just digite");
                                } else {
                                    phoneNumber = "05" + phoneNumber;
                                    String hash_pass = Hash_password.hashPassword(pass_user);
                                    dataBaseHelperUser.updateUser(emailUSER, firstName, lastName, hash_pass, phoneNumber);
                                    Toast.makeText(getActivity(), "Informations Update", Toast.LENGTH_SHORT).show();
                                    dismiss(); // Close the dialog
                                }
                            }
                        }
                    }
                }

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