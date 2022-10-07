package com.ssafy.mas.service;

import com.ssafy.mas.database.entity.Member;
import com.ssafy.mas.database.entity.UpdateLog;
import com.ssafy.mas.database.repository.MemberRepository;
import com.ssafy.mas.database.repository.UpdateLogRepository;
import com.ssafy.mas.request.*;
import com.ssafy.mas.response.MemberRes;
import com.ssafy.mas.util.AES256;
import com.ssafy.mas.util.NCloudSENSUtil;
import com.ssafy.mas.util.RandomStringGenerator;
import com.ssafy.mas.util.ValidationChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("memberService")
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    UpdateLogRepository updateLogRepository;

    @Autowired
    ValidationChecker validationChecker;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AES256 aes256;

    @Autowired
    RandomStringGenerator randomStringGenerator;

    @Autowired
    NCloudSENSUtil nCloudSENSUtil;

    @Override
    public boolean checkID(String inputId) {
        return memberRepository.findFirstByUserId(inputId) != null;
    }

    @Override
    public boolean saveMember(MemberReq memberReq) throws Exception {
        if(!validationChecker.idValidationCheck(memberReq.getUserid())) return false;
        if(!validationChecker.pwdValidationCheck(memberReq.getPassword())) return false;

        Member member = Member.builder()
                .userId(memberReq.getUserid())      // 아이디
                .password(passwordEncoder.encode(memberReq.getPassword()))  // 패스워드
                .name(memberReq.getName())          // 사용자명
                .phone(aes256.encrypt(memberReq.getPhone()))        // 휴대전화 번호
                .regDate(LocalDateTime.now())       // 가입일
                .build();

        UpdateLog updateLog = UpdateLog.builder()
                .member(member)
                .count(0)
                .build();
        try {
            memberRepository.save(member);
            updateLogRepository.save(updateLog);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public MemberRes findMemberInfo(String userId) throws Exception {
        Member findMember = memberRepository.findFirstByUserIdAndWithdrawal(userId, false);
        if(findMember != null) {
            MemberRes result = MemberRes.builder()
                    .id(findMember.getId())
                    .userid(findMember.getUserId())
                    .name(findMember.getName())
                    .phone(aes256.decrypt(findMember.getPhone()))
                    .profileUrl(findMember.getProfileUrl())
                    .regDate(findMember.getRegDate().toLocalDate().toString())
                    .build();
            System.out.println(result);

            return result;
        }
        return null;
    }

    @Override
    public String findID(FindIdReq findIdReq) throws Exception {
        String name = findIdReq.getName();
        String phone = findIdReq.getPhone();
        Member member = memberRepository.findByNameAndPhone(name, aes256.encrypt(phone));

        if (member != null) return member.getUserId();
        else return null;
    }

    @Override
    public boolean findPassword(FindPwdReq findPwdReq) throws Exception {
        String userId = findPwdReq.getUserid();
        String name = findPwdReq.getName();
        String phone = findPwdReq.getPhone();
        String randomPwd = randomStringGenerator.generateRandomPassword(10);
        System.out.println("RAND PWD " + randomPwd);

        int result = memberRepository.updateRandomPassword(userId, name, aes256.encrypt(phone), passwordEncoder.encode(randomPwd));

        // 임시 비밀번호 변경 성공 시 사용자 휴대전화로 SMS 전송
        if (result > 0) {
            MessageDTO messageDto = new MessageDTO();
            messageDto.setTo(phone.replaceAll("-", ""));
            messageDto.setContent(String.format("[MAS] %s님의 임시비밀번호는 %s입니다.", name, randomPwd));
            nCloudSENSUtil.sendSms(messageDto);
            System.out.println("비밀번호 변경 성공");
            return true;
        } else {
            System.out.println("비밀번호 변경 실패");
            return false;
        }
    }

    @Override
    public boolean updateMemberImage(ChangeProfileUrlReq changeProfileUrlReq) {
        String userId = changeProfileUrlReq.getUserid();
        String url = changeProfileUrlReq.getUrl();
        int result = memberRepository.updateProfileImageUrl(userId, url);
        return result > 0;
    }

    @Override
    public String updateMemberPassword(ChangePwdReq changePwdReq) {
        String userId = changePwdReq.getUserid();
        String currentPwd = changePwdReq.getCurpwd();
        String newPwd = changePwdReq.getNewpwd();
        String newPwd2 = changePwdReq.getNewpwd2();

        Member findMember = memberRepository.findFirstByUserId(userId);

        if(!passwordEncoder.matches(currentPwd, findMember.getPassword())) { // 현재 비밀번호 확인
            return "현재 비밀번호가 일치하지 않습니다.";
        }
        if(!newPwd.equals(newPwd2)) { // 새로 사용할 비밀번호 일치 여부 검사
            return "새로 입력한 비밀번호가 일치하지 않습니다.";
        }
        if(!validationChecker.pwdValidationCheck(newPwd)) { // 새 비밀번호 유효성 검사
            return "비밀번호는 8자 이상의 알파벳, 숫자 조합으로 이루어져야 합니다.";
        }
        if(passwordEncoder.matches(newPwd, findMember.getPassword())) { // 현재 비밀번호와 새 비밀번호 일치 여부 검사
            return "새로 입력한 비밀번호가 현재 사용하고 있는 비밀번호와 동일합니다.";
        }

        int result = memberRepository.updatePassword(passwordEncoder.encode(newPwd), userId);

        return result > 0 ? "success" : "fail";
    }

    @Override
    public String deleteMember(WithdrawReq withdrawReq) {
        String userId = withdrawReq.getUserid();
        String password = withdrawReq.getPassword();

        Member findMember = memberRepository.findFirstByUserId(userId);

        if(!passwordEncoder.matches(password, findMember.getPassword())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        int result = memberRepository.updateIsDeleted(userId);
        return result > 0 ? "success" : "fail";
    }
}
