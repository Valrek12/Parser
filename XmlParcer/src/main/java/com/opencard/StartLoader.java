package com.opencard;

import com.opencard.Dao.Category;
import com.opencard.Dao.CategoryTemp;
import com.opencard.Mapping.Mapping;
import com.opencard.Utils.DbConnector;
import com.opencard.Utils.XmlUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class StartLoader {
    private DbConnector connector;
    private XmlUtils xmlUtils;
    private Mapping mapping;

  public StartLoader() throws ParserConfigurationException, SAXException, IOException {
      this.connector = new DbConnector();
      this.xmlUtils = new XmlUtils();
      this.mapping = new Mapping();
  }

  public void LoadDataBaseData() throws ParserConfigurationException, SAXException, IOException {
      connector.ConnectionOpen();
      CreateDump();
      xmlUtils.DownloadXml(xmlUtils.xmlFIle);
      Category.deleteAll();
      mapping.CategoryMapping();
      connector.ConnectionClose();
  }

  public void CreateDump() throws IOException {
      if(CategoryTemp.findAll()!= null){
          CategoryTemp.deleteAll();
          Mapping mapping = new Mapping();
          mapping.DumpTable();
      }
  }


}
