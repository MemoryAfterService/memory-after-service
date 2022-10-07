package com.ssafy.mas.controller;

import com.ssafy.mas.request.*;
import com.ssafy.mas.response.MemberRes;
import com.ssafy.mas.service.MemberService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
@Api(value = "Member API", tags = {"Member"})
public class MemberController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String DUPLICATED = "duplicated";
    private static final String NONDUPLICATED = "nonduplicated";

    @Autowired
    MemberService memberService;

    @PostMapping("/chkid")
    @ApiOperation(value = "아이디 사용 여부 확인", notes = "입력받은 아이디가 사용 중인지 확인한다.")
    @ApiImplicitParam(name = "inputid", value = "확인할 유저 아이디", required = true, dataType = "string", paramType = "body")
    public ResponseEntity<Map<String, Object>> checkDuplicatedID(@ApiIgnore @RequestBody Map<String, Object> reqData) {
        System.out.println("[POST] - /member/chkid");
        String inputId = (String) reqData.get("inputid");

        Map<String, Object> result = new HashMap<>();

        if (memberService.checkID(inputId)) {
            result.put("message", DUPLICATED);
        } else {
            result.put("message", NONDUPLICATED);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "회원 정보 등록", notes = "입력받은 회원의 정보를 데이터베이스에 저장한다.")
    public ResponseEntity<Map<String, Object>> registerMember(@RequestBody MemberReq memberReq) throws Exception {
        System.out.println("[POST] - /member " + memberReq);

        Map<String, Object> result = new HashMap<>();

        if (memberService.saveMember(memberReq)) {
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{userid}")
    @ApiOperation(value = "회원 정보 조회", notes = "아이디와 일치하는 회원의 정보를 반환한다.")
    public ResponseEntity<Map<String, Object>> getMemberInfo(@PathVariable String userid) throws Exception {
        System.out.println("[GET] - /member/{user_id}");

        Map<String, Object> result = new HashMap<>();
        MemberRes member = memberService.findMemberInfo(userid);

        if (member != null) {
            result.put("message", SUCCESS);
            result.put("data", member);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/findid")
    @ApiOperation(value = "회원 아이디 찾기", notes = "회원의 이름과 휴대전화 번호로 아이디를 찾아 반환한다.")
    public ResponseEntity<Map<String, Object>> findID(@RequestBody FindIdReq findIdReq) throws Exception {
        System.out.println("[POST] - /member/findid " + findIdReq);

        Map<String, Object> result = new HashMap<>();
        String userid = memberService.findID(findIdReq);

        if (userid != null) {
            result.put("userid", userid);
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/findpwd")
    @ApiOperation(value = "임시 비밀번호 발급", notes = "임시 비밀번호로 재설정하고 SMS로 전송한다.")
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody FindPwdReq findPwdReq) throws Exception {
        System.out.println("[POST] - /member/findpwd " + findPwdReq);

        Map<String, Object> result = new HashMap<>();

        if (memberService.findPassword(findPwdReq)) {
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/changeimg")
    @ApiOperation(value = "프로필 이미지 URL 변경", notes = "회원의 프로필 이미지 URL을 변경한다.")
    public ResponseEntity<Map<String, Object>> updateMemberProfileImage(@RequestBody ChangeProfileUrlReq changeProfileUrlReq) {
        System.out.println("[PUT] - /member/changeimg" + changeProfileUrlReq);

        Map<String, Object> result = new HashMap<>();

        if (memberService.updateMemberImage(changeProfileUrlReq)) {
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/changepwd")
    @ApiOperation(value = "회원 비밀번호 변경", notes = "회원의 비밀번호를 변경한다.")
    public ResponseEntity<Map<String, Object>> modifyMemberPassword(@RequestBody ChangePwdReq changePwdReq) {
        System.out.println("[PUT] - /member/changepwd" + changePwdReq);

        Map<String, Object> result = new HashMap<>();
        String description = memberService.updateMemberPassword(changePwdReq);

        if (SUCCESS.equals(description)) {
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
            result.put("description", description);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "회원 탈퇴", notes = "서비스에서 회원을 탈퇴시킨다.")
    public ResponseEntity<Map<String, Object>> withdrawFromMember(@RequestBody WithdrawReq withdrawReq) {
        System.out.println("[DELETE] - /member" + withdrawReq);

        Map<String, Object> result = new HashMap<>();
        String description = memberService.deleteMember(withdrawReq);

        if (SUCCESS.equals(description)) {
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
            result.put("description", description);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
