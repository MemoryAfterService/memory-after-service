package com.ssafy.mas.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePwdReq {
    @Schema(description = "회원 아이디", example = "ssafyb103")
    private String userid;

    @Schema(description = "현재 비밀번호", example = "qwerty1234")
    private String curpwd;

    @Schema(description = "새 비밀번호", example = "q1w2e3r4t5y")
    private String newpwd;

    @Schema(description = "새 비밀번호 확인", example = "q1w2e3r4t5y")
    private String newpwd2;
}
