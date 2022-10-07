package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.memoryafterservice.dto.FindIdReq;
import com.example.memoryafterservice.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindIdActivity extends AppCompatActivity {
    private Toast toast;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextAuthCode;
    private TextView textViewFindId;
    private Button sendAuthCodeBtn;
    private Button verifyBtn;
    private String authCode;
    private boolean authenticationSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextName = findViewById(R.id.FindIdName);
        editTextPhone = findViewById(R.id.FindIdPhone);
        editTextAuthCode = findViewById(R.id.FindIdVerify);
        sendAuthCodeBtn = findViewById(R.id.SignUpSendVerifyButton);
        textViewFindId = findViewById(R.id.FindIdTextView);
        verifyBtn = findViewById(R.id.FindIdVerifyButton);
        authenticationSuccess = false;
    }

    public void BackToLogin(View view) {
        super.onBackPressed();
    }

    public void sendVerification(View view) {
        String phoneNumber = editTextPhone.getText().toString().trim();

        if("".equals(phoneNumber)) {
            editTextPhone.setError("휴대전화 번호를 입력해주십시오.");
            editTextPhone.requestFocus();
            return;
        }

        sendAuthCodeBtn.setText("인증번호 재전송");
        editTextAuthCode.setVisibility(View.VISIBLE);
        verifyBtn.setVisibility(View.VISIBLE);
        authenticationSuccess = false;

        Map<String, Object> params = new HashMap<>();
        params.put("phone", phoneNumber);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAuthApi()
                .authenticateMobile(params);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = null;
                JSONObject json = null;
                String message = null;
                try {
                    responseBody = response.body().string();
                    json = new JSONObject(responseBody);
                    message = json.getString("message");

                    if ("success".equals(message)) {
                        authCode = json.getString("authcode");
                    } else {
                        Toast.makeText(getApplicationContext(), "인증번호를 재요청 해주세요.", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void onClickVerifyBtn(View view) {
        String inputCode = editTextAuthCode.getText().toString().trim();

        if("".equals(inputCode)) {
            editTextAuthCode.setError("인증번호를 입력해주십시오.");
            editTextAuthCode.requestFocus();
            return;
        }

        if (authCode.equals(inputCode)) {
            authenticationSuccess = true;
            Toast.makeText(getApplicationContext(), "인증 성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "인증 실패", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickFindIdBtn(View view) {
        String userName = editTextName.getText().toString().trim();
        String phoneNumber = editTextPhone.getText().toString().trim();

        if ("".equals(userName)) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextName.requestFocus();
            return;
        }
        if ("".equals(phoneNumber)) {
            Toast.makeText(getApplicationContext(), "휴대전화 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextPhone.requestFocus();
            return;
        }
        if (!authenticationSuccess) {
            Toast.makeText(getApplicationContext(), "휴대전화 번호 인증을 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        FindIdReq params = new FindIdReq(userName, phoneNumber);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getMemberApi().
                findUserId(params);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = null;
                JSONObject json = null;
                String message = null;

                try {
                    responseBody = response.body().string();
                    json = new JSONObject(responseBody);
                    message = json.getString("message");

                    if("success".equals(message)) {
                        String findId = json.getString("userid");
                        String textMessage = String.format("당신의 아이디는\n[%s] 입니다.", findId);
                        textViewFindId.setText(textMessage);
                        textViewFindId.setVisibility(View.VISIBLE);
                        //Toast.makeText(getApplicationContext(), findId, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디 찾기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}