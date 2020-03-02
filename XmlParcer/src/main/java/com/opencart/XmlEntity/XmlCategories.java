package com.opencart.XmlEntity;

import com.opencart.Utils.XmlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class XmlCategories {
    private int id;
    private int parentId;
    private  String name;
    private static ArrayList<XmlCategories> categories = new ArrayList<XmlCategories>();
    private static final Logger logger = LogManager.getLogger(XmlCategories.class);

    @Contract(pure = true)
    public XmlCategories(){

    }

    @Contract(pure = true)
    private XmlCategories(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName(){ return name; }

    public static ArrayList<XmlCategories> getCategories (@NotNull XmlUtils xmlDeserialization) throws ParserConfigurationException, SAXException, IOException {
        NodeList employeeElements = xmlDeserialization.getDocument().getDocumentElement().getElementsByTagName("category");
        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node employee = employeeElements.item(i);
            NamedNodeMap attributes = employee.getAttributes();
            Element eElement = (Element) employee;

            if(attributes.getNamedItem("parentId") != null) {
                //  Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
                categories.add(new XmlCategories(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()), Integer.parseInt(attributes.getNamedItem("parentId").getNodeValue()), eElement.getFirstChild().getNodeValue()));
            }else{
                categories.add(new XmlCategories(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()), 0,  eElement.getFirstChild().getNodeValue()));
            }
        }
        for (XmlCategories categories : categories){
            logger.info(String.format(" id - %s, parentId - %s, name - %s", categories.getId(), categories.getParentId(), categories.getName()));
        }
            logger.debug("Парсинг категорий прошел успешно");
        return categories;
    }

}