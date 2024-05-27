package edu.birzeit.proj;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button get_started;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        String password = Hash_password.hashPassword("pizza12345");

        Admin admin = new Admin(
                "doctorpizza@gmail.com",
                password,
                "0595101520",
                "Male",
                "Doctor",
                "pizza");
        DataBaseHelperAdmin dbHelper = new DataBaseHelperAdmin(MainActivity.this, "Admin1", null, 1);
        Cursor allUserCursor = dbHelper.SearchforAdmin(admin.getEmail());
        if (!(allUserCursor.moveToFirst())) {
            dbHelper.insertAdmin(admin);
        }

        get_started = (Button) findViewById(R.id.get_started);
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionAsyncTask connectionAsyncTask = new
                        ConnectionAsyncTask(MainActivity.this);
                connectionAsyncTask.execute("https://18fbea62d74a40eab49f72e12163fe6c.api.mockbin.io/");

            }

        });
    }


    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar)
                findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}