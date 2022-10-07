package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberRes {
    private Long id;
    private String userid;
    private String name;
    private String phone;
    private String profileUrl;
    private String regDate;
}