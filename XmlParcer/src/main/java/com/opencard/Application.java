package com.opencard;

import com.opencard.Mapping.Mapping;
import com.opencard.Utils.DbConnector;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        DbConnector connector = new DbConnector();
        connector.ConnectionOpen();
        Mapping mapping = new Mapping();
        mapping.DumpTable();

    }


}

