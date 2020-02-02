package XmlSettings;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shop")
public class Shop {
   private String name;

    public String getName(){
        return  name;
    }
}
