package com.ssafy.mas.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WithdrawReq {
    @Schema(description = "회원 아이디", example = "ssafyb103")
    private String userid;

    @Schema(description = "비밀번호", example = "qwerty1234")
    private String password;
}
