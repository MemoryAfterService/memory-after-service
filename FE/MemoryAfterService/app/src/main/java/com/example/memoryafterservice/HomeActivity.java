package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private HomeFragment homeFragment = new HomeFragment();
    private UploadFragment uploadFragment = new UploadFragment();
    private CalendarFragment calendarFragment = new CalendarFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bottomNavigationView = findViewById(R.id.HomeBottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) item -> {
            switch(item.getItemId()){
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.HomeFragmentContainer, homeFragment).commit();
                    break;
                case R.id.upload:
                    getSupportFragmentManager().beginTransaction().replace(R.id.HomeFragmentContainer, uploadFragment).commit();
                    break;
                case R.id.calendar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.HomeFragmentContainer, calendarFragment).commit();
                    break;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.HomeFragmentContainer, profileFragment).commit();
                    break;
            }
            return true;
        });
    }
}