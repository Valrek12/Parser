package com.opencard.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для работы с application.properties
 */
public class Settings {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
    FileInputStream fileInputStream;
    Properties prop = new Properties();

    public Properties ReadProperties() throws IOException {
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
        return prop;
    }

    public String getFile() throws IOException {
        String file = ReadProperties().getProperty("uri");
        return file;
    }

    public String getServer() throws IOException {
        String server = ReadProperties().getProperty("sever");
        return server;
    }

    public String getBaseName() throws IOException {
        String baseName = ReadProperties().getProperty("database");
        return baseName;
    }

    public String getLogin() throws IOException {
        String login = ReadProperties().getProperty("login");
        return login;
    }

    public String getPassword() throws IOException {
        String password = ReadProperties().getProperty("password");
        return password;
    }

    public String getNameFile() throws IOException {
        String name = ReadProperties().getProperty("nameFile");
        return name;
    }
}
