package com.opencart;

import com.opencart.Dao.Category;
import com.opencart.Dao.CategoryTemp;
import com.opencart.Mapping.Mapping;
import com.opencart.Utils.DbConnector;
import com.opencart.Utils.XmlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/*
Класс описывающий логику загрузки
 */
public class StartLoader {
    private DbConnector connector;
    private XmlUtils xmlUtils;
    private Mapping mapping;
    private static final Logger logger = LogManager.getLogger();


    public StartLoader() throws IOException {
      this.connector = new DbConnector();
      this.xmlUtils = new XmlUtils();
      this.mapping = new Mapping();
  }

  public void LoadDataBaseData() throws IOException {
      connector.ConnectionOpen();
      CreateDump();
      xmlUtils.DownloadXml(xmlUtils.xmlFIle);
      logger.debug(String.format("файл с именем -%s успешно выгружен", xmlUtils.nameFile));
      Category.deleteAll();
      mapping.CategoryMapping();
      connector.ConnectionClose();
      xmlUtils.DeleteXml();
      logger.debug(String.format("файл с именем -%s успешно удален", xmlUtils.nameFile));
  }

  private void CreateDump() throws IOException {
      if(CategoryTemp.findAll()!= null){
          CategoryTemp.deleteAll();
          Mapping mapping = new Mapping();
          mapping.DumpTable();
      }
  }
}
