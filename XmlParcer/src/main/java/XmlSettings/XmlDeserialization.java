package XmlSettings;

import Utils.Settings;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlDeserialization {

    private Settings settings;
    private String xmlFIle;
    private String nameFile;

    private File DownloadXml(String xmlFIle) throws ParserConfigurationException, IOException, SAXException, TransformerException {DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
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

    public Document Run() throws JAXBException, IOException, SAXException, ParserConfigurationException, TransformerException {
        DownloadXml(xmlFIle);
        File file = new File(nameFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();
        System.out.println(nameFile);
        Document document = builder.parse(file);
        NodeList employeeElements = document.getDocumentElement().getElementsByTagName("Shop");
        System.out.println(employeeElements);
        return  document;

    }

}
