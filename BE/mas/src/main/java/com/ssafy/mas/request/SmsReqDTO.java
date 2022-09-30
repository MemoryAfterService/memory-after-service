package com.ssafy.mas.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsReqDTO {
    String type;
    String contentType;
    String countryCode;
    String from;
    String content;
    List<MessageDTO> messages;
}
