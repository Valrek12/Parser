package com.opencart;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        StartLoader startLoader = new StartLoader();
        startLoader.LoadDataBaseData();
    }


}

