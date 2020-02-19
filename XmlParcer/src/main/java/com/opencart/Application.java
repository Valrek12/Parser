package com.opencart;

import com.opencart.Utils.XmlUtils;
import com.opencart.XmlEntity.XmlOffer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        XmlUtils xmlUtils= new XmlUtils();
        XmlOffer.getOffers(xmlUtils);

        StartLoader startLoader = new StartLoader();
        startLoader.LoadDataBaseData();
    }


}

