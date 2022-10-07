package com.ssafy.mas.controller;

import com.jcraft.jsch.JSchException;
import com.ssafy.mas.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "FileUpload API", tags = {"FileUpload"})
public class FileUploadController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "분석할 파일 업로드", notes = "분석을 파일을 업로드한다.")
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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/conn")
    @ApiOperation(value = "Shell Script 실행", notes = "업로드한 데이터를 클러스터에서 분석하기 위한 Shell Script 실행 요청")
    public ResponseEntity<Map<String, Object>> airflowConnect(){
        System.out.println("[GET] - Run shell script");

        Map<String, Object> result = new HashMap<>();

        result.put("Response", fileUploadService.runPipeline("ssafy1234", "20220929020116476"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
