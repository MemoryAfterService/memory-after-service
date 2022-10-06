package com.ssafy.mas.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        System.out.println(host_dir);
        System.out.println(result_path);

        Process process;
        HashMap<String, ArrayList<Object>> result = new HashMap<>();
        try {
            // only for unix
            String command = String.format("sh %s %s %s %s %s", shell_path, key_path, remote_url, host_dir, result_path);
            process = Runtime.getRuntime().exec(command);
            // 끝날 때 까지 기다림
            process.waitFor();

            result.put("logs", new ArrayList<>());
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                result.get("logs").add(inputLine);
            }
            in.close();

            //System.out.println(result.get(0).toString());
//            result.put("result", new ArrayList<>());
//            in = new BufferedReader(new FileReader(host_dir + '/' + result_file));
//            inputLine = "";
//            while ((inputLine = in.readLine()) != null) {
//                String[] inputSplit = inputLine.split(",");
//                HashMap<String, String> cnt = new HashMap<>();
//                cnt.put("date_time", inputSplit[1]);
//                cnt.put("user_name", inputSplit[2]);
//                cnt.put("room_name", inputSplit[3]);
//                cnt.put("word", inputSplit[4]);
//                cnt.put("count", inputSplit[5]);
//                if(cnt.get("count").equals("count") || cnt.get("word").length() == 1 || Integer.parseInt(cnt.get("count")) == 1) continue;
//                System.out.println(cnt.get("word"));
//                result.get("result").add(cnt);
//            }
//            in.close();

            return result;
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }
}
