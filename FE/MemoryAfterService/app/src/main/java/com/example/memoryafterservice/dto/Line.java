package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Line {
    String date;
    public String name;
    String room_name;
    public String hour;
    public int count;

    public Line(String date, String name, String room_name, String hour, int count) {
        this.date = date;
        this.name = name;
        this.room_name = room_name;
        this.hour = hour;
        this.count = count;
    }
}
