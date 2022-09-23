package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.memoryafterservice.dto.MemberReq;
import com.example.memoryafterservice.retrofit.MemberApi;
import com.example.memoryafterservice.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private Toast toast;
    private Toast signupConfirm;
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
        signupConfirm = Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT);

        initializeComponents();
    }

    private void initializeComponents() {

        TextInputEditText inputEditId = findViewById(R.id.SignUpId);
        TextInputEditText inputEditPw = findViewById(R.id.SignUpPw);
        TextInputEditText inputEditName = findViewById(R.id.SignUpName);
        TextInputEditText inputEditPhone = findViewById(R.id.SignUpPhone);
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


        MaterialButton buttonSave = findViewById(R.id.SignUpButton);

        RetrofitService retrofitService = new RetrofitService();
        MemberApi memberApi = retrofitService.getRetrofit().create(MemberApi.class);

        buttonSave.setOnClickListener(view -> {
            String userid = String.valueOf(inputEditId.getText());
            String password = String.valueOf(inputEditPw.getText());
            String name = String.valueOf(inputEditName.getText());
            String phone = String.valueOf(inputEditPhone.getText());

            MemberReq memberReq = new MemberReq();
            memberReq.setUserid(userid);
            memberReq.setPassword(password);
            memberReq.setName(name);
            memberReq.setPhone(phone);



            memberApi.save(memberReq)
                    .enqueue(new Callback<MemberReq>() {
                        @Override
                        public void onResponse(Call<MemberReq> call, Response<MemberReq> response) {
                            signupConfirm.show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
//                            Toast.makeText(SignUpActivity.this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<MemberReq> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(SignUpActivity.class.getName()).log(Level.SEVERE, "Error Occurred", t);

                        }
                    });

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

//    public void signUp(View view) {
//        signupConfirm.show();
//        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
}