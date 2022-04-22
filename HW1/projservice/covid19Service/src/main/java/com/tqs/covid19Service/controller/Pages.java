package com.tqs.covid19Service.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tqs.covid19Service.inputsForms.FilterOtherDays;
import com.tqs.covid19Service.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Pages {
    List<Country> countries = new ArrayList<>();
    
    @Autowired
    private Covid19Controller covid19Controller;

    @ModelAttribute("filterForm")
    public FilterOtherDays getFilterObject() {
      return new FilterOtherDays();
    }


    @GetMapping("/otherdays")
    public String getOtherDays(Model model) {

        try {
            countries = covid19Controller.getCountries();
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        
        model.addAttribute("countries", countries);
        return "otherDays";
    }

    @PostMapping("/otherdays/filter")
    public String filter(@ModelAttribute FilterOtherDays filterOtherDays, Model model) {
        model.addAttribute("countries", countries);

        try {
            model.addAttribute("statistics", covid19Controller.getHistory(filterOtherDays.getCountryName()));
            model.addAttribute("countrySelected", filterOtherDays.getCountryName());
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return "otherDays";
    }
    
}
