package com.tqs.covid19Service.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherDays {

    @GetMapping("/otherdays")
    public String getOtherDays(Model model) {
        return "otherDays";
    }
    
}
