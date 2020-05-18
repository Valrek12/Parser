package com.opencart.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String parser() {
        return "/parser";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
