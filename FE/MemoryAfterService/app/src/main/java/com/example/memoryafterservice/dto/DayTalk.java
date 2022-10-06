package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayTalk {
    public String name;
    public String room_name;
    public int count;

    public DayTalk(String name, String room_name, int count) {
        this.name = name;
        this.room_name = room_name;
        this.count = count;
    }
}
