package com.opencart.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javalite.activejdbc.Base;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

public class DbConnector {
    private Settings settings;
    private static final Logger logger = LogManager.getLogger();


    @Contract(pure = true)
    public DbConnector(){
        this.settings = new Settings();
    }

    public void ConnectionOpen() throws IOException {
        try{
            Base.open(settings.getDriver(), settings.getServer(), settings.getLogin(),settings.getPassword());
            logger.debug(String.format("соединение c ceрвером базы данных - %s успешно уставновлено", settings.getServer()));
        }catch (Exception ex){
            logger.error("Ошибка соединения с бд, подробности: ", ex.toString());
        }
    }

    public void ConnectionClose(){
        Base.close();
    }
}
