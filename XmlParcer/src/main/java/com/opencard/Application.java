package com.opencard;

import com.opencard.Dao.Category;
import com.opencard.Utils.Settings;
import org.javalite.activejdbc.Base;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws  IOException {
        Settings settings = new Settings();
        Base.open(settings.getDriver(), settings.getServer(), settings.getLogin(),settings.getPassword());
        System.out.println(Base.findAll("select * from oc3_category "));
        Category category = new Category();
        category.setCategoryId(1);
        category.save();
        int id= category.getCategoryId();
        System.out.println(id);
    }


}

