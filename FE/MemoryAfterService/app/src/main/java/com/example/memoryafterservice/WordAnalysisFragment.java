package com.example.memoryafterservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WordAnalysisFragment extends Fragment {
    private View view;
    private JSONObject json;

    public WordAnalysisFragment() {
        super(R.layout.fragment_word_analysis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_analysis, container, false);
        String rawJSON = getActivity().getIntent().getStringExtra("result");
        try{
            json = new JSONObject(rawJSON);

        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(getContext(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
        }



        return view;
    }
}