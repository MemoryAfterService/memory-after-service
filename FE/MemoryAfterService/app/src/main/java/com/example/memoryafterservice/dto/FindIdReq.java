package com.example.memoryafterservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindIdReq {
    private String name;
    private String phone;

    public FindIdReq(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
