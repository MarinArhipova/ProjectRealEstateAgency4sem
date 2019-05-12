package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/home")
    public String getHomePage(ModelMap modelMap) {
        return "home";
    }
}
