package com.example.memoryafterservice.retrofit;

import com.example.memoryafterservice.dto.FindPwdReq;
import com.example.memoryafterservice.dto.LoginReq;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AuthApi {
    @POST("/api/auth/login")
    Call<ResponseBody> login(@Body LoginReq loginReq);

    @POST("/api/auth/mobile")
    Call<ResponseBody> authenticateMobile(@Body Map<String, Object> phone);
}
