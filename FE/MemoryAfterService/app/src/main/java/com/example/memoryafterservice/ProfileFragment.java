package com.example.memoryafterservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
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

        termOfUse = view.findViewById(R.id.ProfileTOULayout);
        termOfUse.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TermOfUseActivity.class);
            intent.putExtra("loggedIn", true);
            startActivity(intent);
        });

        withdrawal = view.findViewById(R.id.ProfileSignoutLayout);

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