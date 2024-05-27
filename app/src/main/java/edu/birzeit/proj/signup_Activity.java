package edu.birzeit.proj;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signup_Activity extends AppCompatActivity {
    String email;
    String phone;
    String Fname;
    String Lname;
    String password;
    String password1;
    // SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        String[] options = {"Male", "Female"};
        final Spinner genderSpinner = (Spinner)
                findViewById(R.id.spinner_Gender);
        ArrayAdapter<String> objGenderArr = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);
        //  sharedPrefManager =SharedPrefManager.getInstance(this);

        final EditText EmailEditText =
                (EditText) findViewById(R.id.editTextEmail);
        final EditText PasswordEditText =
                (EditText) findViewById(R.id.editTextPassword);
        final EditText Password_ConfirmEditText =
                (EditText) findViewById(R.id.editTextPassword_confirm);
        final EditText FnameEditText =
                (EditText) findViewById(R.id.editTextFname);
        final EditText LnameEditText =
                (EditText) findViewById(R.id.editTextLname);

        final EditText phoneEditText =
                (EditText) findViewById(R.id.editTextPhone);
        Button removeUsersButton = findViewById(R.id.removeUsersButton);
        removeUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeUsers();
            }
        });
        final TextView text_error =  (TextView) findViewById(R.id.the_error);
        Button CreateAccount = (Button) findViewById(R.id.buttonSignIn);
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User();
                Fname = FnameEditText.getText().toString().trim();
                if (Fname.isEmpty()) {
                    text_error.setText("The Fisrt name is Empty");
                } else if (Fname.length() <3) {
                    text_error.setText("The First name is too short");
                }
                else if (!Fname.matches("[a-zA-Z]+")) {
                    text_error.setText("The First name should be letters");

                } else {
                    text_error.setText("");

                    Lname = LnameEditText.getText().toString().trim();
                    if (Lname.isEmpty()) {
                        text_error.setText("The Last name is Empty");
                    } else if (Lname.length() < 3) {
                        text_error.setText("The Last name is too short");
                    }  else if (!Lname.matches("[a-zA-Z]+")) {
                        text_error.setText("The Last name should be letters");
                    }
                    else {
                        text_error.setText("");

                        email = EmailEditText.getText().toString().trim();
                        if (email.isEmpty()) {
                            text_error.setText("The Email is Empty");
                        } else if (!email.matches(".*@.*\\.com$")) {
                            text_error.setText("The Email is Wrong Way");
                        }
                        else {
                            DataBaseHelper dataBaseHelper = new
                                    DataBaseHelper(signup_Activity.this, "User1", null, 1);
                            Cursor allUserCursor = dataBaseHelper.SearchforUser(email);
                            if (allUserCursor.moveToFirst()) {
                                text_error.setText("There is an email that name");

                            } else {
                                text_error.setText("");

                                password = PasswordEditText.getText().toString().trim();
                                password1 = Password_ConfirmEditText.getText().toString().trim();
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
                                        phone = phoneEditText.getText().toString().trim();
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
                                            newUser.setGender(genderSpinner.getSelectedItem().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(text_error.getText().toString().isEmpty()){
                    newUser.setEmail(EmailEditText.getText().toString());
                    newUser.setPassword(password);
                    newUser.setLname(Lname);
                    newUser.setmPhone(phone);
                    newUser.setFname(Fname);
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(signup_Activity.this, "User1", null, 1);
                    dataBaseHelper.insertUser(newUser);

                    Intent intent = new Intent(signup_Activity.this, login_Activity.class);
                    signup_Activity.this.startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void removeUsers() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(signup_Activity.this, "User1", null, 1);
        dataBaseHelper.deleteAllUsers();
    }
}