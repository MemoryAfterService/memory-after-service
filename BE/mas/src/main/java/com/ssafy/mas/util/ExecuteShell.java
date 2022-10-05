package com.ssafy.mas.util;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
public class ExecuteShell {
    // 셸 파일 주소
    @Value("${instance_key.shell_path}")
    private String shell_path;
    // 보낼 데이터 주소
//    // 보낼 데이터 파일 -> 인자로 받기
//    private String data_name = "kakaotalk.zip";
//    // EC2 인스턴스의 파일 주소 -> 인자로 받기
//    private String host_dir = "/Users/wondae";
//    // Hadoop Cluster의 디렉토리 -> host와 짝으로 맞추기
//    private String result_path = "/home/j7b103/word";
//    // 결과 파일 이름 -> cluster의 Python script로 맞추기
//    private String result_file = "finaldataframe.csv";
    // Hadoop cluster의 주소
    @Value("${instance_key.remote_url}")
    private String remote_url;
    // 진입하는 키 주소
    @Value("${instance_key.path}")
    private String key_path;

    // 셸 스크립트 실행
    public ArrayList<ArrayList<String>> run_shell(String host_dir, String data_name, String result_path, String result_file) {
        String homeDirectory = System.getProperty("user.home");
        Process process;
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            // only for unix
            String command = String.format("sh %s %s %s %s %s %s %s", shell_path, key_path, remote_url, host_dir, data_name, result_path, result_file);
            process = Runtime.getRuntime().exec(command);
            // 끝날 때 까지 기다림
            process.waitFor();

            result.add(new ArrayList<>());
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                result.get(0).add(inputLine);
            }
            in.close();

            System.out.println(result.get(0).toString());
//            result.add(new ArrayList<>());
//            in = new BufferedReader(new FileReader(host_dir + '/' + result_file));
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//                result.get(1).add(inputLine);
//            }
//            in.close();

            return result;
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }
}
