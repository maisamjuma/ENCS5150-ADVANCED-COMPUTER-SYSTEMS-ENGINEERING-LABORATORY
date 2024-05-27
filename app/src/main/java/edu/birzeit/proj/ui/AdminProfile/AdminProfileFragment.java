package edu.birzeit.proj.ui.AdminProfile;


import static androidx.fragment.app.FragmentManager.TAG;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.Admin;
import edu.birzeit.proj.DataBaseHelper;
import edu.birzeit.proj.DataBaseHelperAdmin;
import edu.birzeit.proj.Hash_password;
import edu.birzeit.proj.AdminPage;
import edu.birzeit.proj.MainActivity;
import edu.birzeit.proj.SharedPrefManager;
import edu.birzeit.proj.User;
import edu.birzeit.proj.databinding.FragmentAdminProfileBinding;
import edu.birzeit.proj.login_Activity;
import edu.birzeit.proj.signup_Activity;

public class AdminProfileFragment extends Fragment {

    private @NonNull FragmentAdminProfileBinding binding;
    SharedPrefManager sharedPrefManager1;
    SharedPrefManager sharedPrefManager2;


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
        final EditText phoneNumberEditText = binding.phoneNumber;
        final TextView text_error=binding.textError;
        final Button saveButton = binding.saveButton;
        sharedPrefManager1=SharedPrefManager.getInstance(getActivity());
        sharedPrefManager2=SharedPrefManager.getInstance(getActivity());

        String emailAdmin=sharedPrefManager1.readString("newEmail","");
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
            phoneNumberEditText.setText(allAdminCursor.getString(2));
            Gender.setText(allAdminCursor.getString(3));
            Gender.setEnabled(false);

        }    else {
            Toast.makeText(getActivity(), "Admin not found", Toast.LENGTH_SHORT).show();
        }

        saveButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString().trim();
            String lastName = lastNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String phoneNumber = phoneNumberEditText.getText().toString().trim();

            if (firstName.isEmpty()) {
                text_error.setText("The First name is Empty");
            } else if (firstName.length() < 3) {
                text_error.setText("The First name is too short");
            }
            else if (!firstName.matches("[a-zA-Z]+")) {
                text_error.setText("The First name should be letters");

            } else {
                text_error.setText("");

                if (lastName.isEmpty()) {
                    text_error.setText("The Last name is Empty");
                } else if (lastName.length() < 3) {
                    text_error.setText("The Last name is too short");
                }  else if (!lastName.matches("[a-zA-Z]+")) {
                    text_error.setText("The Last name should be letters");
                }
                else {
                    text_error.setText("");
                    if (password.isEmpty())
                        text_error.setText("The Passowrd  is Empty");
                    else if (password.length() < 8 || !password.matches(".*[a-zA-Z].*")
                            || !password.matches(".*\\d.*")) {
                        text_error.setText("The Passowrd should " +
                                "be at least 8 characters include at least 1  character and 1 number");
                    } else{
                        text_error.setText("");
                        if (phoneNumber.isEmpty()) {
                            text_error.setText("The phone is Empty");
                        } else if (phoneNumber.length() != 8) {
                            text_error.setText("The phone should be 10 digites");
                        }else if (!(phoneNumber.matches("\\d+"))) {
                            text_error.setText("The phone should be just digite");
                        }
                        else {
                            phoneNumber = "05" + phoneNumber;
                            text_error.setText("");
                            String hash_pass = Hash_password.hashPassword(password);
                            dataBaseHelperAdmin.updateAdmin(emailAdmin,firstName,lastName,hash_pass,phoneNumber);
                            Toast.makeText(getActivity(), "Informations Update", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                // Hash the password


            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}