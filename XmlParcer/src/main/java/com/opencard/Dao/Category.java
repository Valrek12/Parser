package com.opencard.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.sql.Date;

@Table("oc3_category")
public class Category extends Model {

    public Category(){
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

    public void setImage(int image){
        set("image", image);
    }

    public int getImage(){
        return getInteger("image");
    }

    public void setSortOrder(int sortOrder){
        set("sort_order", sortOrder);
    }

    public int getSortOrder(){
        return getInteger("sort_order");
    }

    public void setStatus(int status){
        set("status", status);
    }

    public int getStatus(){
        return getInteger("status");
    }

    public void setStickers(int stickers){
        set("stickers", stickers);
    }

    public int getStickers(){
        return getInteger("stickers");
    }

    public void setTop(int top){
        set("top", top);
    }

    public int getTop(){
        return getInteger("top");
    }

}
