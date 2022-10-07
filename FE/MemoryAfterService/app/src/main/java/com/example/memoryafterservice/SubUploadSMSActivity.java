package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SubUploadSMSActivity extends AppCompatActivity {
    private TextView smsText;
    ArrayList<HashMap<String,String>> smsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_upload_sms);
        Objects.requireNonNull(getSupportActionBar()).hide();
        smsText = findViewById(R.id.SubUploadSMS);
        smsData = new ArrayList<>();
        final int REQUEST_CODE_ASK_PERMISSIONS = 123;
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        getSMS();
    }

    private void getSMS(){
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                smsData.add(new HashMap<>());
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    smsData.get(smsData.size() - 1).put(cursor.getColumnName(idx),cursor.getString(idx));
                }
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
            smsText.setText("There is no SMS!!");
        }
    }
}