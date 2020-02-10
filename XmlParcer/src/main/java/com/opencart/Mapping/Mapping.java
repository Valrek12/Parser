package com.opencart.Mapping;

import com.opencart.Dao.Category;
import com.opencart.Dao.CategoryTemp;
import com.opencart.Utils.XmlUtils;
import com.opencart.XmlEntity.XmlCategories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger();

    public Mapping() throws IOException {
        this.xmlUtils = new XmlUtils();
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    @Contract(pure = true)
    public void CategoryMapping() throws IOException{
        ArrayList<XmlCategories> xmlCategories = null;
        try {
            xmlCategories = XmlCategories.getCategories(xmlUtils);
        } catch (ParserConfigurationException e) {
            logger.error(String.format("Возникла ошибка при парсинге конфигурации, подробности - %s ", e.getMessage()));
        } catch (SAXException e) {
            logger.error(String.format("Возникла ошибка при парсинге XML, подробности - %s ", e.getMessage()));
        }
        for(XmlCategories xmlCategory: xmlCategories){
            Category category = new Category();
            category.setCategoryId(xmlCategory.getId());
            category.setParentId(xmlCategory.getParentId());
            category.save();
            logger.debug(String.format("Добавлена запись в таблицу oc3_category c category_id - %s", xmlCategory.getId()));
        }
        logger.debug("Данные успешно загружены в таблицу oc3_category ");
    }

    public void DumpTable(){
        LazyList<Category> categoryArrayList = Category.findAll();
        for(Category model: categoryArrayList){
            CategoryTemp categoryTemp = new CategoryTemp();
            categoryTemp.setCategoryId(model.getCategoryId());
            categoryTemp.setParentId(model.getParentId());
            categoryTemp.save();
            logger.debug(String.format("Добавлена запись в таблицу oc3_category_temp c category_id - %s", model.getCategoryId()));
        }
        logger.debug("Данные успешно выгружены в таблицу oc3_category_temp");
    }


}



