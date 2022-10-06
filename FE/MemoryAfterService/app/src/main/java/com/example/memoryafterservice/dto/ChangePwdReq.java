package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePwdReq {
    private String userid;
    private String curpwd;
    private String newpwd;
    private String newpwd2;

    public ChangePwdReq(String userid, String curpwd, String newpwd, String newpwd2) {
        this.userid = userid;
        this.curpwd = curpwd;
        this.newpwd = newpwd;
        this.newpwd2 = newpwd2;
    }
}