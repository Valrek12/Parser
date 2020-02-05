package com.opencard.SshClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.opencard.Utils.Settings;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SshCLient {
    private String host;
    private String user;
    private String password;
    private String command1="ls -ltr";
    public Channel channel;

    public SshCLient(@NotNull Settings settings) throws IOException, JSchException {
        this.user=settings.getLogin();
        this.host=settings.getServer();
        this.password=settings.getPassword();
        this.channel = SshConnection();
    }

    public Channel SshConnection() throws JSchException {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 777);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
            Channel channel=session.openChannel("exec");
            return channel;
    }
}
