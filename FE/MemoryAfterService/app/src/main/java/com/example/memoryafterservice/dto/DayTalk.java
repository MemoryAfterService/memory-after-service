package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayTalk {
    public String name;
    public int count;

    public DayTalk(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
