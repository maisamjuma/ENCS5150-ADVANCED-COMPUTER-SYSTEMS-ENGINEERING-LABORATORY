package edu.birzeit.proj;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import edu.birzeit.proj.databinding.ActivityAdminPageBinding;


public class AdminPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityAdminPageBinding binding;
    SharedPrefManager sharedPrefManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarAdminPage.toolbar);
        binding.appBarAdminPage.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

        sharedPrefManager1=SharedPrefManager.getInstance(this);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_Adminprofile, R.id.nav_Addadmin, R.id.nav_ViewAllorders,R.id.nav_Adddeals,R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_admin_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView navigationView1 = findViewById(R.id.nav_view);
        View headerView = navigationView1.getHeaderView(0); // 0 indicates the first header
        TextView Email = headerView.findViewById(R.id.theemail); // Replace 'your_textview_id' with the actual id of your TextView
        TextView Name = headerView.findViewById(R.id.thename); // Replace 'your_textview_id' with the actual id of your TextView

        String emailAdmin=sharedPrefManager1.readString("newEmail","");
        Email.setText(emailAdmin);
        DataBaseHelperAdmin dataBaseHelperAdmin = new
                DataBaseHelperAdmin(AdminPage.this, "Admin1", null, 1);
        Cursor allAdminCursor = dataBaseHelperAdmin.SearchforAdmin(emailAdmin);
        if (allAdminCursor != null && allAdminCursor.moveToFirst()) {
            Name.setText(allAdminCursor.getString(4)+" "+allAdminCursor.getString(5));
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item clicks here
                NavController navController = Navigation.findNavController(AdminPage.this, R.id.nav_host_fragment_content_admin_page);

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
        getMenuInflater().inflate(R.menu.admin_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_admin_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}