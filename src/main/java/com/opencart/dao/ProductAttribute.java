package com.opencart.dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_product_attribute")
public class ProductAttribute extends Model {
    public void setProductId(int id){
        set("product_id", id);
    }
    public int getProductId(){
        return getInteger("product_id");
    }

    public void setAttributeId(int id) {set("attribute_id", id);}
    public int getAttributeId(){return getInteger("attribute_id");}

    public void setLanguage(int id){set("language_id", id);}
    public int getLanguage(){return getInteger("language_id");}

    public void setText(String text){set("text", text);}
    public String getText(){return getString("text");}

}
