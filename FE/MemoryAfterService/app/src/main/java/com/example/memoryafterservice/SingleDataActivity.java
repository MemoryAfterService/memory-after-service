package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class SingleDataActivity extends AppCompatActivity {

    private HashSet<LocalDate> singleDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_data);
        Objects.requireNonNull(getSupportActionBar()).hide();

        singleDate = (HashSet<LocalDate>) getIntent().getExtras().get("singleDate");

        Toast toast = Toast.makeText(this, singleDate.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }
}