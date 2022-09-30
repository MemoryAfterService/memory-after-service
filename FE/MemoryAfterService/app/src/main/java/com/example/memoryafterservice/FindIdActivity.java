package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class FindIdActivity extends AppCompatActivity {
    private Toast toast;
    private TextView verify;
    private Button verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
        Objects.requireNonNull(getSupportActionBar()).hide();
        verify = findViewById(R.id.FindIdVerify);
        verifyBtn = findViewById(R.id.FindIdVerifyButton);
        toast = Toast.makeText(this, "기능 구현 중입니다.", Toast.LENGTH_SHORT);
    }

    public void BackToLogin(View view){
        super.onBackPressed();
    }

    public void sendVerification(View view) {
        toast.show();
        verify.setVisibility(View.VISIBLE);
        verifyBtn.setVisibility(View.VISIBLE);
    }

    public void findId(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("아직 구현중입니다");
        builder.setTitle("구현중");
        builder.setNeutralButton("확인", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}