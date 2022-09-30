package com.ssafy.mas.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public boolean saveFile(String userid, MultipartFile[] files);
}
