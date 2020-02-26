package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_category_to_layout")
public class CategoryToLayout extends Model {
    public void setCategoryId(int id){ set("category_id", id); }

    public int getCategoryId(){
        return getInteger("category_id");
    }

    public void setStoreId(int storeIdd){ set("store_id", storeIdd); }

    public int getStoreId(){
        return getInteger("store_id");
    }

    public void setLayout_id(int layoutId){
        set("layout_id", layoutId);
    }

    public int getLayoutId(){
        return getInteger("layout_id");
    }


}
