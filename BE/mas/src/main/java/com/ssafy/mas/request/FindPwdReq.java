package com.ssafy.mas.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPwdReq {
    @Schema(description = "회원 아이디", example = "ssafyb103")
    private String userid;

    @Schema(description = "회원명", example = "김싸피")
    private String name;

    @Schema(description = "휴대전화 번호", example = "010-1234-5678")
    private String phone;
}
