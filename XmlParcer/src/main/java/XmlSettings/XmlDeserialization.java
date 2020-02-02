package XmlSettings;

import Utils.Settings;
import org.jsoup.Jsoup;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class XmlDeserialization {

    private Settings settings;
    private static Shop shop;
    private String xmlFIle;

    private org.jsoup.nodes.Document DownloadXml(String xmlFIle) throws ParserConfigurationException, IOException, SAXException {
        org.jsoup.nodes.Document doc = Jsoup.connect(xmlFIle).get();
        String title = doc.title();
        System.out.println("Title : " + title);
        return doc;
    }
    public XmlDeserialization() throws IOException {
        settings = new Settings();
        xmlFIle = settings.getFile();
    }

    public void Run() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        shop = (Shop) unmarshaller.unmarshal(
                new URL(xmlFIle)
        );
        System.out.println(shop);
    }

}
