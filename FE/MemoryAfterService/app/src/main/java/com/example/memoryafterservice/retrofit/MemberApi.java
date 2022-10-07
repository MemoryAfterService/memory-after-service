package com.example.memoryafterservice.retrofit;

import com.example.memoryafterservice.dto.ChangePwdReq;
import com.example.memoryafterservice.dto.FindIdReq;
import com.example.memoryafterservice.dto.FindPwdReq;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MemberApi {
    @POST("/api/member/chkid")
    Call<ResponseBody> checkDuplicatedId(@Body Map<String, Object> reqData);

    @POST("/api/member")
    Call<ResponseBody> saveMember(@Body MemberReq memberReq);

    @POST("/api/member/findid")
    Call<ResponseBody> findUserId(@Body FindIdReq findIdReq);

    @POST("/api/member/findpwd")
    Call<ResponseBody> issuingTempPwd(@Body FindPwdReq findPwdReq);

    @DELETE("/api/member/{userid}")
    Call<ResponseBody> withdrawFromMember(@Path("userid") String userid);

    @PUT("/api/member/changepwd")
    Call<ResponseBody> modifyMemberPassword(@Body ChangePwdReq changePwdReq);
}
