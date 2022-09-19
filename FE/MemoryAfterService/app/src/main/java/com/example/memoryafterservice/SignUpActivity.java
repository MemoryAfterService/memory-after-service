package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private Toast toast;
    private EditText verify;
    private Button verifyCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        verify = findViewById(R.id.SignUpVerify);
        verifyCheck = findViewById(R.id.SignUpVerifyButton);
        toast = Toast.makeText(this, "기능 구현 중입니다.", Toast.LENGTH_SHORT);
    }

    public void backToTOU(View view){
        super.onBackPressed();
    }

    public void idCheck(View view){
        toast.show();
    }

    public void sendVerification(View view) {
        toast.show();
        verify.setVisibility(View.VISIBLE);
        verifyCheck.setVisibility(View.VISIBLE);
    }

    public void signIn(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("아직 구현중입니다");
        builder.setTitle("구현중");
        builder.setNeutralButton("확인", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}