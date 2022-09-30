package com.ssafy.mas.response;

import lombok.*;

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
}
