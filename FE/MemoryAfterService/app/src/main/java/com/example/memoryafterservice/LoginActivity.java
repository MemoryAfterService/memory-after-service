package com.example.memoryafterservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        toast = Toast.makeText(this, "기능 구현 중 입니다.", Toast.LENGTH_SHORT);

        String text = getResources().getString(R.string.LoginFindPwId);
        SpannableString str = new SpannableString(text);

        Intent moveId = new Intent(this, FindIdActivity.class);
        Intent movePw = new Intent(this, FindPwActivity.class);

        ClickableSpan findId = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(moveId);
            }
        };
        ClickableSpan findPw = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(movePw);
            }
        };

        str.setSpan(findId, 13, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(findPw, 23, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView findIdPw = findViewById(R.id.FindIdPw);
        findIdPw.setText(str, TextView.BufferType.SPANNABLE);
        findIdPw.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void DoLogin(View view){
        toast.show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void launchTOUActivity(View view){
        Intent intent = new Intent(this, TermOfUseActivity.class);
        startActivity(intent);
    }

    public void DoSocialLogin(View view){
        toast.show();
    }
}