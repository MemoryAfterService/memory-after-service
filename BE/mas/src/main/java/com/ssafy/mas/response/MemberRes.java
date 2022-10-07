package com.ssafy.mas.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRes {
    private Long id;
    private String userid;
    private String name;
    private String phone;
    private String profileUrl;
    private String regDate;
    private List<String> bookmark;
    private UpdateLog updatelog;

    @Data
    @AllArgsConstructor
    public static class UpdateLog {
        private LocalDate date;
        private int count;
    }
}
