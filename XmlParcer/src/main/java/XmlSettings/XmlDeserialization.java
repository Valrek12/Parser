package XmlSettings;

import Utils.Settings;
import org.w3c.dom.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class XmlDeserialization {

    private Settings settings;
    private String xmlFIle;
    private String nameFile;
    private static ArrayList<Categories> categories = new ArrayList<>();

    private File DownloadXml(String xmlFIle) throws  IOException {
        InputStream in = new URL(xmlFIle).openStream();
        Files.copy(in, Paths.get(nameFile));
        File file = new File(nameFile);
        return file.getCanonicalFile();
    }

    public XmlDeserialization() throws IOException {
        settings = new Settings();
        xmlFIle = settings.getFile();
        nameFile = settings.getNameFile();
    }

    public Document getDocument() throws JAXBException, IOException, SAXException, ParserConfigurationException, TransformerException {
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

    public void Run() throws IOException, TransformerException, ParserConfigurationException, SAXException, JAXBException {
        NodeList employeeElements = getDocument().getDocumentElement().getElementsByTagName("category");

        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node employee = employeeElements.item(i);
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = employee.getAttributes();
            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            categories.add(new Categories(attributes.getNamedItem("id").getNodeValue(), attributes.getNamedItem("parentId").getNodeValue()));

            for (Categories categories : categories)
                System.out.println(String.format(" id - %s, parentId - %s, name categories ", categories.getId(), categories.getParentId()));
        }

        Shop shop = new Shop(getDocument());

        System.out.println(Arrays.toString(shop.categories.getItems()));
    }



}
