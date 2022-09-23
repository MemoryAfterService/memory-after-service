package com.example.memoryafterservice.retrofit;

import com.example.memoryafterservice.dto.MemberReq;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MemberApi {
    @POST("/api/member")
    Call<MemberReq> save(@Body MemberReq memberReq);
}
