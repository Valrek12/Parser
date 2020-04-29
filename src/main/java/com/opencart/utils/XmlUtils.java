package com.opencart.utils;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class XmlUtils {

    public Settings settings;
    public String xmlFIle;
    public String nameFile;
    public ArrayList<Integer> categoriesIds;

    /**
     * Метод скачиваеь файл, по указанному пути в application.properties
     * @paдram xmlFIle
     * @return
     * @throws IOException
     */
    public File DownloadXml(String xmlFIle) throws  IOException {
        InputStream in = new URL(xmlFIle).openStream();
        Files.copy(in, Paths.get(nameFile));
        File file = new File(nameFile);
        return file.getCanonicalFile();
    }

    public XmlUtils() throws IOException {
        settings = new Settings();
        xmlFIle = settings.getFile();
        nameFile = settings.getNameFile();

    }

    /**
     * Метод получения, скаченного Xml
     * @return - получаем xml
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public Document getDocument() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException {
                if (systemId.contains("shops.dtd")) {
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
            }
        });
        Document document = builder.parse(nameFile);
        this.categoriesIds = GetCategoryIds(document);
        return  document;
    }

    @NotNull
    private static  ArrayList<Integer> GetCategoryIds(@NotNull Document document)  {
        ArrayList<Integer> CategoryIds = new ArrayList<>();
        NodeList employeeElements = document.getDocumentElement().getElementsByTagName("category");
        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node employee = employeeElements.item(i);
            NamedNodeMap attributes = employee.getAttributes();
            CategoryIds.add(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()));
        }
       return CategoryIds;
    }

    /**
     * Метод удаления скаченного файла
     */
    public void DeleteXml(){
        if(new File(nameFile).exists()){
            new File(nameFile).delete();
        }
    }

}

