package XmlSettings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;

public class Categories {
    private String id;
    private String parentId;

    public Categories(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }


    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }


}