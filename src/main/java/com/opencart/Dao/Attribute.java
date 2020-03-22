package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_attribute")
public class Attribute extends Model {
    public void setAttributeId(int id){
        set("attribute_id", id);
    }
    public int getAttributeId(){
        return getInteger("attribute_id");
    }

    public void setAttributeGroupId(int id){
        set("attribute_group_id", id);
    }
    public int getAttributeGroupId(){
        return getInteger("attribute_group_id");
    }

    public void setSortOrder(boolean sortOrder){
        set("sort_order", sortOrder);
    }
    public boolean getSortOrder(){
        return getBoolean("sort_order");
    }
}
