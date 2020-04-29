package com.opencart.utils;

import org.jetbrains.annotations.Contract;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlParserController {

    private String Complects;
    private String Specifications;
    private String fragment;


    @Contract(pure = true)
    public HtmlParserController(String fragment){
      this.Complects = new String();
      this.Specifications = new String();
      this.fragment = fragment;
    }

    /**
     * Метод получение Комплектаций товара
     * @return
     */
    public String getComplects(){
        Document document = Jsoup.parseBodyFragment(fragment);
        Elements dc = document.select("p");
        for(int i=0; i< dc.size(); i++) {
            String tag = dc.get(i).text();
            if (tag.indexOf("Комплектация:") != -1) {
                String[] stringArray = tag.split("—");
                Complects = String.join(",", stringArray);
            }
        }
        return Complects;
    }

    /**
     * Метод получение характеристик
     * @return Array<List>Specification</List>
     */
    public String getSpecifications(){
        Document document = Jsoup.parseBodyFragment(fragment);
        Elements dc = document.select("p");
        for(int i=0; i< dc.size(); i++) {
            String tag = dc.get(i).text();
            if (tag.indexOf("Характеристики:") != -1) {
                String[] stringArray = tag.split("—");
                Specifications = String.join(",", stringArray);
            }
        }
        return Specifications;
    }
}
