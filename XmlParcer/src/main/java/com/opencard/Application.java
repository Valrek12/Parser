package com.opencard;

import com.opencard.Dao.Category;
import com.opencard.Utils.Settings;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws  IOException {
        Settings settings = new Settings();
        Base.open(settings.getDriver(), settings.getServer(), settings.getLogin(),settings.getPassword());
        System.out.println(Base.findAll("select * from oc3_category "));
        LazyList<Model> categoryDao = Category.find("category_id = ?", 1);

        System.out.println(categoryDao);
    }


}
