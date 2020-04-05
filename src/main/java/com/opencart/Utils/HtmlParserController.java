package com.opencart.Utils;

import org.jetbrains.annotations.Contract;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HtmlParserController {

    private ArrayList<String> Complects;
    private ArrayList<String> Specifications;
    private String fragment;


    @Contract(pure = true)
    public HtmlParserController(String fragment){
      this.Complects = new ArrayList<>();
      this.Specifications = new ArrayList<>();
      this.fragment = fragment;
    }

    /**
     * Метод получение Комплектаций товара
     * @return
     */
    public ArrayList<String> getComplects(){
        Document document = Jsoup.parseBodyFragment(fragment);
        Elements dc = document.select("p");
        for(int i=0; i< dc.size(); i++) {
            String tag = dc.get(i).text();
            if (tag.indexOf("Комплектация:") != -1) {
                String[] stringArray = tag.split("—");
                for (int k = 1; k < stringArray.length; k++) {
                    Complects.add(stringArray[k]);
                }
            }
        }
        return Complects;
    }

    /**
     * Метод получение характеристик
     * @return Array<List>Specification</List>
     */
    public ArrayList<String> getSpecifications(){
        Document document = Jsoup.parseBodyFragment(fragment);
        Elements dc = document.select("p");
        for(int i=0; i< dc.size(); i++) {
            String tag = dc.get(i).text();
            if (tag.indexOf("Харакiтеристики:") != -1) {
                String[] stringArray = tag.split("—");
                for (int k = 1; k < stringArray.length; k++) {
                    Specifications.add(stringArray[k]);
                }
            }
        }
        return Specifications;
    }
}
