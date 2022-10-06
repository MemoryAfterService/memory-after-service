package com.example.memoryafterservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Word {
    String date;
    public String name;
    String room_name;
    public String word;
    public int count;

    public Word(String date, String name, String room_name, String word, int count) {
        this.date = date;
        this.name = name;
        this.room_name = room_name;
        this.word = word;
        this.count = count;
    }
}
