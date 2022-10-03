package com.ssafy.mas.service;

import com.ssafy.mas.database.entity.Member;
import com.ssafy.mas.database.repository.MemberRepository;
import com.ssafy.mas.database.repository.UpdateLogRepository;
import com.ssafy.mas.util.RestAPI;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;


@Service("fileUploadService")
@RequiredArgsConstructor
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    UpdateLogRepository updateLogRepository;

    @Autowired
    RestAPI restAPI;

    @Override
    public boolean saveFile(String userid, MultipartFile[] files) {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String homePath = System.getProperty("user.home");
        String fileSeperator = File.separator; // Windows: \\, linux: /

        String storagePath = null;
        if("windows 10".equals(osName)) {
            storagePath = homePath + fileSeperator + "Desktop" + fileSeperator + "storage";
        } else if("linux".equals(osName)) {
            storagePath = homePath + fileSeperator + "upload";
        } else {
            System.out.println("This OS is not supported.");
            return false;
        }
        File storage = new File(storagePath);
        LocalDateTime currentTime = LocalDateTime.now();
        String curTimeString = currentTime.toString().replaceAll("[^0-9]+", "");

        if(!storage.exists()) storage.mkdirs();

        String savePath = storagePath + fileSeperator + userid + fileSeperator + curTimeString;
        storage = new File(savePath);
        if(!storage.exists()) storage.mkdirs();

        for (int i = 0; i < files.length; i++) {
            String originalFileName = files[i].getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String fileType = null;

            if("zip".equals(ext)) {
                fileType = "kakaotalk";
            } else if("txt".equals(ext)) {
                fileType = "sms";
            } else {
                System.out.println("WRONG FILE EXT");
                return false;
            }

            String newFileName = fileType + "." + ext;
            File changeFile = new File(savePath + fileSeperator + newFileName);

            // File upload
            try {
                files[i].transferTo(changeFile);
                System.out.printf("[%s] - File upload is complete. The file is in %s\n", newFileName, savePath);

            } catch (IllegalStateException | IOException e) {
                System.out.printf("[%s] - File upload failed.\n", newFileName);
                e.printStackTrace();
                return false;
            }
        }
        Member findMember = memberRepository.findFirstByUserIdAndWithdrawal(userid, false);
        updateLogRepository.updateProfileUploadLog(findMember, currentTime.toLocalDate());

        // 파일 전송이 완료되어야 airflow 실행 요청
        // AIRFLOW TRIGGER //

        return true;
    }

    @Override
    public JSONObject runPipeline() {
        String res = restAPI.runDAG().getBody();
        Object obj = JSONValue.parse(res);
        JSONObject json = (JSONObject) obj;
        return json;
    }
}
