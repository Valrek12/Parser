package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_attribute_description")
public class AttributeDescription extends Model {
    public void setAttributeGroupId(int id){
        set("attribute_group_id", id);
    }
    public int getAttributeGroupId(){
        return getInteger("attribute_group_id");
    }


    public void setLanguage(int id){set("language_id", id);}
    public int getLanguage(){return getInteger("language_id");}

    public void setName(String description){set("name", description);}
    public String getName(){
        return getString("name");
    }
}
