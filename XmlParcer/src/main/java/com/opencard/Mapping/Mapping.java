package com.opencard.Mapping;

import com.opencard.Dao.Category;
import com.opencard.Dao.CategoryTemp;
import com.opencard.Utils.XmlUtils;
import com.opencard.XmlEntity.XmlCategories;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.Contract;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Mapping {
    private Category category;
    private XmlUtils xmlUtils;
    private CategoryTemp categoryTemp;


    public Mapping() throws IOException {
        this.category = new Category();
        this.xmlUtils = new XmlUtils();
        this.categoryTemp = new CategoryTemp();
    }

    @Contract(pure = true)
    public void CategoryMapping() throws IOException, SAXException, ParserConfigurationException {
        ArrayList<XmlCategories> xmlCategories = XmlCategories.getCategories(xmlUtils);
        for(XmlCategories xmlCategory: xmlCategories){
            this.category.setCategoryId(xmlCategory.getId());
            this.category.setParentId(xmlCategory.getParentId());
            this.category.setColumn(2);
            this.category.setImage("");
            this.category.setStatus(1);
            this.category.setSortOrder(false);
            this.category.setDateAdded(new Date());
            this.category.setDateModified(new Date());
            this.category.setStickers(true);
            this.category.setSortOrder(true);
            this.category.setTop(true);
            this.category.save();
        }
    }

    public void DumpTable(){
        LazyList<Category> categoryArrayList = Category.findAll();
        for(Category model: categoryArrayList){
            categoryTemp.setCategoryId(model.getCategoryId());
            categoryTemp.setParentId(model.getParentId());
            categoryTemp.setColumn(model.getColumn());
            categoryTemp.setImage(model.getImage());
            categoryTemp.setStatus(model.getStatus());
            categoryTemp.setSortOrder(model.getSortOrder());
            categoryTemp.setDateAdded(model.getDateAdded());
            categoryTemp.setDateModified(model.getDateModified());
            categoryTemp.setStickers(model.getStickers());
            categoryTemp.setTop(model.getTop());
            categoryTemp.save();
            System.out.println(categoryTemp.getCategoryId());
        }



    }


}



