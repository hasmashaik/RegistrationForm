package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //@Controller---> Handles HTTP web requests and returns views
public class HomeController {
    @GetMapping("/") //Maps the root URL (/) to a method
    public String home() {
        return "index";  //Loads the index.html page
    }
}
