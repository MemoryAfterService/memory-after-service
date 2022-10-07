package com.ssafy.mas.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirflowReqDTO {
    String dag_run_id;
}
