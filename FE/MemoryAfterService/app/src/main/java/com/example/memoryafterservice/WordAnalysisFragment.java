package com.example.memoryafterservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;


public class WordAnalysisFragment extends Fragment {
    private View view;
    private JSONArray json;
    private ImageView imageView;

    public WordAnalysisFragment() {
        super(R.layout.fragment_word_analysis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_analysis, container, false);
        imageView = view.findViewById(R.id.WordCountImg);
        String rawJSON = getActivity().getIntent().getStringExtra("image");
        try{
            json = new JSONArray(rawJSON);
            JSONObject j = json.getJSONObject(0);
            //parse(j.getString("image"));
        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(getContext(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
//
//    public void parse(String s){
//        File temp = new File("")
//        byte[] decodedBytes = Base64.getDecoder().decode(s);
//        FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
//
//    }
}