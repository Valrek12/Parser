package com.opencard.Utils;

import org.javalite.activejdbc.Base;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

public class DbConnector {
    private Settings settings;

    @Contract(pure = true)
    public DbConnector(){
        this.settings = new Settings();
    }

    public void ConnectionOpen() throws IOException {
        Base.open(settings.getDriver(), settings.getServer(), settings.getLogin(),settings.getPassword());
    }

    public void ConnectionClose(){
        Base.close();
    }
}
