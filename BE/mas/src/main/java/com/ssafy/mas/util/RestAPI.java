package com.ssafy.mas.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mas.request.AirflowReqDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class RestAPI {
    @Value("${airflow-conf.url}")
    private String BASE_URL;
    @Value("${airflow-conf.userid}")
    private String userId;
    @Value("${airflow-conf.password}")
    private String password;

    // 연결 테스트 -> GET
    public ResponseEntity<String> runDAG(){
        // 조금 더 빼고 싶다
        String url = "/api/v1/dags/MAS/dagRuns";

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(userId, password);
            headers.setContentType(MediaType.APPLICATION_JSON);
            String now = new Date().toString();
            AirflowReqDTO airflowReqDTO = AirflowReqDTO.builder().dag_run_id(now).build();
            String body = new ObjectMapper().writeValueAsString(airflowReqDTO);

            HttpEntity<String> requestEntity = new HttpEntity(body, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + url, requestEntity, String.class);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
