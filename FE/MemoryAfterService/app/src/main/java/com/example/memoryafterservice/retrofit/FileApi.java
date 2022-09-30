package com.example.memoryafterservice.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface FileApi {
    @Multipart
    @POST("/api/file/upload")
    Call<ResponseBody> uploadTextDatas(
            @Part("userid") RequestBody userid,
            @Part MultipartBody.Part files);
}
