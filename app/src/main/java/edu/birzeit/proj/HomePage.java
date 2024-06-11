package edu.birzeit.proj;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import edu.birzeit.proj.R;
import edu.birzeit.proj.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {

    SharedPrefManager sharedPrefManager3;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomePage.toolbar);
        binding.appBarHomePage.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        sharedPrefManager3=SharedPrefManager.getInstance(this);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Define the top level destinations (menu items)
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_yourfavorites, R.id.nav_hotdeals,
                R.id.nav_notifications, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        NavigationView navigationView1 = findViewById(R.id.nav_view);
        View headerView = navigationView1.getHeaderView(0); // 0 indicates the first header
        TextView Email = headerView.findViewById(R.id.email_user); // Replace 'your_textview_id' with the actual id of your TextView
        TextView Name = headerView.findViewById(R.id.name_user); // Replace 'your_textview_id' with the actual id of your TextView

        String emailUser=sharedPrefManager3.readString("Email_user","");
        Email.setText(emailUser);
        DataBaseHelper dataBaseHelperUser = new
                DataBaseHelper(HomePage.this, "User1", null, 1);
        Cursor allUserCursor = dataBaseHelperUser.SearchforUser(emailUser);
        if (allUserCursor != null && allUserCursor.moveToFirst()) {
            Name.setText(allUserCursor.getString(4)+" "+allUserCursor.getString(5));
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item clicks here
                NavController navController = Navigation.findNavController(HomePage.this, R.id.nav_host_fragment_content_home_page);

                // Check if the clicked item is the logout item
                if (item.getItemId() == R.id.nav_logout) {
                    // Handle logout here
                    navigateToLogin();
                    return true; // Return true to indicate that the item is handled
                }

                else {
                    // Handle other navigation items
                    boolean handled = NavigationUI.onNavDestinationSelected(item, navController);

                    // Close the navigation drawer after item selection
                    DrawerLayout drawer = binding.drawerLayout;
                    drawer.closeDrawers();

                    return handled;
                }
            }
        });

    }



    private void navigateToLogin() {
        // Start the LoginActivity
        Intent intent = new Intent(this, login_Activity.class);
        startActivity(intent);

        // Finish the current activity
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}