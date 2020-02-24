package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_product_to_category")
public class ProductToCategory extends Model {
    public void setCategoryId(int id){
        set("category_id", id);
    }

    public int getCategoryId(){
        return getInteger("category_id");
    }

    public void setProductId(int id){
        set("product_id", id);
    }
    public int getProductId(){
        return getInteger("product_id");
    }

}
