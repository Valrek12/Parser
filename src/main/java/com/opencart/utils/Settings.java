package com.opencart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для работы с application.properties
 */
public class Settings {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
    FileInputStream fileInputStream;
    Properties prop = new Properties();

    public Properties ReadProperties() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/application.properties");
        prop.load(inputStream);
        return prop;
    }

    /**
     * Метод получения uri из конфига
     * @return
     * @throws IOException
     */
    public String getFile() throws IOException {
        String file = ReadProperties().getProperty("uri");
        return file;
    }

    /**
     * Метод получения server из конфига
     * @return
     * @throws IOException
     */
    public String getServer() throws IOException {
        String server = ReadProperties().getProperty("sever");
        return server;
    }

    /**
     * Метод получения польхователя бд (логин) из конфига
     * @return
     * @throws IOException
     */
    public String getLogin() throws IOException {
        String login = ReadProperties().getProperty("login");
        return login;
    }

    /**
     * Метод получения password из конфига
     * @return
     * @throws IOException
     */
    public String getPassword() throws IOException {
        String password = ReadProperties().getProperty("password");
        return password;
    }

    /**
     * Метод получения имени файла из конфига(Xml)
     * @return
     * @throws IOException
     */
    public String getNameFile() throws IOException {
        String name = ReadProperties().getProperty("nameFile");
        return name;
    }

    /**
     * Метод получения драйвера для бд из конфига
     * @return
     * @throws IOException
     */
    public String getDriver() throws IOException {
        String driver = ReadProperties().getProperty("driver");
        return driver;
    }

    /**
     * Метод получения относителбного пути для картинки из конфига
     * @return
     * @throws IOException
     */
    public String getSourcePath() throws IOException {
        String path = ReadProperties().getProperty("imagePath");
        return path;
    }

    /**
     * Метод получения полного пути для картинки из конфига
     * @return
     * @throws IOException
     */
    public String getCanonicalPath() throws  IOException{
        String path = ReadProperties().getProperty("imageCanonicalPath");
        return path;
    }
}
