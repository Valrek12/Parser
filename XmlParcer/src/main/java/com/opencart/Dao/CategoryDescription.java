package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_category_description")
public class CategoryDescription extends Model {
    public CategoryDescription(){

    }

    public void setCategoryId(int id){
        set("category_id", id);
    }

    public int getCategoryId(){
        return getInteger("category_id");
    }

    public void setLanguage(int id){set("language_id", id);}

    public int getLanguage(){return getInteger("language_id");}

    public void setName(String id){
        set("name", id);
    }

    public String getName(){
        return getString("name");
    }
}
