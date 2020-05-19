package com.opencart.webapp.controller;

import com.opencart.StartLoader;
import com.opencart.webapp.view.ContentTypeToXml;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/settings")
    public String settings() {
        return "/settings";
    }

    @GetMapping("/reporting")
    public String reporting() {
        return "/reporting";
    }

    @GetMapping("/crm")
    public String crm() {
        return "/crm";
    }

    @GetMapping("/parser")
    public String parserForm(Model model) {
        model.addAttribute("parser", new ContentTypeToXml());
        return "/parser";
    }

    @PostMapping("/parser")
    public String parserSubmit(@ModelAttribute ContentTypeToXml contentTypeToXml) throws ParserConfigurationException, SAXException, IOException {
        StartLoader startLoader = new StartLoader();
        return "/result";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
