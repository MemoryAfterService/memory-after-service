package com.ssafy.mas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.mas.request.*;
import com.ssafy.mas.response.MemberRes;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface MemberService {
    public boolean checkID(String inputId);
    public boolean saveMember(MemberReq memberReq) throws Exception;
    public MemberRes findMemberInfo(String userId) throws Exception;
    public String findID(FindIdReq findIdReq) throws Exception;
    public boolean findPassword(FindPwdReq findPwdReq) throws Exception;
    public boolean updateMemberImage(ChangeProfileUrlReq changeProfileUrlReq);
    public String updateMemberPassword(ChangePwdReq changePwdReq);
    public String deleteMember(WithdrawReq withdrawReq);
}
