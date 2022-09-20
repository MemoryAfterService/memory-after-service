package com.example.memoryafterservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private View view;
    private ImageButton elephant;
    private Button moveToCalendar;
    private Button moveToUpload;
    private BottomNavigationView bottomNavigationView;

    public HomeFragment() {
        // Required empty public constructor
        super(R.layout.fragment_home);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setEvents();
        return view;
    }

    public void setEvents(){
        elephant = view.findViewById(R.id.HomeMascotButton);
        elephant.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), HomeDetailAnalysisActivity.class);
            startActivity(intent);
        });

        moveToCalendar = view.findViewById(R.id.HomeCalendarButton);
        moveToCalendar.setOnClickListener(view -> {
            ((HomeActivity)getActivity()).moveToFragment(R.id.calendar);
        });

        moveToUpload = view.findViewById(R.id.HomeUploadButton);
        moveToUpload.setOnClickListener(view -> {
            ((HomeActivity)getActivity()).moveToFragment(R.id.upload);
        });
    }
}