package com.opencart.Mapping;

import com.opencart.Dao.*;
import com.opencart.Utils.HtmlParserController;
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

    /**
     * Метод опиывает логику загрузки распарсенных категорий по таблицам в бд.
     * @throws IOException
     */
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
            if(CategoryToStore.where("category_id = ?", xmlCategory.getId()).size() == 0){
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
                UpdateCategoryLayout(xmlCategory);
                logger.info(String.format("Добавлена запись в таблицу oc3_category_to_layout с id -%s", xmlCategory.getId()));
                UpdateToStoreCategory(xmlCategory);
                logger.info(String.format("Добавлена запись в таблицу oc3_category_to_store с id -%s", xmlCategory.getId()));
            }

        }
        logger.info("Данные успешно загружены в таблицу oc3_category ");

    }

    /**
     * Метод обновления store у продукта
     * @param xmlOffer
     */
    private void UpdateToStoreProduct(@NotNull XmlOffer xmlOffer){
        Products products = Products.findFirst("sku = ?", xmlOffer.getId());
        int id = (int) products.get("product_id");
        if(ProductToStore.where("product_id = ?", id).size()==0){
            ProductToStore productToStore = new ProductToStore();
            productToStore.setProductId(id);
            productToStore.setStoreId(0);
            productToStore.save();
        }
    }

    /**
     * Метод обновления store у категорий
     * @param xmlCategories
     */
    private void UpdateToStoreCategory(@NotNull XmlCategories xmlCategories){
        if(CategoryToStore.where("category_id = ?", xmlCategories.getId()).size() == 0){
            CategoryToStore categoryToStore = new CategoryToStore();
            categoryToStore.setCategoryId(xmlCategories.getId());
            categoryToStore.setStoreId(0);
            categoryToStore.save();
        }
    }

    /**
     * Метод обновления Layout у категорий
     * @param xmlCategory - объект класса, работающий с распасренными категориями
     */
    private void UpdateCategoryLayout(@NotNull XmlCategories xmlCategory){
        if(CategoryToLayout.where("category_id = ?", xmlCategory.getId()).size() == 0) {
            CategoryToLayout toLayout = new CategoryToLayout();
            toLayout.setCategoryId(xmlCategory.getId());
            toLayout.setLayout_id(0);
            toLayout.setStoreId(0);
            toLayout.save();
        }
    }

    /**
     * Метод опиывает логику загрузки распарсенных продуктов по таблицам в бд.
     * @throws IOException
     */
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
                insertOrUpdateOfferDescription(xmlOffer);
                UpdateToStoreProduct(xmlOffer);
                logger.debug(String.format("Обновлен продукт с scu - %s", xmlOffer.getId()));
            }else{
                Products product = new Products();
                int productId = insertProduct(product, xmlOffer);
                InsertProductToCategory(xmlOffer, productId);
                insertOrUpdateOfferDescription(xmlOffer);
                UpdateToStoreProduct(xmlOffer);
            }

        }
    }

    private static void InsertAttribute(@NotNull XmlOffer xmlOffer){
        HtmlParserController htmlParserController = new HtmlParserController(xmlOffer.getDescription());
        AttributeDescription attributeDescription = new AttributeDescription();
        String complects = htmlParserController.getComplects();

    }

    /**
     * Метод  добавляет данные в таблицу Product_to_category
     * @param xmlOffer - объект класса, работающий с распарсенными продуктами
     * @param productId
     */
    private static void InsertProductToCategory(@NotNull XmlOffer xmlOffer, int productId){
        for(int categoryId: xmlOffer.getCategories()){
            ProductToCategory productToCategory = new ProductToCategory();
            productToCategory.setCategoryId(categoryId);
            productToCategory.setProductId(productId);
            productToCategory.saveIt();
            logger.info(String.format("Выполнен маппинг категории и продукта с id - %s", productId));
        }
    }

    /**
     * Метод добаляет или обновляет описание подукта
     * @param xmlOffer - бъект класса, работающий с распарсенными продуктами
     */
    private static void insertOrUpdateOfferDescription(@NotNull XmlOffer xmlOffer){
        Products products = Products.findFirst("sku = ?", xmlOffer.getId());
        int id = (int) products.get("product_id");
        if(OfferDescription.where("product_id = ?", id).size() != 0){
            OfferDescription.update("name = ?, description = ?, tag = ?", "product_id= ?", xmlOffer.getName(), xmlOffer.getDescription(), GetCategoryName(xmlOffer)  + ", " +  xmlOffer.getVendor(), id);
            logger.debug(String.format("oc3_product_description: обновлена запись с id - %s ", id));
        }else {
            OfferDescription offerDescription = new OfferDescription();
            offerDescription.setProductId(id);
            offerDescription.setLanguage(1);
            offerDescription.setName(xmlOffer.getName());
            offerDescription.setMetaDescription("");
            offerDescription.setMetaTitle(xmlOffer.getName());
            offerDescription.setMetKeyword("");
            offerDescription.setDescription("");
            offerDescription.setTag(GetCategoryName(xmlOffer)  + ", " +  xmlOffer.getVendor());
            offerDescription.save();
            logger.debug(String.format("oc3_product_description: добавлена запись с id - %s ", id));
        }
    }

    /**
     * Метод получения категории
     * @param xmlOffer - сущность для работы с продуктами
     * @return
     */
    private static String GetCategoryName(@NotNull XmlOffer xmlOffer){
        if(xmlOffer.getCategories().size() != 0){
            int categoryId = xmlOffer.getCategories().get(0);
            CategoryDescription model = CategoryDescription.findFirst("category_id = ?", categoryId);
            String name = model.getName();
            return name;
        } else{
            return "Нет категории";
        }
    }

    /**
     * Метод добавления продукта в бд opencart
     * @param  product - объект описывающий работу с таблицей product
     * @param xmlOffer - объект класса распарсенной Xml
     * @return id
     * @throws IOException
     */
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
        product.setStatus(true);
        product.save();
        logger.debug(String.format("oc3_product: добавлена запись с id - %s ", xmlOffer.getId()));
        Products products = Products.findFirst("sku = ?", xmlOffer.getId());
        int id = (int) products.get("product_id");
        return id;
    }

    /**
     * Получаем id производителя по его имени
     * @param name - имя производителя
     * @return
     */
    private static int getManufactureId(String name){
        if(Manufacture.findFirst("name = ?", name) != null){
            Manufacture manufacture = Manufacture.findFirst("name = ?", name);
            int id = manufacture.getInteger("manufacturer_id");
            logger.info(String.format("ОБновлена запись в таблице manufacturer - %s", id));
            ManufactureToStore(id);
            return id;
        }else{
            Manufacture manufacture = new Manufacture();
            manufacture.setImage("");
            manufacture.setSortOrder(1);
            manufacture.setName(name);
            manufacture.save();
            Manufacture manufacturer = Manufacture.findFirst("name = ?", name);
            int id = manufacturer.getManufactureId();
            ManufactureToStore(id);
            logger.info(String.format("Добавлена запись в таблицу manufacturer - %s", id));
            return id;
        }
    }

    /**
     * Метод добавления записи в Manufacture_to_store
     * @param id - id производителя
     */
    private static void ManufactureToStore(int id){
        if(ManufactureToStore.where("manufacturer_id = ?", id).size() == 0){
            ManufactureToStore manufactureToStore = new ManufactureToStore();
            manufactureToStore.setManufactureId(id);
            manufactureToStore.setStoreId(0);
            manufactureToStore.save();
            logger.info(String.format("Добавлена запись в таблицу manufacturer_to_store с id - %s",  id));
        }
    }

    /**
     * Метод создает дамп таблицы категорий
     * TO DO: Вынести в этот класc, сделать метод private
     */
    public void DumpTableCategory(){
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
            categoryTemp.setTop(model.getTop());
            categoryTemp.save();
            logger.info(String.format("Добавлена запись в таблицу oc3_category_temp c category_id - %s", model.getCategoryId()));
        }
        logger.info("Данные успешно выгружены в таблицу oc3_category_temp");
    }

    /**
     * Метод обновляет описание у категорий
     * TO DO: Вынести в этот класc, сделать метод private
     */
    public void UpdateDescriptionCategory(){
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
                description.setDescription("Сгенерировано пасером");
                description.setMetaDescription("Сгенерировано пасером");
                description.setMetaTitle(xmlCategory.getName());
                description.setMetaKeyword("");
                description.save();
                logger.info(String.format("Добавлена запись в таблицу oc3_category_description c category_id - %s", xmlCategory.getId()));
            }

        }
    }

}



