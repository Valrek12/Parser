package com.opencart.webControllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParserController {
    @GetMapping("/parser")
    public String getForm(@NotNull Model model){
        model.addAttribute("parser", new ContentTypeToXml());
        return "parser";
    }

    @PostMapping("/parser")
    public String Submit(@ModelAttribute ContentTypeToXml contentTypeToXml){
        return "result";
    }
}
