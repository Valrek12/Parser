import XmlSettings.XmlDeserialization;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XmlParcelRuining {
    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException, TransformerException {
        XmlDeserialization XmlPaced = new XmlDeserialization();
        XmlPaced.Run();
    }
}
