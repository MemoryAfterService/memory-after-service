package com.ssafy.mas.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mas.request.MessageDTO;
import com.ssafy.mas.request.SmsReqDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NCloudSENSUtil {
    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String senderPhone;

    public String makeSignature(Long currentTime) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String space = " ";                                    // one space
        String newLine = "\n";                                // new line
        String method = "POST";                                // method
        String url = "/sms/v2/services/" + this.serviceId + "/messages";    // url (include query string)
        String timestamp = currentTime.toString();  // current timestamp (epoch)
        String accessKey = this.accessKey;                    // access key id (from portal or Sub Account)
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    public boolean sendSms(MessageDTO messageDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        Long currentTime = System.currentTimeMillis();
        List<MessageDTO> messages = new ArrayList<>();
        messages.add(messageDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", currentTime.toString());
        headers.set("x-ncp-iam-access-key", this.accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(currentTime));

        SmsReqDTO request = SmsReqDTO.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(this.senderPhone)
                .content(messageDto.getContent())
                .messages(messages)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        HttpEntity<String> requestBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" +
                this.serviceId + "/messages"), requestBody, SmsReqDTO.class);

        return true;
    }
}
