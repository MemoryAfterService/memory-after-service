package com.ssafy.mas.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeProfileUrlReq {
    @Schema(description = "회원 아이디", example = "ssafyb103")
    private String userid;

    @Schema(description = "프로필 이미지의 URL", example = "s3.img-url.png")
    private String url;
}
