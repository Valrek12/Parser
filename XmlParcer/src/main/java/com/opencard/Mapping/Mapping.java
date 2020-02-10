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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Mapping {
    private XmlUtils xmlUtils;
    private DateFormat dateFormat;


    public Mapping() throws IOException {
        this.xmlUtils = new XmlUtils();
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    @Contract(pure = true)
    public void CategoryMapping() throws IOException, SAXException, ParserConfigurationException {
        ArrayList<XmlCategories> xmlCategories = XmlCategories.getCategories(xmlUtils);
        for(XmlCategories xmlCategory: xmlCategories){
            Category category = new Category();
            category.setCategoryId(xmlCategory.getId());
            category.setParentId(xmlCategory.getParentId());
            category.save();
        }
    }

    public void DumpTable(){
        LazyList<Category> categoryArrayList = Category.findAll();
        for(Category model: categoryArrayList){
            CategoryTemp categoryTemp = new CategoryTemp();
            categoryTemp.setCategoryId(model.getCategoryId());
            categoryTemp.setParentId(model.getParentId());
            System.out.println();
            categoryTemp.save();
        }
        System.out.println("Данные успешно выгружены в таблицу oc3_category_temp");
    }


}



