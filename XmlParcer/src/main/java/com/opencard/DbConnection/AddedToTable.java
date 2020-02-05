package com.opencard.DbConnection;

import com.jcraft.jsch.JSchException;
import com.opencard.SshClient.SshCLient;
import com.opencard.Utils.Settings;

import java.io.IOException;
import java.util.Date;

public class AddedToTable {

    public void Run() throws JSchException, IOException {
        Settings settings = new Settings();
        SshCLient sshCLient = new SshCLient(settings);
        sshCLient.channel.connect();
        TableCategories categories = new TableCategories(1,0, new Date(), new Date());
        WebController webController = new WebController();
        webController.process(categories);
        System.out.println("added category");
        sshCLient.channel.disconnect();
        System.out.println("connected closed");

    }
}
