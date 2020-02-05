package com.opencard;

import com.jcraft.jsch.JSchException;
import com.opencard.DbConnection.AddedToTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, JSchException {
        SpringApplication.run(Application.class, args);
        AddedToTable addedToTable = new AddedToTable();
        addedToTable.Run();

    }
}
