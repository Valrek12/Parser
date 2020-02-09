package com.opencard.Mapping;

import com.opencard.Dao.Category;
import com.opencard.XmlEntity.XmlCategories;
import com.opencard.Utils.XmlUtils;
import org.jetbrains.annotations.Contract;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class Mapping {
    private Category category;
    private XmlUtils xmlUtils;

    public Mapping() throws IOException {
        this.category = new Category();
        this.xmlUtils = new XmlUtils();
    }

    @Contract(pure = true)
    public void CategoryMapping() throws IOException, SAXException, ParserConfigurationException {
        ArrayList<XmlCategories> xmlCategories = XmlCategories.getCategories(xmlUtils);
        for(XmlCategories xmlCategory: xmlCategories){
            this.category.setCategoryId(xmlCategory.getId());
            this.category.setParentId(xmlCategory.getParentId());
            this.category.setColumn(2);
            this.category.setImage(1);
            this.category.setStatus(1);
            this.category.setSortOrder(2);
            this.category.setDateAdded(new Date(1));
            this.category.setDateModified(new Date(1));
            this.category.setStickers(1);
            this.category.setSortOrder(2);
            this.category.save();
        }
    }



}
