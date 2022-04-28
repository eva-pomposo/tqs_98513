package com.tqs.covid19Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.tqs.covid19Service.service.Covid19Service;
import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.Statistic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Covid19Controller.class)
class Covid19ControllerTest {
    @Autowired
    private MockMvc mvc;   

    @MockBean
    private Covid19Service service;

    @Test
    void givenManyCountries_whenGetCountries_thenReturnJsonArray() throws Exception {
        Country country1 = new Country("Portugal");
        Country country2 = new Country("Albania");
        Country country3 = new Country("Angola");

        List<Country> allCountries = Arrays.asList(country1, country2, country3);

        when( service.getCountries()).thenReturn(allCountries);

        mvc.perform(
            get("/api/countries").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is(country1.getName())))
            .andExpect(jsonPath("$[1].name", is(country2.getName())))
            .andExpect(jsonPath("$[2].name", is(country3.getName())));

        verify(service, times(1)).getCountries();
    }

    @Test
    void givenManyStatistics_whenGetHistoryByCountry_thenReturnJsonArray() throws Exception {
        Statistic statistic1 = new Statistic("Europe", "Portugal", 10198931, "+195", 11590, 58, 19869, "3225", 32895, "+12", "141", 1436, "79657", 812415, "2020-06-02", "2020-06-02T12:45:07+00:00");
        Statistic statistic2 = new Statistic("Europe", "Portugal", 10199012, "+195", 11591, 57, 19868, "3224", 32894, "+11", "140", 1435, "79656", 812414, "2020-06-03", "2020-06-03T12:00:06+00:00");
        List<Statistic> statistics = Arrays.asList(statistic1, statistic2);

        when( service.getHistory("Portugal")).thenReturn(statistics);
     
        mvc.perform(
            get("/api/history/Portugal").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].country", is(statistic1.getCountry())))
            .andExpect(jsonPath("$[1].country", is(statistic2.getCountry())))
            .andExpect(jsonPath("$[0].new_cases", is(statistic1.getNew_cases())))
            .andExpect(jsonPath("$[1].new_cases", is(statistic2.getNew_cases())));

        verify(service, times(1)).getHistory("Portugal");
    }

    //Quando procuro a history do pais x retorna uma lista vazia, devia ser assim ou returnar uma lista vazia??
    /*
    @Test
    void testGetHistoryByCountry_withCountryInvalid() throws Exception {
        mvc.perform(
            get("/api/history/Portugal"))
            .andExpect(status().isNotFound());

        verify(service, times(1)).getHistory("Portugal");
    }
    */

    @Test
    void testGetHistoryByCountry_withCountryInvalid() throws Exception {
        mvc.perform(
            get("/api/history/Portugal").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));

        verify(service, times(1)).getHistory("Portugal");
    }

    @Test
    void givenManyStatistics_whenGetHistoryByCountryAndByDay_thenReturnJsonArray() throws Exception {
        Statistic statistic1 = new Statistic("Europe", "Portugal", 10198931, "+195", 11590, 58, 19869, "3225", 32895, "+12", "141", 1436, "79657", 812415, "2020-06-02", "2020-06-02T12:45:07+00:00");
        Statistic statistic2 = new Statistic("Europe", "Portugal", 10199012, "+195", 11591, 57, 19868, "3224", 32894, "+11", "140", 1435, "79656", 812414, "2020-06-02", "2020-06-02T12:00:06+00:00");
        List<Statistic> statistics = Arrays.asList(statistic1, statistic2);

        when( service.getHistory("Portugal", "2020-06-02")).thenReturn(statistics);
     
        mvc.perform(
            get("/api/history/Portugal/2020-06-02").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].country", is(statistic1.getCountry())))
            .andExpect(jsonPath("$[1].country", is(statistic2.getCountry())))
            .andExpect(jsonPath("$[0].new_cases", is(statistic1.getNew_cases())))
            .andExpect(jsonPath("$[1].new_cases", is(statistic2.getNew_cases())))
            .andExpect(jsonPath("$[0].day", is(statistic1.getDay())))
            .andExpect(jsonPath("$[1].day", is(statistic2.getDay())));

        verify(service, times(1)).getHistory("Portugal", "2020-06-02");
    }

    //Quando procuro a history do pais x retorna uma lista vazia, devia ser assim ou returnar uma lista vazia??
    //Esta completo ??
    @Test
    void testGetHistoryByCountryAndByDay_withParametersInvalid() throws Exception {
        mvc.perform(
            get("/api/history/Portugal/2020-06-02").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));

            verify(service, times(1)).getHistory("Portugal", "2020-06-02");
    }

    @Test
    void getCacheStatisticsTest() throws Exception {
        HashMap<String, Integer> statistics = new HashMap<>();
        statistics.put("hits", 1);
        statistics.put("misses", 1);
        statistics.put("requests", 2);

        when( service.getCacheStatistics()).thenReturn(statistics);

        mvc.perform(
            get("/api/cache").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hits", is(1)))
            .andExpect(jsonPath("$.misses", is(1)))
            .andExpect(jsonPath("$.requests", is(2)));

        verify(service, times(1)).getCacheStatistics();

    }
}