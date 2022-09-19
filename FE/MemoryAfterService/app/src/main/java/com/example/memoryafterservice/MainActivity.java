package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

// splash screen

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        handler = new Handler();
        Intent intent = new Intent(this, LoginActivity.class);
        handler.postDelayed(() -> {
            startActivity(intent);
            finish();
        }, 1500);
    }
}