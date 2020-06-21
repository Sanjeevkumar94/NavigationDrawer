package com.example.navigationdrawerex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mNavDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Facebook");

        mNavDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mNavDrawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close

        );

        mNavDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new FragmentHome())
                .commit();

        navigationView.setCheckedItem(R.id.home);


    }

    @Override
    public void onBackPressed() {
        if(mNavDrawer.isDrawerOpen(GravityCompat.START))
            mNavDrawer.closeDrawer(GravityCompat.START);
        else
          super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.chat:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new FragmentChat())
                        .commit();
                break;

                case R.id.home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new FragmentHome())
                        .commit();
                break;
        }
        mNavDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
