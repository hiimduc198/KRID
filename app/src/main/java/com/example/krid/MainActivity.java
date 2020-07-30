package com.example.krid;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;

import com.example.krid.model.Advertiser;
import com.example.krid.ui.HomeFragment;
import com.example.krid.ui.LoginFragment;
import com.example.krid.ui.Register2Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_booking, R.id.nav_campaign, R.id.nav_login, R.id.nav_register1, R.id.nav_register2, R.id.adv_create_campaign,
                R.id.adv_home, R.id.adv_logout, R.id.adv_manage_delivery, R.id.adv_mycampaign_adv, R.id.adv_account)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void navigateToRegister2Fragment(Advertiser adv) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        Fragment fragment = new Register2Fragment();
        Bundle args = new Bundle();
        args.putSerializable("adv", adv);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack("Tag")
                .replace(R.id.nav_host_fragment, fragment, "Tag")
                .commit();

        drawer.closeDrawer(GravityCompat.START);
    }

    public void navigateToFragmentWithoutArgs(Fragment fragment) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack("Tag")
                .replace(R.id.nav_host_fragment, fragment, "Tag")
                .commit();

        drawer.closeDrawer(GravityCompat.START);
    }
}
