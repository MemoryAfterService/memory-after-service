package com.example.memoryafterservice.retrofit;

import com.example.memoryafterservice.dto.LoginReq;
import com.example.memoryafterservice.dto.MemberReq;
import com.example.memoryafterservice.dto.MemberRes;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MemberApi {
    @POST("/api/member/chkid")
    Call<ResponseBody> checkDuplicatedId(@Body Map<String, Object> reqData);

    @POST("/api/member")
    Call<ResponseBody> saveMember(@Body MemberReq memberReq);

    @DELETE("/api/member/{userid}")
    Call<ResponseBody> withdrawFromMember(
            @Path("userid") String userid);

    @POST("/api/auth/login")
    Call<ResponseBody> login(@Body LoginReq loginReq);
}
