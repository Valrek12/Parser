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

public class XmlOffer {
    private int id;

    private boolean available;
    private String url;
    private ArrayList<Integer> categories;
    private String picture;
    private String currencyId;
    private String delivery;
    private String name;
    private String vendor;
    private String vendorCode;
    private String description;
    private int price;
    private static ArrayList<XmlOffer> offers = new ArrayList<XmlOffer>();
    private static final Logger logger = LogManager.getLogger();


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Integer> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Integer> categories) {
        this.categories = categories;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Contract(pure = true)
    public XmlOffer(){

    }

    @Contract(pure = true)
    public XmlOffer(boolean now){
        this.id = getId();
        this.available = isAvailable();
        this.categories = getCategories();
        this.currencyId = getCurrencyId();
        this.delivery = getDelivery();
        this.description = getDescription();
        this.url = getUrl();
        this.picture = getPicture();
        this.vendor = getVendor();
        this.vendorCode = getVendorCode();
        this.name = getName();
    }

    public static ArrayList<XmlOffer> getOffers (@NotNull XmlUtils xmlDeserialization) throws ParserConfigurationException, SAXException, IOException {
        NodeList offerElements = xmlDeserialization.getDocument().getElementsByTagName("offer");
        for (int i = 0; i < offerElements.getLength(); i++) {
            Node offerId = offerElements.item(i);
            NamedNodeMap attributes = offerId.getAttributes();
            Element eElement = (Element) offerId;
            XmlOffer offer = new XmlOffer();
            offer.setId(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()));
            offer.setAvailable(Boolean.parseBoolean(attributes.getNamedItem("available").getNodeValue()));
            getOfferEntity(eElement, offer);
            offers.add(offer);
            logger.info("Offer с Id - %s распарсен успешно", attributes.getNamedItem("id").getNodeValue());
        }
        return offers;
    }

    private static XmlOffer getOfferEntity(@NotNull Node node, XmlOffer offer) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            ArrayList<Integer> ints = new ArrayList<>();
            Element element = (Element) node;
            offer.setName(getTagValue("name", element));
            offer.setUrl(getTagValue("url", element));
            offer.setCurrencyId(getTagValue("currencyId", element));
            offer.setDelivery(getTagValue("delivery", element));
            offer.setDescription(getTagValue("description", element));
            offer.setVendorCode(getTagValue("vendorCode", element));
            offer.setVendor(getTagValue("vendor",element));
            offer.setPicture(getTagValue("picture",element));
            offer.setPrice(Integer.parseInt(getTagValue("price", element)));
            for(int i =0; i < element.getElementsByTagName("categoryId").getLength(); i++){
                    Element nodes = (Element) element.getElementsByTagName("categoryId").item(i);
                    int categoryId = Integer.parseInt(nodes.getFirstChild().getNodeValue());
                    ints.add(categoryId);
            }
            offer.setCategories(ints);
            System.out.println(getTagValue("name", element));
        }
        return offer;
    }

    private static String getTagValue(String tag, @NotNull Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
