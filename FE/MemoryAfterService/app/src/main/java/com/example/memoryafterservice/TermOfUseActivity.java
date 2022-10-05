package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
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
        Boolean loggedIn = getIntent().getExtras().getBoolean("loggedIn");
        TextView backMain = findViewById(R.id.BackMain);
        TextView backProfile = findViewById(R.id.BackProfile);
        ScrollView termOfUse = findViewById(R.id.TermOfUseTextView);
        CheckBox termOfUseCheck = findViewById(R.id.TermOfUseCheck);
        Button TOUButton = findViewById(R.id.TOUButton);

        if (loggedIn == false) {


        } else {
            backMain.setVisibility(View.GONE);
            backProfile.setVisibility(View.VISIBLE);
            termOfUseCheck.setVisibility(View.GONE);
            TOUButton.setVisibility(View.GONE);
            ConstraintLayout.LayoutParams prams = (ConstraintLayout.LayoutParams) termOfUse.getLayoutParams();
            int newHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,440,getResources().getDisplayMetrics());
            prams.height = newHeight;
            prams.topToBottom = backProfile.getId();
            termOfUse.setLayoutParams(prams);
            termOfUse.requestLayout();
        }
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