package com.example.memoryafterservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.prefs.Preferences;

import javax.xml.transform.Source;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
//    private String prefName;
//    private String prefUserId;
    private EditText displayuserId;
    private EditText displayName;
    private View view;
    private ImageButton profileImage;
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
        setEvents();
        return view;
    }
    public void setEvents(){
        profileImage = view.findViewById(R.id.ProfileImageChangeButton);

//        displayuserId = view.findViewById(R.id.ProfileIdEditText);
//        displayuserId.setText(prefUserId);
//        displayName = view.findViewById(R.id.editTextTextPersonName4);
//        displayName.setText(prefName);

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
            builder.setMessage("회원탈퇴를 할 경우,\n해당 계정의 모든 데이터가 삭제됩니다\n회원탈퇴를 원하신다면\n비밀번호를 입력 후 탈퇴버튼을 눌러주십시오.");

            final EditText input = new EditText(getActivity().getApplicationContext());
            input.setHint(getString(R.string.pwEditText));
            input.setGravity(Gravity.CENTER_HORIZONTAL);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

            builder.setNegativeButton("회원탈퇴", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
                    String value = input.getText().toString();
                    value.toString();
                }
            });
            builder.setPositiveButton("취소", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
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
    };
}