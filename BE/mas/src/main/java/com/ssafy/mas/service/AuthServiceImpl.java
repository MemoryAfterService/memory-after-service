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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @PersistenceContext
    EntityManager em;

    @Override
    public Map<String, Object> loginMember(LoginReq user) throws Exception {
        Member findMember = memberRepository.findFirstByUserId(user.getUserid());
        HashMap<String, Object> result = new HashMap<>();

        if (findMember != null && !findMember.isWithdrawal() && passwordEncoder.matches(user.getPassword(), findMember.getPassword())) {
            System.out.println("LOGIN SUCCESS");

            Member join = em.find(Member.class, findMember.getId());
            List<String> bookmarks = new ArrayList<>();
            for (int i = 0; i < join.getBookmarks().size(); i++) {
                bookmarks.add(join.getBookmarks().get(i).getSmsNumber());
            }

            MemberRes member = MemberRes.builder()
                    .id(join.getId())
                    .userid(join.getUserId())
                    .name(join.getName())
                    .phone(aes256.decrypt(join.getPhone()))
                    .profileUrl(join.getProfileUrl())
                    .regDate(join.getRegDate().toLocalDate().toString())
                    .bookmark(bookmarks)
                    .updatelog(new MemberRes.UpdateLog(join.getUpdateLog().getLastUploadDate(), join.getUpdateLog().getCount()))
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
