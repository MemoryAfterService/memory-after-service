// 현재 사용하지 않는 컴포넌트

package com.ssafy.mas.util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SSHUtils {
    @Value("${ssh-service.username}")
    private String username;
    @Value("${ssh-service.host}")
    private String host;
    @Value("${ssh-service.port}")
    private int port;

    private Session session;
    private ChannelExec channelExec;

    public void connectSSH() throws JSchException {
        System.out.printf("%s %s %d\n", username, host, port);
        session = new JSch().getSession(username, host, port);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
    }

    public void disConnectSSH() {
        if(session != null) session.disconnect();
        if(channelExec != null) channelExec.disconnect();
    }
}
