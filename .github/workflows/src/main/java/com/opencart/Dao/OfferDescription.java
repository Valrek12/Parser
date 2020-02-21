package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("oc3_product_description")
public class OfferDescription extends Model {

    public OfferDescription(){
    }

    public void setProductId(int id){
        set("product_id", id);
    }
    public int getProductId(){
        return getInteger("product_id");
    }

    public void setLanguage(int id){set("language_id", id);}
    public int getLanguage(){return getInteger("language_id");}

    public void setName(String name){
        set("name", name);
    }

    public String getName(){
        return getString("name");
    }

    public void setTag(String tag){
        set("tag", tag);
    }

    public String getTag(){
        return getString("tag");
    }


    public void setMetaTitle(String metaTitle){
        set("meta_title", metaTitle);
    }

    public String getMetaTitle(){
        return getString("meta_title");
    }


    public String getMetaDescription(){
        return getString("meta_description");
    }

    public void setMetaDescription(String metaDescription){
        set("meta_description", metaDescription);
    }

    public String getMetKeyword(){
        return getString("meta_keyword");
    }

    public void setDescription(String description){
        set("description", description);
    }
    public String getDescription(){
        return getString("description");
    }

    public void setMetKeyword(String MetKeyword){
        set("description", MetKeyword);
    }

}
