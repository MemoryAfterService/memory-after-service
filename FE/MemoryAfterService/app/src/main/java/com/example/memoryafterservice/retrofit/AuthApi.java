package com.example.memoryafterservice.retrofit;

import com.example.memoryafterservice.dto.LoginReq;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/api/auth/login")
    Call<ResponseBody> login(@Body LoginReq loginReq);
}
