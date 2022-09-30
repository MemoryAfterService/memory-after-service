package com.example.memoryafterservice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class UploadFragment extends Fragment {
    private Button next;
    private View view;
    public UploadFragment() {
        // Required empty public constructor
        super(R.layout.fragment_upload);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upload, container, false);
        next = view.findViewById(R.id.UploadMainNextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(getActivity(), "다음은 없다", Toast.LENGTH_LONG);
                t.show();
            }
        });

        return view;
    }
}