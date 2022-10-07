package com.ssafy.mas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.mas.request.LoginReq;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface AuthService {

    public Map<String, Object> loginMember(LoginReq user) throws Exception;
    public String sendAuthenticationCode(String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException;
}
