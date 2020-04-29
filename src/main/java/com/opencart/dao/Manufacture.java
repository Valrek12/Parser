package com.opencart.dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_manufacturer")
public class Manufacture extends Model {

    public Manufacture(){

    }
    public void setManufactureId(int id){
        set("manufacturer_id", id);
    }

    public int getManufactureId(){
        return getInteger("manufacturer_id");
    }

    public void setName(String name){
        set("name", name);
    }

    public String getName(){
        return getString("name");
    }

    public void setImage(String image){set("image", image);}

    public String getImage(){
        return getString("image");
    }

    public void setSortOrder(int sortOrder){ set("sort_order", sortOrder); }

    public int getSortOrder(){ return getInteger("sort_order"); }
}
