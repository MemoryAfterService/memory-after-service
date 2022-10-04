package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memoryafterservice.dto.MemberReq;
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

public class SignUpActivity extends AppCompatActivity {
    private Toast toast;
    private EditText editTextId;
    private EditText editTextPwd;
    private EditText editTextPwdConfirm;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextAuthCode;
    private TextView checkIdDuplicationMessage;
    private TextView checkPwdConfirmMessage;
    private Button checkIdDuplicationBtn;
    private Button sendAuthCodeBtn;
    private Button verifyBtn;
    private String authCode;
    private boolean idDuplicationCheckSuccess;
    private boolean passwordConfirmSuccess;
    private boolean authenticationSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextId = findViewById(R.id.SignUpId);
        editTextPwd = findViewById(R.id.SignUpPw);
        editTextPwdConfirm = findViewById(R.id.SignUpPwConfirm);
        editTextName = findViewById(R.id.SignUpName);
        editTextPhone = findViewById(R.id.SignUpPhone);
        editTextAuthCode = findViewById(R.id.SignUpVerify);
        checkIdDuplicationMessage = findViewById(R.id.SignUpIdCheckDesc);
        checkIdDuplicationBtn = findViewById(R.id.SignUpIdCheckButton);
        checkPwdConfirmMessage = findViewById(R.id.SignUpPwConfirmDesc);
        sendAuthCodeBtn = findViewById(R.id.SignUpSendVerifyButton);
        verifyBtn = findViewById(R.id.SignUpVerifyButton);
        idDuplicationCheckSuccess = false;
        passwordConfirmSuccess = false;
        authenticationSuccess = false;

        /** editTextPwdConfirm의 TextChangeListener */
        editTextPwdConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkPwdConfirmMessage.setVisibility(View.VISIBLE);
                if(editTextPwd.getText().toString().equals(editTextPwdConfirm.getText().toString())) {
                    passwordConfirmSuccess = true;
                    checkPwdConfirmMessage.setTextColor(Color.parseColor("#008000"));
                    checkPwdConfirmMessage.setText(R.string.SignInPwConfirmDescPass);
                } else {
                    passwordConfirmSuccess = false;
                    checkPwdConfirmMessage.setTextColor(Color.parseColor("#ED1A3D"));
                    checkPwdConfirmMessage.setText(R.string.SignInPwConfirmDescFail);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editTextPwd.getText().toString().equals(editTextPwdConfirm.getText().toString())) {
                    passwordConfirmSuccess = true;
                    checkPwdConfirmMessage.setTextColor(Color.parseColor("#008000"));
                    checkPwdConfirmMessage.setText(R.string.SignInPwConfirmDescPass);
                } else {
                    passwordConfirmSuccess = false;
                    checkPwdConfirmMessage.setTextColor(Color.parseColor("#ED1A3D"));
                    checkPwdConfirmMessage.setText(R.string.SignInPwConfirmDescFail);
                }
            }
        });

        /** editTextPhone의 TextChangeListener */
        editTextPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){
            private boolean backspacingFlag = false;
            private boolean editedFlag = false;
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                cursorComplement = s.length()-editTextPhone.getSelectionStart();
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                String phone = string.replaceAll("[^\\d]", "");

                if (!editedFlag) {
                    if (phone.length() >= 7 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 3) + "-" + phone.substring(3,7) + "-" + phone.substring(7);
                        editTextPhone.setText(ans);
                        editTextPhone.setSelection(editTextPhone.getText().length()-cursorComplement);

                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 3) + "-" + phone.substring(3);
                        editTextPhone.setText(ans);
                        editTextPhone.setSelection(editTextPhone.getText().length()-cursorComplement);
                    }
                } else {
                    editedFlag = false;
                }
            }
        });
    }

    public void backToTOU(View view){
        super.onBackPressed();
    }

    public void onClickCheckIdDuplicationBtn(View view) {
        String inputId = editTextId.getText().toString().trim();
        if("".equals(inputId)) {
            editTextId.setError("아이디를 입력해주십시오.");
            editTextId.requestFocus();
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("inputid", inputId);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getMemberApi()
                .checkDuplicatedId(params);

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

                    if("duplicated".equals(message)) {
                        idDuplicationCheckSuccess = false;
                        checkIdDuplicationMessage.setTextColor(Color.parseColor("#ED1A3D"));
                        checkIdDuplicationMessage.setText(R.string.SignInIdCheckDescFail);
                    } else if("nonduplicated".equals(message)) {
                        idDuplicationCheckSuccess = true;
                        checkIdDuplicationMessage.setTextColor(Color.parseColor("#008000"));
                        checkIdDuplicationMessage.setText(R.string.SignInIdCheckDescPass);
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

    public void onClickRegisterBtn(View view) {
        String userId = editTextId.getText().toString().trim();
        String userPasword = editTextPwd.getText().toString().trim();
        String userPasswordConfirm = editTextPwdConfirm.getText().toString().trim();
        String userName = editTextName.getText().toString().trim();
        String phoneNumber = editTextPhone.getText().toString().trim();
        String inputAuthCode = editTextAuthCode.getText().toString().trim();

        if ("".equals(userId)) {
            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextId.requestFocus();
            return;
        }
        if ("".equals(userPasword)) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextPwd.requestFocus();
            return;
        }
        if ("".equals(userPasswordConfirm)) {
            Toast.makeText(getApplicationContext(), "비밀번호 재확인을 해주세요.", Toast.LENGTH_SHORT).show();
            editTextPwdConfirm.requestFocus();
            return;
        }
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
        if ("".equals(inputAuthCode)) {
            Toast.makeText(getApplicationContext(), "인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextAuthCode.requestFocus();
            return;
        }
        if(!idDuplicationCheckSuccess) {
            Toast.makeText(getApplicationContext(), "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!passwordConfirmSuccess) {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!authenticationSuccess) {
            Toast.makeText(getApplicationContext(), "휴대전화 번호 인증을 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        MemberReq params = new MemberReq(userId, userPasword, userName, phoneNumber);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getMemberApi().
                saveMember(params);

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
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "회원가입에 성공하였습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
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