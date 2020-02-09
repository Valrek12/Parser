package com.opencard.XmlEntity;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;

/**
 * Класс описываает методы дял работы с Shop.dtd
 */
public class Shop {
    public List categories;

    public Shop(Document document){
        categories = GetCategories(document);
    }
    @NotNull
    private List GetCategories(@NotNull Document document){
        List categories = new List();
        Element root = document.getDocumentElement();
        System.out.println(root);
        NodeList employeeElements = document.getDocumentElement().getElementsByTagName("shop");
        for(int i=0; i < employeeElements.getLength() ; i ++){
            Node nNode = employeeElements.item(i);
            Element eElement = (Element) nNode;
            categories.add(eElement
                    .getElementsByTagName("categories")
                    .item(i)
                    .getTextContent());
        }
        return categories;
    }

}
