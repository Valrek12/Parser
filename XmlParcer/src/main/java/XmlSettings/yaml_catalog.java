package XmlSettings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "yaml_catalog")
public class yaml_catalog {
    @XmlElement
    public Shop shop;

}

