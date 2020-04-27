package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.fragments.ActivityFragment;
import com.example.myapplication.ui.fragments.FriendsFragment;
import com.example.myapplication.ui.fragments.GoalsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    Intent intent;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
//    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottom_navigation = findViewById(R.id.bottom_navigation);
        Menu menu = bottom_navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Log.i("home", "home inside1 home");
                        intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(ProfileActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        return true;
                    case R.id.activities:
                        Log.i("Activities", "Activities inside1 Activities");
                        intent = new Intent(ProfileActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ActivityFragment();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
        fragmentTransaction.commit();

        tab = findViewById(R.id.tabs);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch(tab.getPosition()) {
                    case 0:
                        Log.i("Activities", "Activities inside1 Activities");
                        Fragment fragment = new ActivityFragment();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        Log.i("Friends", "Friends inside1 Friends");
                        fragment = new FriendsFragment();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        Log.i("Goals", "Goals inside1 Goals");
                        fragment = new GoalsFragment();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        Button logoutBT = findViewById(R.id.logoutBT);
//        logoutBT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Set LoggedIn status to false
//                SaveSharedPreference.setLoggedIn(ProfileActivity.this, false);
//                // Logout
//                logout();
//
//            }
//        });

    }


    public void logout() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }



}
