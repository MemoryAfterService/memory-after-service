package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    String userid;
    String password;

    public LoginReq(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}