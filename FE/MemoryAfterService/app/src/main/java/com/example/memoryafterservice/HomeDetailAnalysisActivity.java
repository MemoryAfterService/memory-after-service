package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Objects;

public class HomeDetailAnalysisActivity extends AppCompatActivity {
    private LinearLayout recentAmount;
    private LinearLayout recentAmountData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail_analysis);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Objects.requireNonNull(getSupportActionBar()).hide();
        recentAmount = findViewById(R.id.DetailLayoutRecentAmountLayout);
        recentAmountData = findViewById(R.id.DetailAnalysisRecentAmountDataLayout);
    }

    public void close(View view) {
        super.onBackPressed();
    }

    public void expand(View view) {
        int visibility = (recentAmountData.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

        TransitionManager.beginDelayedTransition(recentAmount, new AutoTransition());
        recentAmountData.setVisibility(visibility);
    }
}