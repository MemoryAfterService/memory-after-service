package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Line {
    public String hour;
    public int count;

    public Line(String hour, int count) {
        this.hour = hour;
        this.count = count;
    }
}
