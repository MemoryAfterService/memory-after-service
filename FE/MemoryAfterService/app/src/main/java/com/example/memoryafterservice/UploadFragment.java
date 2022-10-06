package com.example.memoryafterservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.memoryafterservice.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFragment extends Fragment {
    private Button next;
    private ImageButton upload;
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
        upload = view.findViewById(R.id.UploadKakaoBtn);
        next.setOnClickListener(view -> {
            Toast t = Toast.makeText(getActivity(), "다음은 없다", Toast.LENGTH_LONG);
            t.show();
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getFileApi()
                        .uploadKakao();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String responseBody = null;
                        JSONObject json = null;
                        String message = null;
                        try {
                            responseBody = response.body().string();
                            json = new JSONObject(responseBody);
                            //message = json.getString("Success")

                            json = (JSONObject) json.get("Response");
                            json = (JSONObject) json.get("response");

//                            // save file internal storage
//                            FileOutputStream fos = getActivity().openFileOutput("result.json", Context.MODE_PRIVATE);
//                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//
//                            bw.write(json.toString());
//
//                            bw.close();

                            Toast.makeText(getContext(), "업로드 성공", Toast.LENGTH_SHORT).show();

                            Intent indent = new Intent(getActivity(), AnalysisActivity.class);
                            indent.putExtra("result", json.toString());

                            getActivity().startActivity(indent);

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
}