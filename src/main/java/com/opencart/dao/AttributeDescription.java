package com.opencart.dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_attribute_description")
public class AttributeDescription extends Model {
    public void setAttributeId(int id){
        set("attribute_id", id);
    }
    public int getAttributeId(){
        return getInteger("attribute_id");
    }


    public void setLanguage(int id){set("language_id", id);}
    public int getLanguage(){return getInteger("language_id");}

    public void setName(String description){set("name", description);}
    public String getName(){
        return getString("name");
    }
}
