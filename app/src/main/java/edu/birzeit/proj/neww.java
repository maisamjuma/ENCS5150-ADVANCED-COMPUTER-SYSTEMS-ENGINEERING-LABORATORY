package edu.birzeit.proj;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class neww extends AppCompatActivity {

    LinearLayout secondLinearLayout;
    TextView phoneTextView;
    LinearLayout firstLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstLinearLayout = new LinearLayout(this);

        Button addButton = new Button(this);
        secondLinearLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);

        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.setOrientation(LinearLayout.VERTICAL);
        addButton.setText("Add User");
        addButton.setLayoutParams(new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams.WRAP_CONTENT));

        firstLinearLayout.addView(addButton);
        scrollView.addView(secondLinearLayout);
        firstLinearLayout.addView(scrollView);
        setContentView(firstLinearLayout);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(neww.this, signup_Activity.class);
                neww.this.startActivity(intent);
                finish();
            }
        });

    }

    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(neww.this, "User1", null, 1);
        Cursor allUserCursor = dataBaseHelper.getAllUser();

        while (allUserCursor.moveToNext()) {
            TextView textView = new TextView(neww.this);
            textView.setText(
                    "Email= " + allUserCursor.getString(0)
                            + "\nPassword= " + allUserCursor.getString(1)
                            + "\nPHONE= " + allUserCursor.getString(2)
                            + "\nGENDER= " + allUserCursor.getString(3)
                            + "\nFname= " + allUserCursor.getString(4)
                            + "\nLname= " + allUserCursor.getString(5)
                            + "\n\n"
            );
            secondLinearLayout.addView(textView);
        }

    }

}
