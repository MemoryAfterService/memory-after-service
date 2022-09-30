package com.ssafy.mas.controller;

import com.jcraft.jsch.JSchException;
import com.ssafy.mas.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
public class FileUploadController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadTextDatas(
                                    @RequestParam(value="userid") String userid,
                                    @RequestParam(value="files") MultipartFile[] files) throws IOException, JSchException {
        System.out.println("[POST] - file");


        Map<String, Object> result = new HashMap<>();

        if (fileUploadService.saveFile(userid, files)) {
            result.put("message", SUCCESS);
        } else {
            result.put("message", FAIL);
        }

        // 파일 내용을 읽는 테스트 코드
//        System.out.println("USERID: " + userid);
//
//        for (int i = 0; i < files.length; i++) {
//            System.out.printf("File%d: %s\n", i+1, files[i].getOriginalFilename());
//        }
//
//        String fileName = files[0].getOriginalFilename(); // 파일명
//        String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length()); // 확장자
//        InputStreamReader isr = new InputStreamReader(files[0].getInputStream());
//
//        String line = null;
//        BufferedReader br = new BufferedReader(new InputStreamReader(files[0].getInputStream(), "UTF-8"));
//        while((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//        br.close();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
