package edu.birzeit.proj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login_Activity extends AppCompatActivity {
    EditText EmailEditText_login;
    EditText PasswordEditText;
    Button buttonSignIn;
    TextView message;
    TextView pass;
    CheckBox rememberMeCheckbox;
    SharedPrefManager sharedPrefManager;
    SharedPrefManager sharedPrefManager1;
    SharedPrefManager sharedPrefManager2;

    SharedPrefManager sharedPrefManager3;

    SharedPrefManager sharedPrefManager4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        message =  (TextView) findViewById(R.id.message);
        pass =  (TextView) findViewById(R.id.pass);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        sharedPrefManager1 =SharedPrefManager.getInstance(this);
        sharedPrefManager2 =SharedPrefManager.getInstance(this);
        sharedPrefManager3 =SharedPrefManager.getInstance(this);
        sharedPrefManager4 =SharedPrefManager.getInstance(this);

        rememberMeCheckbox = findViewById(R.id.checkboxRem_Me);

        EmailEditText_login =
                (EditText) findViewById(R.id.editTextEmail_login);
        PasswordEditText =
                (EditText) findViewById(R.id.editTextPasswordd);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_Activity.this, signup_Activity.class));
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        DataBaseHelperAdmin dataBaseHelperAdmin = new
                DataBaseHelperAdmin(login_Activity.this, "Admin1", null, 1);
        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(login_Activity.this, "User1", null, 1);
        EmailEditText_login.setText(sharedPrefManager.readString("Email",""));

        rememberMeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Save email to shared preferences when checkbox is checked
                    sharedPrefManager.writeString("Email", EmailEditText_login.getText().toString());
                }

            }

        });
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_login = EmailEditText_login.getText().toString().trim();
                String pass_login = PasswordEditText.getText().toString().trim();
                if (email_login.isEmpty()) {
                    EmailEditText_login.setError("Email is empty");

//                    message.setText("Email is empty");
                } else if (pass_login.isEmpty()) {

                    PasswordEditText.setError("Password is empty");

//                    message.setText("Password is empty");
                } else {
                    EmailEditText_login.setBackgroundColor(Color.WHITE);
                    Cursor allAdminCursor = dataBaseHelperAdmin.SearchforAdmin(email_login);
                    if (allAdminCursor.moveToFirst()) {
                        int passwordIndex = allAdminCursor.getColumnIndex("Password");
                        if (passwordIndex != -1) {

                            String pass_database = allAdminCursor.getString(passwordIndex);
                            sharedPrefManager2.writeString("Pass",pass_login);
                            pass_login =  Hash_password.hashPassword(pass_login);
                            if (pass_database.equals(pass_login)) {
                                sharedPrefManager1.writeString("newEmail",email_login);
                                Intent intent = new Intent(login_Activity.this, AdminPage.class);
                                login_Activity.this.startActivity(intent);
                                finish();
                            }
                            else {
//                                pass.setText("Incorrect password");
                                PasswordEditText.setError("Incorrect password");

//                                message.setText("");
                            }
                        }
                    } else {
                        //Customer Page
                        Cursor allUserCursor = dataBaseHelper.SearchforUser(email_login);
                        if (allUserCursor.moveToFirst()) {
                            int passwordIndex = allUserCursor.getColumnIndex("Password");
                            if (passwordIndex != -1) {
                                String pass_database = allUserCursor.getString(passwordIndex);
                                String pass_login1 = Hash_password.hashPassword(pass_login);
                                if (pass_database.equals(pass_login1)) {
                                    sharedPrefManager3.writeString("Email_user",email_login);
                                    sharedPrefManager4.writeString("Pass_User",pass_login);
                                    Intent intent = new Intent(login_Activity.this, HomePage.class);
                                    login_Activity.this.startActivity(intent);
                                    finish();
                                } else {
//                                    pass.setText("Incorrect password");
//                                    message.setText("");
                                    PasswordEditText.setError("Incorrect password");

                                }
                            }
                        }else {
//                            message.setText("NO USER");
                            EmailEditText_login.setError("NO USER");


                        }
                    }
                }
            }

        });
    }
}