package com.ssafy.mas.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

@Component
public class ExecuteScript {
    // 셸 파일 주소
    @Value("${instance_key.shell_path}")
    private String shell_path;
    // Hadoop cluster의 주소
    @Value("${instance_key.remote_url}")
    private String remote_url;
    // 진입하는 키 주소
    @Value("${instance_key.path}")
    private String key_path;

    // 셸 스크립트 실행
    public HashMap<String, ArrayList<Object>> run_shell(String host_dir, String result_path) {

        Process process;
        HashMap<String, ArrayList<Object>> result = new HashMap<>();
        try {
            BufferedReader in;
            String inputLine;
            // only for unix
            String command = String.format("sh %s %s %s %s %s", shell_path, key_path, remote_url, host_dir, result_path);
            process = Runtime.getRuntime().exec(command);
            // 끝날 때 까지 기다림
            process.waitFor();

            result.put("logs", new ArrayList<>());
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                result.get("logs").add(inputLine);
            }
            in.close();


            // LineCount
            result.put("LineCount", new ArrayList<>());
            in = new BufferedReader(new FileReader(host_dir + '/' + "Data_LineCount_result.csv"));
            inputLine = "";
            int count = 0;
            while ((inputLine = in.readLine()) != null) {
                if(count == 100) break;
                String[] inputSplit = inputLine.split(",");
                HashMap<String, String> cnt = new HashMap<>();
                cnt.put("hour", inputSplit[1]);
                cnt.put("count", inputSplit[2]);
                if(cnt.get("count").equals("Count") || Integer.parseInt(cnt.get("count")) == 1) continue;
                result.get("LineCount").add(cnt);
                count++;
            }
            in.close();

            // DayTalkCount
            result.put("DayTalkCount", new ArrayList<>());
            in = new BufferedReader(new FileReader(host_dir + '/' + "Data_DayTalkCount_result.csv"));
            inputLine = "";
            count = 0;
            while ((inputLine = in.readLine()) != null) {
                if(count == 100) break;
                String[] inputSplit = inputLine.split(",");
                HashMap<String, String> cnt = new HashMap<>();
                cnt.put("user_name", inputSplit[1]);
                cnt.put("count", inputSplit[2]);
                if(cnt.get("count").equals("Count") || Integer.parseInt(cnt.get("count")) == 1) continue;
                System.out.println(cnt.get("word"));
                result.get("DayTalkCount").add(cnt);
                count++;
            }
            in.close();

            // Image
            result.put("image", new ArrayList<>());
            byte[] fileContent = FileUtils.readFileToByteArray(new File(host_dir + '/' + "WordCloud.png"));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            HashMap<String, String> cnt = new HashMap<>();
            cnt.put("image", encodedString);
            result.get("image").add(cnt);

            return result;
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }
}
