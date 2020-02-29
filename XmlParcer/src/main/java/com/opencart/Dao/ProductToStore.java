package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_product_to_store")
public class ProductToStore extends Model {
    public void setProductId(int id){ set("product_id", id); }

    public int getProductId(){
        return getInteger("product_id");
    }

    public void setStoreId(int storeIdd){ set("store_id", storeIdd); }

    public int getStoreId(){
        return getInteger("store_id");
    }
}
