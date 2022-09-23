package com.example.memoryafterservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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

        initializeComponents();
    }

    private void initializeComponents() {

        TextInputEditText inputEditId = findViewById(R.id.SignUpId);
        TextInputEditText inputEditPw = findViewById(R.id.SignUpPw);
        TextInputEditText inputEditName = findViewById(R.id.SignUpName);
        TextInputEditText inputEditPhone = findViewById(R.id.SignUpPhone);
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
                            Toast.makeText(SignUpActivity.this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
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

    public void signIn(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("아직 구현중입니다");
        builder.setTitle("구현중");
        builder.setNeutralButton("확인", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}