package com.example.memoryafterservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private Toast toast;

    private ISessionCallback mSessionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {

                UserManagement.getInstance().me(new MeV2ResponseCallback()
                {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Toast.makeText(LoginActivity.this, "로그인 도중에 오류가 발생했습니다..",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Toast.makeText(LoginActivity.this, "세션이 닫혔습니다..다시 시도해주세요",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, "환영합니다!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {

            }
        };

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
        intent.putExtra("loggedIn", false);
        startActivity(intent);
    }

    private void getAppKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }

    public void DoSocialLogin(View view){
        toast.show();
    }
}