package com.opencart.dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_manufacturer_to_store")
public class ManufactureToStore extends Model {
    public void setManufactureId(int id){
        set("manufacturer_id", id);
    }

    public int getManufactureId(){
        return getInteger("manufacturer_id");
    }

    public void setStoreId(int storeIdd){ set("store_id", storeIdd); }

    public int getStoreId(){
        return getInteger("store_id");
    }
}
