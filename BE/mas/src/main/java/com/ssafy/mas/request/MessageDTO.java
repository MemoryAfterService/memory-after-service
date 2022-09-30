package com.ssafy.mas.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    String to;
    String content;
}
