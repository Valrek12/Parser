package com.opencart.dao;

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


    public void setDescription(String description){set("description", description);}
    public int getDescription(){return getInteger("description");}

    public void setMetaTitle(String name ){set("meta_title", name);}
    public int getMetaTitle(){return getInteger("meta_title");}

    public void setName(String description){set("name", description);}
    public String getName(){
        return getString("name");
    }


    public void setMetaDescription(String description){set("meta_description", description);}
    public int getMetaDescription(){return getInteger("meta_description");}

    public void setMetaKeyword(String metaKeyword){set("meta_keyword", metaKeyword);}
}
