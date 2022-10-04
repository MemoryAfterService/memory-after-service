package com.ssafy.mas.service;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean saveFile(String userid, MultipartFile[] files);

    JSONObject runPipeline();
}
