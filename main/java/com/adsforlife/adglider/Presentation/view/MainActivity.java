package com.adsforlife.adglider.Presentation.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.adsforlife.adglider.AdGliderApplication;
import com.adsforlife.adglider.Presentation.AdGliderViewModel;
import com.adsforlife.adglider.Presentation.AdGliderViewModelFactory;
import com.adsforlife.adglider.Presentation.fragment.ProfileFragment;
import com.adsforlife.adglider.R;
import com.adsforlife.adglider.data.localroom.Ad;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private DrawerLayout drawer;
    private AdGliderViewModel adGliderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout layout = (FrameLayout) findViewById(R.id.thePlaceHolder);
        layout.setVisibility(View.GONE);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        displayAds();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final FrameLayout layout = (FrameLayout) findViewById(R.id.thePlaceHolder);

        switch (item.getItemId()) {
            case R.id.nav_home:
                layout.setVisibility(View.GONE);
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.thePlaceHolder, new ProfileFragment()).commit();
                layout.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void getAds() {
        adGliderViewModel = new ViewModelProvider(this, new AdGliderViewModelFactory(AdGliderApplication.getAdGliderApplication())).get(AdGliderViewModel.class);
        adGliderViewModel.getAllAds().observeForever(new Observer<List<Ad>>() {
            @Override
            public void onChanged(@Nullable final List<Ad> ads) {
                //suntem siguri ca adaptorul nostru este de tipul GameAdapter
                AdAdapter adAdapter = (AdAdapter) recyclerView.getAdapter();
                if (adAdapter != null)
                    adAdapter.setAds(ads);
            }
        });
    }

    private void setAdsLayoutManager() {
        recyclerView = findViewById(R.id.adsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void setAdsAdapter() {
        recyclerView.setAdapter(new AdAdapter(this));
    }

    private void displayAds() {
        setAdsLayoutManager();
        setAdsAdapter();
        getAds();
    }

}