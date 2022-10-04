package com.example.memoryafterservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPwdReq {
    private String userid;
    private String name;
    private String phone;

    public FindPwdReq(String userid, String name, String phone) {
        this.userid = userid;
        this.name = name;
        this.phone = phone;
    }
}
