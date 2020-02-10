package com.opencard.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_category_temp")
public class CategoryTemp extends Model {

    public CategoryTemp(){
    }

    public void setCategoryId(int id){
        set("category_id", id);
    }

    public int getCategoryId(){
        return getInteger("category_id");
    }

    public void setParentId(int id){
        set("parent_id", id);
    }

    public int getParentId(){
        return getInteger("parent_id");
    }

}
