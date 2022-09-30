package com.ssafy.mas.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
public class MemberReq {
    @Schema(description = "회원 아이디", example = "ssafyb103")
    private String userid;

    @Schema(description = "비밀번호", example = "qwerty1234")
    private String password;

    @Schema(description = "회원명", example = "김싸피")
    private String name;

    @Schema(description = "휴대전화 번호", example = "010-1234-5678")
    private String phone;
}
