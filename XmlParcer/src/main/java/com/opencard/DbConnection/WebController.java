package com.opencard.DbConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    private JpaRepository repository;

    @GetMapping("/save")
    public String process(TableCategories categories){

        repository.save(categories);
        return "Done";
    }


    @GetMapping("/findall")
    public String findAll(){

        String result = "";

        for(TableCategories cust : repository.findAll()){
            result += cust + "</br>";
        }

        return result;
    }

    @GetMapping("/findbyid")
    public String findById(@RequestParam("id") int id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }

    @GetMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "";

        for(TableCategories cust: repository.findById(2212)){
            result += cust + "</br>";
        }

        return result;
    }
}