package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class UploadSMSActivity extends AppCompatActivity {
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_sms_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();
        number = findViewById(R.id.UploadSMSPhone);
    }

    public void toSubUploadSMS(View view) {
        Intent intent = new Intent(this, SubUploadSMSActivity.class);
        intent.putExtra("number", number.getText());
        startActivity(intent);
    }
}