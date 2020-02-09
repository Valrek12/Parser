package com.opencard.Utils;

import org.w3c.dom.Document;
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

public class XmlUtils {

    public Settings settings;
    public String xmlFIle;
    public String nameFile;

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
        return  document;
    }

    public void DeleteXml(){
      new File(nameFile).delete();
    }

}

