package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.Objects;

public class AnalysisActivity extends AppCompatActivity {
    private LineAnalysisFragment chattingAnalysisFragment = new LineAnalysisFragment();
    private DaytalkAnalysisFragment consumptionAnalysisFragment = new DaytalkAnalysisFragment();
    private WordAnalysisFragment wordAnalysisFragment = new WordAnalysisFragment();

    private BottomNavigationView analysisNavigationView;

    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        Objects.requireNonNull(getSupportActionBar()).hide();

        analysisNavigationView = findViewById(R.id.AnalysisNavigationBar);
        analysisNavigationView.setSelectedItemId(R.id.LineAnalysisMenu);

        analysisNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.LineAnalysisMenu:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.AnalysisFragment, chattingAnalysisFragment)
                            .commit();
                    break;
                case R.id.DayTalkAnalysisMenu:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.AnalysisFragment, consumptionAnalysisFragment)
                            .commit();
                    break;
                case R.id.WordAnalysisMenu:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.AnalysisFragment, wordAnalysisFragment)
                            .commit();
            }
            return true;
        });
    }
}