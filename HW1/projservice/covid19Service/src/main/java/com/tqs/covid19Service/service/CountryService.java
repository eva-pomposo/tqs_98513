package com.tqs.covid19Service.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.tqs.covid19Service.model.Country;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private static final String baseURL =  "https://covid-193.p.rapidapi.com";

    public List<Country> getCountries() throws URISyntaxException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://covid-193.p.rapidapi.com/countries"))
		.header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
		.header("X-RapidAPI-Key", "886b841662msh40d546b46fa2f68p1573e7jsndafa7121ef3a")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        List<Country> countries = convertStringtoListCountries(response.body());
        
        return countries;
    }

    public List<Country> convertStringtoListCountries(String string){
        List<Country> countries = new ArrayList<>(); // array??

        JSONObject jo = new JSONObject(string);

        for (int i = 0; i < jo.getJSONArray("response").length(); i++) {
            countries.add(new Country(jo.getJSONArray("response").getString(i)));
        }

        return countries;
    }  

}