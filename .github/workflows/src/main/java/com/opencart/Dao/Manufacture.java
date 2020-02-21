package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_manufacturer")
public class Manufacture extends Model {

    public Manufacture(){

    }
    public void setManufactureId(int id){
        set("manufacturer_id", id);
    }

    public int getModel(){
        return getInteger("manufacturer_id");
    }

    public void setName(String name){
        set("name", name);
    }

    public String getNAme(){
        return getString("name");
    }
}
