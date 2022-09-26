package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberReq {
    private String userid;
    private String password;
    private String name;
    private String phone;

    public MemberReq(String userid, String password, String name, String phone) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}