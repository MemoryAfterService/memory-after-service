package com.ssafy.mas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.mas.database.entity.Member;
import com.ssafy.mas.database.repository.MemberRepository;
import com.ssafy.mas.request.LoginReq;
import com.ssafy.mas.request.MessageDTO;
import com.ssafy.mas.response.MemberRes;
import com.ssafy.mas.util.AES256;
import com.ssafy.mas.util.NCloudSENSUtil;
import com.ssafy.mas.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service("authService")
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AES256 aes256;

    @Autowired
    RandomStringGenerator randomStringGenerator;

    @Autowired
    NCloudSENSUtil nCloudSENSUtil;

//    @Value("${security-aes256.key}")
//    private String key;

    @Override
    public Map<String, Object> loginMember(LoginReq user) throws Exception {
        Member findMember = memberRepository.findFirstByUserId(user.getUserid());
        HashMap<String, Object> result = new HashMap<>();

        if (findMember != null && !findMember.isWithdrawal() && passwordEncoder.matches(user.getPassword(), findMember.getPassword())) {
            System.out.println("CORRECT");
            MemberRes member = MemberRes.builder()
                    .id(findMember.getId())
                    .userid(findMember.getUserId())
                    .name(findMember.getName())
                    .phone(aes256.decrypt(findMember.getPhone()))
                    .profileUrl(findMember.getProfileUrl())
                    .regDate(findMember.getRegDate().toLocalDate().toString())
                    .build();
            result.put("member", member);
            result.put("result", true);

            return result;
        }
        System.out.println("LOGIN FAIL");
        result.put("result", false);
        return result;
    }

    @Override
    public String sendAuthenticationCode(String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        String authCode = randomStringGenerator.generateRandomAuthCode(6);
        System.out.println("AUTH-CODE : " + authCode);

        MessageDTO messageDto = new MessageDTO();
        messageDto.setTo(phone.replaceAll("-", ""));
        messageDto.setContent(String.format("[MAS] 인증번호는 %s입니다.", authCode));

        if(nCloudSENSUtil.sendSms(messageDto)) return authCode;
        else return null;
    }
}
