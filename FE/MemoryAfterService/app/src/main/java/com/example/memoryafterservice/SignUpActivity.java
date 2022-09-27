package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.memoryafterservice.dto.MemberReq;
import com.example.memoryafterservice.retrofit.MemberApi;
import com.example.memoryafterservice.retrofit.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    static final int SMS_SEND_PERMISSION = 1;
    private Toast toast;
    private Toast signupConfirm;
    private EditText verify;
    private Button verifyCheck;
    private TextInputEditText inputEditId;
    private TextInputEditText inputEditPw;
    private TextInputEditText inputEditName;
    private TextInputEditText inputEditPhone;
    private MaterialButton buttonSave;

    //EditText inputPhoneNum;
    Button sendSMSBt;

    private void sendSMS(String phoneNumber, String message)
    {
        PendingIntent pi = PendingIntent.getActivity(this,0,
                new Intent(this, SignUpActivity.class),0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);

        Toast.makeText(getBaseContext(), "메시지가 전송되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //inputPhoneNum = findViewById(R.id.SignUpPhone);
        //문자보내기 권한
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            //문자 보내기 권한 거부
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                Toast.makeText(getApplicationContext(), "SMS 권한이 필요합니다", Toast.LENGTH_SHORT).show();

            }
            //문자 보내기 권한 허용
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
        }

        Objects.requireNonNull(getSupportActionBar()).hide();
        verify = findViewById(R.id.SignUpVerify);
        verifyCheck = findViewById(R.id.SignUpVerifyButton);
        //toast = Toast.makeText(this, "기능 구현 중입니다.", Toast.LENGTH_SHORT);
        signupConfirm = Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT);

        inputEditId = findViewById(R.id.SignUpId);
        inputEditPw = findViewById(R.id.SignUpPw);
        inputEditName = findViewById(R.id.SignUpName);
        inputEditPhone = findViewById(R.id.SignUpPhone);
        sendSMSBt = findViewById(R.id.FindIdSendVerifyButton);
        inputEditPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){
            private boolean backspacingFlag = false;
            private boolean editedFlag = false;
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                cursorComplement = s.length()-inputEditPhone.getSelectionStart();
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
                        inputEditPhone.setText(ans);
                        inputEditPhone.setSelection(inputEditPhone.getText().length()-cursorComplement);

                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 3) + "-" + phone.substring(3);
                        inputEditPhone.setText(ans);
                        inputEditPhone.setSelection(inputEditPhone.getText().length()-cursorComplement);
                    }
                } else {
                    editedFlag = false;
                }
            }
        });

        // 인증번호전송 클릭시 이벤트 기능
        sendSMSBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendSMS(inputEditPhone.getText().toString(), "메세지 전송 테스트");

            }
        });

        buttonSave = findViewById(R.id.SignUpButton);
        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                signUp();
                signupConfirm.show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
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

        private void signUp() {
            String userid = inputEditId.getText().toString().trim();
            String password = inputEditPw.getText().toString().trim();
            String name = inputEditName.getText().toString().trim();
            String phone = inputEditPhone.getText().toString().trim();


            if (userid.isEmpty()) {
                inputEditId.setError("아이디를 입력해주십시오.");
                inputEditId.requestFocus();
                return;
            } else if (password.isEmpty()) {
                inputEditPw.setError("비밀번호를 입력해주십시오.");
                inputEditPw.requestFocus();
                return;
            } else if (name.isEmpty()) {
                inputEditName.setError("이름을 입력해주십시오,");
                inputEditName.requestFocus();
                return;
            } else if (phone.isEmpty()) {
                inputEditPhone.setError("휴대폰번호를 입력해주십시오.");
                inputEditPhone.requestFocus();
                return;
            }

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .saveMember(new MemberReq(userid, password, name, phone));

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s = "";
                    try {
                        s = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
//                    } catch (NullPointerException n) {
//                        n.printStackTrace();
                    }

                    if (s.equals("SUCCESS")) {
                        Toast.makeText(SignUpActivity.this, "회원가입되었습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
}