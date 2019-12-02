package ru.mylife54.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping
    public String getIndexPage(ModelMap modelMap){
        return "index";
    }

}
