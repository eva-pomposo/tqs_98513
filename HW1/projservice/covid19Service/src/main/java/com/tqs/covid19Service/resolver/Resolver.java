package com.tqs.covid19Service.resolver;

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
import com.tqs.covid19Service.model.Statistic;

//@Component
public class Resolver {

    public String callEXternalAPI(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://covid-193.p.rapidapi.com" + url))
		.header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
		.header("X-RapidAPI-Key", "886b841662msh40d546b46fa2f68p1573e7jsndafa7121ef3a")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public List<Country> convertStringtoListCountries(String string){
        List<Country> countries = new ArrayList<>();

        JSONObject jo = new JSONObject(string);

        for (int i = 0; i < jo.getJSONArray("response").length(); i++) {
            countries.add(new Country(jo.getJSONArray("response").getString(i)));
        }

        return countries;
    }

    public List<Statistic> convertStringtoHistory(String string){
        List<Statistic> statistics = new ArrayList<>();

        JSONObject jo = new JSONObject(string);

        for (int i = 0; i < jo.getJSONArray("response").length(); i++) {
            Statistic statistic = new Statistic();

            JSONObject jsonObjectResponse = jo.getJSONArray("response").getJSONObject(i);
            
            if (!jsonObjectResponse.get("continent").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setContinent(jsonObjectResponse.getString("continent"));
            }
            
            if (!jsonObjectResponse.get("country").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setCountry(jsonObjectResponse.getString("country"));
            }
            
            if (!jsonObjectResponse.get("population").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setPopulation(jsonObjectResponse.getInt("population"));
            }

            JSONObject cases = jsonObjectResponse.getJSONObject("cases");

            if (!cases.get("new").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setNew_cases(cases.getString("new"));
            } 

            if (!cases.get("active").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setActive_cases(cases.getInt("active"));
            } 

            if (!cases.get("recovered").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setRecovered_cases(cases.getInt("recovered"));
            } 

            if (!cases.get("critical").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setCritical_cases(cases.getInt("critical"));
            } 

            if (!cases.get("1M_pop").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setPop1m_cases(cases.getString("1M_pop"));
            } 

            if (!cases.get("total").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setTotal_cases(cases.getInt("total"));
            } 

            JSONObject deaths = jsonObjectResponse.getJSONObject("deaths");

            if (!deaths.get("new").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setNew_deaths(deaths.getString("new"));
            } 

            if (!deaths.get("1M_pop").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setPop1m_deaths(deaths.getString("1M_pop"));
            } 

            if (!deaths.get("total").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setTotal_deaths(deaths.getInt("total"));
            } 

            JSONObject tests = jsonObjectResponse.getJSONObject("tests");

            if (!tests.get("1M_pop").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setPop1m_tests(tests.getString("1M_pop"));
            } 

            if (!tests.get("total").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setTotal_tests(tests.getInt("total"));
            } 

            if (!jsonObjectResponse.get("day").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setDay(jsonObjectResponse.getString("day"));
            }

            if (!jsonObjectResponse.get("time").getClass().getName().equals("org.json.JSONObject$Null")) { 
                statistic.setTime(jsonObjectResponse.getString("time"));
            }

            statistics.add(statistic);
        }

        return statistics;
    }  
}
