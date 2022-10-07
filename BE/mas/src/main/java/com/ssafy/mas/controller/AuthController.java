package com.ssafy.mas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.mas.request.LoginReq;
import com.ssafy.mas.response.MemberRes;
import com.ssafy.mas.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Api(value = "Auth API", tags = {"Auth"})
public class AuthController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "서비스 로그인", notes = "입력받은 회원 정보를 이용하여 서비스에 로그인한다.")
    public ResponseEntity<HashMap> login(@RequestBody LoginReq user) throws Exception {
        System.out.println("[POST] - auth/login");

        HashMap<String, Object> result = new HashMap<>();

        Map<String, Object> loginResult = authService.loginMember(user);

        if((boolean) loginResult.get("result")) {
            result.put("member", (MemberRes) loginResult.get("member"));
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
        }

//        if ((boolean) loginresult.get("result")) {
//            result = "success";
//            resultmap.put("access-token", jwtUtil.createAccessToken(user.getUserId()));
//            resultmap.put("member", (MemberRes) loginresult.get("member"));
//        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/mobile")
    @ApiOperation(value = "모바일 SMS로 인증번호 발송", notes = "입력받은 휴대전화 번호로 인증번호를 발송한다.")
    public ResponseEntity<Map<String, Object>> authenticateMobile(@RequestBody Map<String, Object> params) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        System.out.println("[POST] - /auth/mobile");

        Map<String, Object> result = new HashMap<>();
        String phone = (String) params.get("phone");

        String authCode = authService.sendAuthenticationCode(phone);

        if (authCode != null) {
            result.put("message", SUCCESS);
            result.put("authcode", authCode);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
