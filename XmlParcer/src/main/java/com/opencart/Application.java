package com.opencart;

import com.opencart.Utils.ImageController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        ImageController controller = new ImageController();
        String uri = controller.Download("http://ilgc-group.com/upload/iblock/8f4/b6a2c3bb_d043_11e7_80ce_00155d00460e_a05143e1_d049_11e7_80ce_00155d00460e.jpg");
        System.out.println(uri);
        StartLoader startLoader = new StartLoader();
        startLoader.LoadDataBaseData();
    }
}

