package com.opencard;

import com.opencard.DbConnection.TableCategories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SpringApplication.run(Application.class, args);
        TableCategories categories = new TableCategories(2229,0, new Date(), new Date());
    }
}
