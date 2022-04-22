package com.tqs.covid19Service.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.Statistic;
import com.tqs.covid19Service.service.CountryService;
import com.tqs.covid19Service.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Covid19Controller {
    @Autowired
    public HistoryService historyService;

    @Autowired
    public CountryService countryService;


    @GetMapping("/history/{country}")
    public List<Statistic> getHistory(@PathVariable String country) throws IOException, URISyntaxException, InterruptedException {
        return historyService.getHistory(country);
    }

    //day-> (YYYY-MM-DD)
    @GetMapping("/history/{country}/{day}") //assim que se passa 2 argumentos??
    public List<Statistic> getHistory(@PathVariable String country, @PathVariable String day) throws IOException, URISyntaxException, InterruptedException {
        return historyService.getHistory(country, day);
    }

    @GetMapping("/countries")
    public List<Country> getCountries() throws IOException, URISyntaxException, InterruptedException {
        return countryService.getCountries();
    }

}
