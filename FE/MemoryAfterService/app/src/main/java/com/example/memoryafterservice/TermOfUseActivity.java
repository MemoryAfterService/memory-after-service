package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Objects;

public class TermOfUseActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_use);
        Objects.requireNonNull(getSupportActionBar()).hide();
        toast = Toast.makeText(this, "체크박스를 클릭해 주세요!", Toast.LENGTH_SHORT);
        checkBox = findViewById(R.id.TermOfUseCheck);
    }

    public void BackToLogin(View view){
        super.onBackPressed();
    }

    public void launchSignUpActivity(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        if(checkBox.isChecked()) {
            startActivity(intent);
        }else{
            toast.show();
        }
    }
}