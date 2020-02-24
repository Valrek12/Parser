package com.opencart.Mapping;

import com.opencart.Dao.*;
import com.opencart.Utils.ImageController;
import com.opencart.Utils.XmlUtils;
import com.opencart.XmlEntity.XmlCategories;
import com.opencart.XmlEntity.XmlOffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javalite.activejdbc.LazyList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
            category.setImage("");
            category.setTop(true);
            category.setSortOrder(false);
            category.setStickers(false);
            category.setDateAdded(new java.sql.Date(Instant.now().toEpochMilli()));
            category.setDateModified(new java.sql.Date(Instant.now().toEpochMilli()));
            category.setStatus(true);
            category.save();
            logger.info(String.format("Добавлена запись в таблицу oc3_category c category_id - %s", xmlCategory.getId()));
        }
        logger.info("Данные успешно загружены в таблицу oc3_category ");
    }

    public void OfferMapping() throws IOException{
        ArrayList<XmlOffer> xmlOffers = null;
        try {
            xmlOffers = XmlOffer.getOffers(xmlUtils);
        } catch (ParserConfigurationException e) {
            logger.error(String.format("Возникла ошибка при парсинге конфигурации, подробности - %s ", e.getMessage()));
        } catch (SAXException e) {
            logger.error(String.format("Возникла ошибка при парсинге XML, подробности - %s ", e.getMessage()));
        }
        for(XmlOffer xmlOffer: xmlOffers){
            if(Products.where("sku =?", xmlOffer.getId()).size() != 0){
                ImageController imageController = new ImageController();
                String uriImage = imageController.Download(xmlOffer.getPicture());
                Products.update("price = ?, image = ?, date_modified = ?","sku =?", xmlOffer.getPrice(), uriImage, new java.sql.Date(Instant.now().toEpochMilli()),  xmlOffer.getId());
                logger.debug(String.format("Обновлен продукт с scu - %s", xmlOffer.getId()));
            }else{
                Products product = new Products();
                int productId = insertProduct(product, xmlOffer);
                InsertProductToCategory(xmlOffer, productId);
            }
            insertOrUpdateOfferDescription(xmlOffer);

        }
    }

    private static void InsertProductToCategory(@NotNull XmlOffer xmlOffer, int productId){
        ProductToCategory productToCategory = new ProductToCategory();
        for(Integer categoryId: xmlOffer.getCategories()){
            productToCategory.setProductId(productId);
            productToCategory.setCategoryId(categoryId);
            productToCategory.save();
            logger.info(String.format("Выполнен маппинг категории и продукта с id - %s", productId));
        }
    }

    private static void insertOrUpdateOfferDescription(@NotNull XmlOffer xmlOffer){
        Products products = Products.findFirst("sku = ?", xmlOffer.getId());
        int id = (int) products.get("product_id");
        if(Products.where("product_id = ?", id).size() != 0){
            OfferDescription.update("name = ?, description = ?", "product_id= ?", xmlOffer.getName(), xmlOffer.getDescription(), id);
            logger.debug(String.format("oc3_product_description: обновлена запись с id - %s ", id));
        }else {
            OfferDescription offerDescription = new OfferDescription();
            offerDescription.setLanguage(1);
            offerDescription.setName(xmlOffer.getName());
            offerDescription.setMetaDescription("");
            offerDescription.setMetaTitle("");
            offerDescription.setMetKeyword("");
            offerDescription.setDescription(xmlOffer.getDescription());
            offerDescription.save();
            logger.debug(String.format("oc3_product_description: добавлена запись с id - %s ", id));
        }
    }

    private static int insertProduct(@NotNull Products product, @NotNull XmlOffer xmlOffer) throws IOException {
        ImageController controller = new ImageController();
        product.setModel(String.valueOf(xmlOffer.getId()));
        product.setSku(String.valueOf(xmlOffer.getId()));
        product.setPrice((double) xmlOffer.getPrice());
        product.setDateAdded(new java.sql.Date(Instant.now().toEpochMilli()));
        product.setDateModified(new java.sql.Date(Instant.now().toEpochMilli()));
        product.setCost(0);
        product.setLocation("");
        product.setImage(controller.Download(xmlOffer.getPicture()));
        product.setEan("");
        product.setIsbn("");
        product.setJan("");
        product.setMpn("");
        product.setIsbn("");
        product.setQuantity(8);
        product.setStockId(5);
        product.setManufacturerId(getManufactureId(xmlOffer.getVendor()));
        product.setTaxClassId(0);
        product.setSupplerCode(1);
        product.setSupplerType(0);
        product.setUpc("");
        product.setPopupsize(0);
        product.setStickers(0);
        product.save();
        logger.debug(String.format("oc3_product: добавлена запись с id - %s ", xmlOffer.getId()));
        return product.getProductId();
    }


    private static int getManufactureId(String name){
        if(Manufacture.where("name = ?", name).size() != 0){
            Manufacture manufacture = Manufacture.findFirst("name = ?", name);
            return manufacture.getInteger("manufacturer_id");
        }else{
            return 0;
        }
    }

    public void DumpTable(){
        LazyList<Category> categoryArrayList = Category.findAll();
        for(Category model: categoryArrayList){
            CategoryTemp categoryTemp = new CategoryTemp();
            categoryTemp.setCategoryId(model.getCategoryId());
            categoryTemp.setParentId(model.getParentId());
            categoryTemp.setDateAdded(model.getDateAdded());
            categoryTemp.setDateModified(model.getDateModified());
            categoryTemp.setImage(model.getImage());
            categoryTemp.setSortOrder(model.getSortOrder());
            categoryTemp.setStatus(model.getStatus());
            categoryTemp.setStickers(model.getStickers());
            categoryTemp.save();
            logger.info(String.format("Добавлена запись в таблицу oc3_category_temp c category_id - %s", model.getCategoryId()));
        }
        logger.info("Данные успешно выгружены в таблицу oc3_category_temp");
    }

    public void UpdateDescription(){
        ArrayList<XmlCategories> xmlCategories = null;
        try {
            xmlCategories = XmlCategories.getCategories(xmlUtils);
        } catch (ParserConfigurationException e) {
            logger.error(String.format("Возникла ошибка при парсинге конфигурации, подробности - %s ", e.getMessage()));
        } catch (SAXException | IOException e) {
            logger.error(String.format("Возникла ошибка при парсинге XML, подробности - %s ", e.getMessage()));
        }
        for(XmlCategories xmlCategory: xmlCategories){
            if(CategoryDescription.where("category_id = ?", xmlCategory.getId()).size() != 0){
                CategoryDescription.update("name = ?", "category_id = ?", xmlCategory.getName(), xmlCategory.getId());
                logger.info(String.format("Обновлена запись в таблице oc3_category_description c category_id - %s", xmlCategory.getId()));
            }else{
                CategoryDescription description = new CategoryDescription();
                description.setCategoryId(xmlCategory.getId());
                description.setName(xmlCategory.getName());
                description.setLanguage(1);
                logger.info(String.format("Добавлена запись в таблицу oc3_category_description c category_id - %s", xmlCategory.getId()));
            }

        }
    }

}



