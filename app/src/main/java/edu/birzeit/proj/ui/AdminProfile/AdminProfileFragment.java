package edu.birzeit.proj.ui.AdminProfile;


import static android.app.Activity.RESULT_OK;
import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.Admin;
import edu.birzeit.proj.DataBaseHelper;
import edu.birzeit.proj.DataBaseHelperAdmin;
import edu.birzeit.proj.Hash_password;
import edu.birzeit.proj.AdminPage;
import edu.birzeit.proj.HomePage;
import edu.birzeit.proj.MainActivity;
import edu.birzeit.proj.R;
import edu.birzeit.proj.SharedPrefManager;
import edu.birzeit.proj.User;
import edu.birzeit.proj.databinding.FragmentAdminProfileBinding;
import edu.birzeit.proj.login_Activity;
import edu.birzeit.proj.signup_Activity;

public class AdminProfileFragment extends Fragment {

    private @NonNull FragmentAdminProfileBinding binding;
    SharedPrefManager sharedPrefManager1;
    SharedPrefManager sharedPrefManager2;
    SharedPrefManager sharedPrefManager5;

    private static int GALLERY_REQ_CODE = 1000;
    ImageButton imageB;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdminProfileViewModel AdminProfileViewModel =
                new ViewModelProvider(this).get(AdminProfileViewModel.class);

        binding = FragmentAdminProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final EditText firstNameEditText = binding.firstName;
        final EditText EmailAd = binding.EmailAdmin;
        final EditText Gender = binding.Gender;

        final EditText lastNameEditText = binding.lastName;
        final EditText passwordEditText = binding.password;
        final EditText passwordEditText1 = binding.passwordConff;
        final EditText phoneNumberEditText = binding.phoneNumber;
        final Button saveButton = binding.saveButton;

        imageB = root.findViewById(R.id.change_photo);

        sharedPrefManager1=SharedPrefManager.getInstance(getActivity());
        sharedPrefManager2=SharedPrefManager.getInstance(getActivity());
        sharedPrefManager5=SharedPrefManager.getInstance(getActivity());

        imageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Intent.ACTION_PICK);
                intents.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intents, GALLERY_REQ_CODE);
            }
        });
        String emailAdmin=sharedPrefManager1.readString("newEmail","");
//        sharedPrefManager5.writeString("rami",emailAdmin);
        DataBaseHelperAdmin dataBaseHelperAdmin = new
                DataBaseHelperAdmin(getActivity(), "Admin1", null, 1);
        Cursor allAdminCursor = dataBaseHelperAdmin.SearchforAdmin(emailAdmin);
        if (allAdminCursor != null && allAdminCursor.moveToFirst()) {
            EmailAd.setText(emailAdmin);
            EmailAd.setEnabled(false);

            firstNameEditText.setText(allAdminCursor.getString(4));
            lastNameEditText.setText(allAdminCursor.getString(5));
            String pass=sharedPrefManager2.readString("Pass","");
            passwordEditText.setText(pass);
            String phoneNumber = allAdminCursor.getString(2);
            String phoneis = phoneNumber.substring(2);
            phoneNumberEditText.setText(phoneis);
//          phoneNumberEditText.setText(allAdminCursor.getString(2));
            Gender.setText(allAdminCursor.getString(3));
            Gender.setEnabled(false);

        }    else {
            Toast.makeText(getActivity(), "Admin not found", Toast.LENGTH_SHORT).show();
        }

        saveButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString().trim();
            String lastName = lastNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String password1 = passwordEditText1.getText().toString().trim();

            String phoneNumber = phoneNumberEditText.getText().toString().trim();

            if (firstName.isEmpty()) {
                firstNameEditText.setError("the First name is Empt");
//                text_error.setText("The First name is Empty");
            } else if (firstName.length() < 3) {
                firstNameEditText.setError("The First name is too short");
//                text_error.setText("The First name is too short");
            }
            else if (!firstName.matches("[a-zA-Z]+")) {
                firstNameEditText.setError("The First name should be letters");
//                text_error.setText("The First name should be letters");
            } else {
//                text_error.setText("");

                if (lastName.isEmpty()) {
                    lastNameEditText.setError("The Last name is Empty");
//                    text_error.setText("The Last name is Empty");
                } else if (lastName.length() < 3) {
                    lastNameEditText.setError("The Last name is too short");
//                    text_error.setText("The Last name is too short");
                }  else if (!lastName.matches("[a-zA-Z]+")) {
                    lastNameEditText.setError("The Last name should be letters");
//                    text_error.setText("The Last name should be letters");
                }
                else {
//                    text_error.setText("");
                    if (password.isEmpty()) {
                        passwordEditText.setError("The Password is  Empty");
                    }
//                    text_error.setText("The Passowrd  is Empty");
                    else if (password.length() < 8 || !password.matches(".*[a-zA-Z].*")
                            || !password.matches(".*\\d.*")) {
                        passwordEditText.setError("The Passowrd should " +
                                "be at least 8 characters include at least 1  character and 1 number");
//                        text_error.setText("The Passowrd should " +
//                                "be at least 8 characters include at least 1  character and 1 number");
                    } else {
                        if (!(password.equals(password1))) {
//                                        text_error.setText("The PassowrdConfirm is different");
                            passwordEditText1.setError("The Passowrd Confirm is different");
                        } else {
//                        text_error.setText("");
                            if (phoneNumber.isEmpty()) {
                                phoneNumberEditText.setError("the Phone is Empty");
//                            text_error.setText("The phone is Empty");
                            } else if (phoneNumber.length() != 8) {
                                phoneNumberEditText.setError("the phone should be 10 digits");
//                            text_error.setText("The phone should be 10 digites");
                            } else if (!(phoneNumber.matches("\\d+"))) {
                                phoneNumberEditText.setError("the phone should be just digite");
//                            text_error.setText("The phone should be just digite");
                            } else {
                                phoneNumber = "05" + phoneNumber;
//                            text_error.setText("");
                                String hash_pass = Hash_password.hashPassword(password);
                                dataBaseHelperAdmin.updateAdmin(emailAdmin, firstName, lastName, hash_pass, phoneNumber);
                                Toast.makeText(getActivity(), "Informations Update", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }


            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE ) {
                imageB.setImageURI(data.getData());
            }
        }
    }

}