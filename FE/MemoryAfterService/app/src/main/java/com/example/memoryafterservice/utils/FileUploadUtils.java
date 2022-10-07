package com.example.memoryafterservice.utils;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.http.Body;

public class FileUploadUtils {
    public static void sendToServer(File file, String userid) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", file.getName(), RequestBody.create(MultipartBody.FORM, file))
                .addFormDataPart("userid", userid)
                .build();


    }
}
