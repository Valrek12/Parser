package com.opencard.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.Date;

@Table("oc3_category_temp")
public class CategoryTemp extends Model {
    public CategoryTemp(){
    }

    public void setCategoryId(int id){
        set("category_id", id);
    }

    public int getCategoryId(){
        return getInteger("category_id");
    }

    public void setParentId(int id){
        set("parent_id", id);
    }

    public int getParentId(){
        return getInteger("parent_id");
    }

    public void setColumn(int column){
        set("column", column);
    }

    public int getColumn(){
        return getInteger("column");
    }

    public void setDateAdded(Date date){
        set("date_added", date);
    }

    public Date getDateAdded(){
        return getDate("date_added");
    }

    public void setDateModified(Date date){
        set("date_modified", date);
    }

    public Date getDateModified(){
        return getDate("date_modified");
    }

    public void setImage(String image){
        set("image", image);
    }

    public String getImage(){
        return getString("image");
    }

    public void setSortOrder(boolean sortOrder){
        set("sort_order", sortOrder);
    }

    public boolean getSortOrder(){
        return getBoolean("sort_order");
    }

    public void setStatus(boolean status){
        set("status", status);
    }

    public int getStatus(){
        return getInteger("status");
    }

    public void setStickers(boolean stickers){
        set("stickers", stickers);
    }

    public boolean getStickers(){
        return getBoolean("stickers");
    }

    public void setTop(boolean top){
        set("top", top);
    }

    public boolean getTop(){
        return getBoolean("top");
    }

}
