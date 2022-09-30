package com.example.memoryafterservice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.memoryafterservice.dto.MemberReq;
import com.example.memoryafterservice.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.transform.Source;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private String prefName;
    private String prefUserid;
    private String prefPhone;
    private Long prefId;
    private EditText displayuserid;
    private EditText displayName;
    private View view;
    private ImageView profileImage;
    private ImageButton profileImageBtn;
    private LinearLayout termOfUse;
    private LinearLayout withdrawal;
    private LinearLayout signout;
    private LinearLayout changePw;

    public ProfileFragment() {
        // Required empty public constructor
        super(R.layout.fragment_profile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("prefVars", Context.MODE_PRIVATE);
        prefName = prefs.getString("prefName","");
        prefUserid = prefs.getString("prefUserid","");
        prefPhone = prefs.getString("prefPhone", "");
        prefId = prefs.getLong("prefId",0);

        setEvents();
        return view;
    }
    public void setEvents(){
        profileImage = view.findViewById(R.id.ProfileImageView);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == -1) {
                            Intent intent = result.getData();
                            Uri uri = intent.getData();
                            profileImage.setImageURI(uri);

                        }
                    }
                });
        profileImageBtn = view.findViewById(R.id.ProfileImageChangeButton);
        profileImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                intent.setAction(intent.ACTION_PICK);
                activityResultLauncher.launch(intent);


            }
        });



        displayuserid = view.findViewById(R.id.ProfileIdEditText);
        displayuserid.setText(prefUserid);
//        displayuserid.setText(prefId.toString());
        displayName = view.findViewById(R.id.editTextTextPersonName4);
        displayName.setText(prefName);

        termOfUse = view.findViewById(R.id.ProfileTOULayout);
        termOfUse.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TermOfUseActivity.class);
            intent.putExtra("loggedIn", true);
            startActivity(intent);
        });

        withdrawal = view.findViewById(R.id.ProfileSignoutLayout);
        withdrawal.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("");
//            builder.setMessage("회원탈퇴를 할 경우,\n해당 계정의 모든 데이터가 삭제됩니다\n회원탈퇴를 원하신다면\n비밀번호를 입력 후 탈퇴버튼을 눌러주십시오.");
            builder.setMessage("회원탈퇴를 할 경우,\n해당 계정의 모든 데이터가 삭제됩니다\n회원탈퇴를 원하신다면\n탈퇴버튼을 눌러주십시오.");

//            final EditText input = new EditText(getActivity().getApplicationContext());
//            input.setHint(getString(R.string.pwEditText));
//            input.setGravity(Gravity.CENTER_HORIZONTAL);
//            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//            builder.setView(input);


            builder.setNegativeButton("회원탈퇴", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getMemberApi()
                            .withdrawFromMember(prefUserid);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String s = "";
                            String msg = "";
                            try {
                                s = response.body().string();
                                JSONObject json = new JSONObject(s);
                                msg = json.getString("message");
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
//                            } catch (NullPointerException n) {
//                                n.printStackTrace();
                            }
                            if ("success".equals(msg)) {
                                Toast.makeText(getActivity(), "회원탈퇴하셨습니다.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    //                    Log.d("alarm", s);
                            }
                        }


                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
//                    String value = input.getText().toString();
//                    value.toString();
                }
            });
            builder.setPositiveButton("취소", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
//                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        signout = view.findViewById(R.id.ProfileLogout);
        signout.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("로그아웃하시겠습니까?");
            builder.setTitle("");
            builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id){
//                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("로그아웃", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
//            Intent intent = new Intent(getActivity(), LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
        });

        changePw = view.findViewById(R.id.ProfileChangePwLayout);
        changePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangePwDialog();
            }
        });


    }
    void showChangePwDialog() {
        View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(
                R.layout.layout_change_pw_dialog, (LinearLayout) getActivity().findViewById(R.id.changePwDialog));
        EditText editCurrentPw = view.findViewById(R.id.currentPw);
        EditText editNewPw = view.findViewById(R.id.newPw);
        EditText editNewPw2 = view.findViewById(R.id.newPw2);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final AlertDialog alertdialog = builder.setTitle("비밀번호 변경")
                .setView(view)
                .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("변경", null)
                .create();
        alertdialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button buttonChangePw = alertdialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                buttonChangePw.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String currentPw = editCurrentPw.getText().toString().trim();
                        String newPw = editNewPw.getText().toString().trim();
                        String newPw2 = editNewPw2.getText().toString().trim();

                        if (currentPw.isEmpty()) {
                            editCurrentPw.setError("현재 비밀번호를 입력해주십시오");
                            editCurrentPw.requestFocus();
                            return;
                        } else if (newPw.isEmpty()) {
                            editNewPw.setError("새로운 비밀번호를 입력해주십시오.");
                            editNewPw.requestFocus();
                            return;
                        } else if (newPw2.isEmpty()) {
                            editNewPw2.setError("새로운 비밀번호를 다시 입력해주십시오.");
                            editNewPw2.requestFocus();
                            return;
                        }

                        if (newPw.equals(newPw2)) {

                        } else {
                            editNewPw2.setError("새로운 비밀번호를 동일하게 입력해주십시오.");
                            editNewPw2.requestFocus();
                            return;}

                        Call<ResponseBody> call = RetrofitClient
                                .getInstance()
                                .getMemberApi()
                                .modifyMemberPassword(new MemberReq(prefUserid, newPw, prefName, null));

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                Log.d("Tag", "응답");
                                String s = "";
                                String msg = "";
                                try {
                                    s = response.body().string();
                                    JSONObject json = new JSONObject(s);
                                    msg = json.getString("message");
                                } catch(IOException|JSONException e) {
                                    e.printStackTrace();
                                }
                                if ("success".equals(msg)) {
                                    Toast.makeText(getActivity().getApplicationContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

            }
        });
        alertdialog.show();
    }

}
//    void showChangePwDialog() {
//        final View changePwDialog = getLayoutInflater().inflate(R.layout.activity_change_pw_dialog, null);
//        final int changePwInt = 5;
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        final AlertDialog alertdialog = builder.setTitle("비밀번호 변경")
//                .setView(changePwDialog)
//                .setPositiveButton("취소", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setNegativeButton("변경", null)
//                .create();
//
//        alertdialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//
//                Button buttonChangePw = alertdialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//                buttonChangePw.setOnClickListener(new View.OnClickListener(){
//
//                    @Override
//                    public void onClick(View view) {
//                        EditText editCurrentPw = com.example.memoryafterservice.changePwDialog.editCurrentPw;
//                        EditText editNewPw = com.example.memoryafterservice.changePwDialog.editNewPw;
//                        EditText editNewPw2 = com.example.memoryafterservice.changePwDialog.editNewPw2;
//
//                        String currentPw = editCurrentPw.getText().toString().trim();
//                        String newPw = editNewPw.getText().toString().trim();
//                        String newPw2 = editNewPw2.getText().toString().trim();
//
//                        if (currentPw.isEmpty()) {
//                            editCurrentPw.setError("현재 비밀번호를 입력해주십시오");
//                            editCurrentPw.requestFocus();
//                            return;
//                        } else if (newPw.isEmpty()) {
//                            editNewPw.setError("새로운 비밀번호를 입력해주십시오.");
//                            editNewPw.requestFocus();
//                            return;
//                        } else if (newPw2.isEmpty()) {
//                        editNewPw2.setError("새로운 비밀번호를 다시 입력해주십시오.");
//                        editNewPw2.requestFocus();
//                        return;
//                    }
//
//                    }
//                });
//
//            }
//        });
//        alertdialog.show();
//    }
