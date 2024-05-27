package edu.birzeit.proj.ui.AddAdmin;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.proj.Admin;
import edu.birzeit.proj.DataBaseHelper;
import edu.birzeit.proj.DataBaseHelperAdmin;
import edu.birzeit.proj.Hash_password;
import edu.birzeit.proj.R;
import edu.birzeit.proj.databinding.FragmentAddAdminBinding;
import edu.birzeit.proj.login_Activity;
import edu.birzeit.proj.signup_Activity;

public class AddAdminFragment extends Fragment {

    private FragmentAddAdminBinding binding;
    private EditText editTextFname, editTextLname, editTextEmail, editTextPassword, editTextPasswordConfirm, editTextPhone;
    private Spinner spinnerGender;
    private TextView text_error;
    String password,Fname,Lname,phone;
    private DataBaseHelperAdmin dataBaseHelper;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddAdminViewModel AddAdminViewModel =
                new ViewModelProvider(this).get(AddAdminViewModel.class);

        binding = FragmentAddAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String[] options = {"Male", "Female"};
        final Spinner spinnerGender = binding.spinnerGender;
        ArrayAdapter<String> objGenderArr = new
                ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options);
        spinnerGender.setAdapter(objGenderArr);
        editTextFname = binding.editTextFname;
        editTextLname = binding.editTextLname;
        editTextEmail = binding.editTextEmail;
        editTextPassword = binding.editTextPassword;
        editTextPasswordConfirm =binding.editTextPasswordConfirm;
        editTextPhone = binding.editTextPhone;
        text_error = binding.theError;
        Button buttonAddAdmin = binding.buttonSignIn;
        DataBaseHelperAdmin dataBaseHelperAdmin = new
                DataBaseHelperAdmin(getActivity(), "Admin1", null, 1);
        buttonAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin newAdmin = new Admin();
                Fname = editTextFname.getText().toString().trim();
                if (Fname.isEmpty()) {
                    text_error.setText("The Fisrt name is Empty");
                } else if (Fname.length() < 3) {
                    text_error.setText("The First name is too short");
                }
                else if (!Fname.matches("[a-zA-Z]+")) {
                    text_error.setText("The First name should be letters");

                } else {
                    text_error.setText("");

                    Lname = editTextLname.getText().toString().trim();
                    if (Lname.isEmpty()) {
                        text_error.setText("The Last name is Empty");
                    } else if (Lname.length() < 3) {
                        text_error.setText("The Last name is too short");
                    }  else if (!Lname.matches("[a-zA-Z]+")) {
                        text_error.setText("The Last name should be letters");
                    }
                    else {
                        text_error.setText("");

                        String email = editTextEmail.getText().toString().trim();
                        if (email.isEmpty()) {
                            text_error.setText("The Email is Empty");
                        } else if (!email.matches(".*@.*\\.com$")) {
                            text_error.setText("The Email is Wrong Way");
                        }
                        else {
                            Cursor allAdminCursor = dataBaseHelperAdmin.SearchforAdmin(email);
                            if (allAdminCursor.moveToFirst()) {
                                text_error.setText("There is an email that name");

                            } else {
                                text_error.setText("");

                                password = editTextPassword.getText().toString().trim();
                                String  password1 = editTextPasswordConfirm.getText().toString().trim();
                                password = Hash_password.hashPassword(password);
                                password1 = Hash_password.hashPassword(password1);

                                if (password.isEmpty())
                                    text_error.setText("The Passowrd  is Empty");
                                else if (password.length() < 8 || !password.matches(".*[a-zA-Z].*")
                                        || !password.matches(".*\\d.*")) {
                                    text_error.setText("The Passowrd should " +
                                            "be at least 8 characters include at least 1  character and 1 number");
                                } else {
                                    if (!(password.equals(password1))) {
                                        text_error.setText("The PassowrdConfirm is different");

                                    } else {
                                        text_error.setText("");
                                        phone = editTextPhone.getText().toString().trim();
                                        if (phone.isEmpty()) {
                                            text_error.setText("The phone is Empty");
                                        } else if (phone.length() != 8) {
                                            text_error.setText("The phone should be 10 digites");
                                        }else if (!(phone.matches("\\d+"))) {
                                            text_error.setText("The phone should be just digite");
                                        }
                                        else {
                                            phone = "05" + phone;
                                            text_error.setText("");
                                            newAdmin.setGender(spinnerGender.getSelectedItem().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(text_error.getText().toString().isEmpty()){
                    newAdmin.setEmail(editTextEmail.getText().toString());
                    newAdmin.setPassword(password);
                    newAdmin.setLname(Lname);
                    newAdmin.setmPhone(phone);
                    newAdmin.setFname(Fname);
                    DataBaseHelperAdmin dataBaseHelper = new DataBaseHelperAdmin(getActivity(), "Admin1", null, 1);
                    dataBaseHelper.insertAdmin(newAdmin);
                    Toast.makeText(getActivity(), "Admin Added successfully", Toast.LENGTH_SHORT).show();


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
}