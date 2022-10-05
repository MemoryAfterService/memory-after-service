package com.example.memoryafterservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class AnalysisActivity extends AppCompatActivity {
    private ChattingAnalysisFragment chattingAnalysisFragment = new ChattingAnalysisFragment();
    private ConsumptionAnalysisFragment consumptionAnalysisFragment = new ConsumptionAnalysisFragment();

    private BottomNavigationView analysisNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        Objects.requireNonNull(getSupportActionBar()).hide();

        analysisNavigationView = findViewById(R.id.AnalysisNavigationBar);
        analysisNavigationView.setSelectedItemId(R.id.ChattingAnalysisMenu);

        analysisNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ChattingAnalysisMenu:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.AnalysisFragment, chattingAnalysisFragment)
                                .commit();
                        break;
                    case R.id.ConsumptionAnalysisMenu:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.AnalysisFragment, consumptionAnalysisFragment)
                                .commit();
                        break;
                }
                return true;
            }
        });
    }
}