package com.opencart.Utils;

import org.jetbrains.annotations.Contract;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HtmlParserController {

    public ArrayList<String> Complects;
    public ArrayList<String> Specifications;


    @Contract(pure = true)
    public HtmlParserController(String fragment){
      this.Complects = new ArrayList<>();
      this.Specifications = new ArrayList<>();
      ParsingFragment(fragment);
    }


    private void ParsingFragment(String fragment){
        Document document = Jsoup.parseBodyFragment(fragment);
        Elements dc = document.select("p");
        for(int i=0; i< dc.size(); i++){
           String tag = dc.get(i).text();
           if(tag.indexOf("Комплектация:") != -1){
            String[] stringArray = tag.split("—");
            for(int k=1; k< stringArray.length; k++){
                Complects.add(stringArray[k]);
            }
           }
           else if(tag.indexOf("Характеристики:") != -1){
               String[] stringArray = tag.split("—");
               for(int k=1; k< stringArray.length; k++){
                   Specifications.add(stringArray[k]);
               }
           }
        }

    }
}
