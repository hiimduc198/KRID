package com.example.krid.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.krid.R;
import com.example.krid.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class InfluenceActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf);
        toolbar = findViewById(R.id.toolbar_inf);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout_inf);
        NavigationView navigationView = findViewById(R.id.nav_view_inf);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.inf_home, R.id.inf_all_campaigns, R.id.inf_my_campaigns, R.id.inf_account, R.id.inf_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_inf);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        SharedPreferences pref = this.getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);                                SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.PREF_KEY_SESSION_ID, "p7PVxaBiiJ8Xuy0MhwUE");
        editor.putString(Constants.PREF_KEY_SESSION_NAME, "kien");
        editor.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_inf);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void navigateToFragmentWithArgs(Fragment fragment, Serializable param) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_inf);

        Bundle args = new Bundle();
        args.putSerializable("param", param);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack("Tag")
                .replace(R.id.nav_host_fragment_inf, fragment, "Tag")
                .commit();

        drawer.closeDrawer(GravityCompat.START);
    }

    public void navigateToFragmentWithoutArgs(Fragment fragment) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_inf);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack("Tag")
                .replace(R.id.nav_host_fragment_inf, fragment, "Tag")
                .commit();

        drawer.closeDrawer(GravityCompat.START);
    }
}
