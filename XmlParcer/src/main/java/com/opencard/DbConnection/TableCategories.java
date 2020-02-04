package com.opencard.DbConnection;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name ="oc3_category")
public class TableCategories {
    private int category_id;
    private String image;
    private int parent_id;
    private int top;
    private int column;
    private int sort_order;
    private int status;
    private int stickers;
    private Date date_added;
    private Date date_modified;

    public TableCategories(int category_id, int parent_id, Date date_modified, Date date_added){
        this.category_id=category_id;
        this.parent_id=parent_id;
        this.date_added= date_added;
        this.date_modified=date_modified;
        this.column =2;
        this.top=1;
        this.sort_order=0;
        this.status=1;
        this.stickers=0;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStickers() {
        return stickers;
    }

    public void setStickers(int stickers) {
        this.stickers = stickers;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

}
