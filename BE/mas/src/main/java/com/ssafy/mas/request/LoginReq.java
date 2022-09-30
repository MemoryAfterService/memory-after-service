package com.ssafy.mas.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginReq {
    @Schema(description = "회원 아이디", example = "ssafyb103")
    String userid;

    @Schema(description = "아이디", example = "ssafyb103")
    String password;
}
