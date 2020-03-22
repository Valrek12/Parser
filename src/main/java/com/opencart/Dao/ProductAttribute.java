package com.opencart.Dao;

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

}
