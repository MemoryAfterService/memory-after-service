package com.ssafy.mas.controller;

import com.ssafy.mas.request.BookmarkReq;
import com.ssafy.mas.service.BookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookmark")
@Api(value = "Auth API", tags = {"Bookmark"})
public class BookmarkController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    BookmarkService bookmarkService;

    @PostMapping()
    @ApiOperation(value = "최근 입력한 카드사 번호 저장", notes = "최근 입력한 카드사 번호를 데이터베이스에 저장한다.")
    public ResponseEntity<Map<String, Object>> bookmarkCardSMSNumbers(@RequestBody BookmarkReq bookmarkReq) throws Exception {
        System.out.println("[POST] - /bookmark " + bookmarkReq);

        Map<String, Object> result = new HashMap<>();

        List<String> savedNumbers = bookmarkService.saveNumbers(bookmarkReq);

        if (savedNumbers != null) {
            result.put("message", SUCCESS);
            result.put("bookmark", savedNumbers);
        } else {
            result.put("message", FAIL);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
