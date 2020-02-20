package com.opencart.Dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.sql.Date;

@Table("oc3_product")
public class Products extends Model {

    public Products(){

    }
    public void setModel(String id){
        set("model", id);
    }

    public String getModel(){
        return getString("model");
    }

    public void setSku(String id){
        set("sku", id);
    }

    public String getSku(){
        return getString("sku");
    }

    public void setImage(String image){set("image", image);}

    public String getImage(){
        return getString("image");
    }

    public String getUpc(){
        return getString("upc");
    }

    public void setUpc(String upc){
        set("upc", upc);
    }

    public String getEan(){
        return getString("ean");
    }

    public void setEan(String ean){
        set("ean", ean);
    }

    public String getJan(){
        return getString("Jan");
    }

    public void setJan(String Jan){
        set("jan", Jan);
    }

    public String getIsbn(){
        return getString("isbn");
    }

    public void setIsbn(String isbn){
        set("isbn", isbn);
    }

    public String getMpn(){
        return getString("mpn");
    }

    public void setMpn(String mpn){
        set("mpn", mpn);
    }

    public void setLocation(String location){
        set("location", location);
    }

    public String getLocation(){
        return getString("location");
    }

    public void setQuantity(int quantity){
        set("quantity", quantity);
    }

    public int getQuantity(){
        return getInteger("quantity");
    }

    public void setStockId(int stockId){
        set("stock_status_id", stockId);
    }

    public int getStockId(){
        return getInteger("stock_status_id");
    }

    public void setStickers(int stickers){
        set("stickers", stickers);
    }

    public int getStickers(){
        return getInteger("stickers");
    }

    public void setManufacturerId(int stickers){
        set("manufacturer_id", stickers);
    }

    public int getManufacturerId(){
        return getInteger("manufacturer_id");
    }

    public void setSupplerType(int supplerType){
        set("suppler_type", supplerType);
    }

    public int getSupplerType(){
        return getInteger("suppler_type");
    }

    public void setSupplerCode(int supplerCode){
        set("suppler_code", supplerCode);
    }

    public int getSupplerCode(){
        return getInteger("suppler_code");
    }

    public void setCost(int cost){
        set("cost", cost);
    }

    public int getCost(){
        return getInteger("cost");
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

    public void setPrice(Double price){set("price", price);}

    public Double getPrice(){return getDouble("price");}

    public void setTaxClassId(int taxClassId){set("tax_class_id", taxClassId);}

    public int getTaxClassId(){return getInteger("tax_class_id");}

    public void setPopupsize(int popupsize){set("popupsize", popupsize);}

    public int getPopupsize(){return getInteger("popupsize");}

}
