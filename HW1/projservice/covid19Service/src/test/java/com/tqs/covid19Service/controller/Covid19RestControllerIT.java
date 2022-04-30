package com.tqs.covid19Service.controller;

import java.util.Arrays;
import java.util.List;

import com.tqs.covid19Service.Covid19ServiceApplication;
import com.tqs.covid19Service.model.Cache;
import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.KeyCacheHistoryWithDay;
import com.tqs.covid19Service.model.Statistic;
import com.tqs.covid19Service.service.Covid19Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Covid19ServiceApplication.class)
@AutoConfigureMockMvc
class Covid19RestControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Covid19Service covid19Service;

    @AfterEach
    public void resetCache() {
        covid19Service.clearCache();
    }

    @Test
    void givenCountries_whenGetCountries_thenStatus200ThroughCache() throws Exception {
        createTestCountry("Portugal", "Spain");

        mvc.perform(get("/api/countries").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("Portugal")))
                .andExpect(jsonPath("$[1].name", is("Spain")));
    }

    @Test
    void givenCountries_whenGetCountries_thenStatus200ThroughExternalAPI() throws Exception {
        mvc.perform(get("/api/countries").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(233))))
                .andExpect(jsonPath("$[0].name", is("Afghanistan")))
                .andExpect(jsonPath("$[232].name", is("Zimbabwe")));
    }

    @Test
    void getCacheStatisticsTest() throws Exception {

        //Devido ao dois testes anteriores tenho 1 misse e 1 hit, eu limpo a cache mas os valores do hits, misses e requests nao
        mvc.perform(get("/api/cache").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hits", is(1)))
                .andExpect(jsonPath("$.misses", is(1)))
                .andExpect(jsonPath("$.requests", is(2)));
    }

    @Test
    void whenValidCountryAndDay_thenStatus200WithHistoryThroughExternalAPI() throws Exception {
        mvc.perform(get("/api/history/Portugal/2020-06-02").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].country", is("Portugal")))
                .andExpect(jsonPath("$[1].country", is("Portugal")));
    }

    @Test
    void whenValidCountryAndDay_thenStatus200WithHistoryThroughCache() throws Exception {
        createTestHistory();

        mvc.perform(get("/api/history/Portugal/2020-06-02").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].country", is("Portugal")))
                .andExpect(jsonPath("$[1].country", is("Portugal")));
    }

    @Test
    void whenInvalidCountryAndDay_thenStatus200() throws Exception {

        mvc.perform(get("/api/history/x/x").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void whenValidCountry_thenStatus200WithHistoryThroughCache() throws Exception {
        createTestHistory();

        mvc.perform(get("/api/history/Portugal").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].country", is("Portugal")))
                .andExpect(jsonPath("$[1].country", is("Portugal")));
    }

    @Test
    void whenValidCountry_thenStatus200WithHistoryExternalAPI() throws Exception {

        mvc.perform(get("/api/history/Portugal").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].country", is("Portugal")))
                .andExpect(jsonPath("$[1].country", is("Portugal")));
    }


    @Test
    void whenInvalidCountry_thenStatus200() throws Exception {

        mvc.perform(get("/api/history/x").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    private void createTestCountry(String name1, String name2) {
        Country country1 = new Country(name1);
        Country country2 = new Country(name2);
        List<Country> allCountries = Arrays.asList(country1, country2);

        covid19Service.addValue_cacheCountry(allCountries);
    }

    private void createTestHistory() {
        Statistic statistic1 = new Statistic("Europe", "Portugal", 10198931, "+195", 11590, 58, 19869, "3225", 32895, "+12", "141", 1436, "79657", 812415, "2020-06-02", "2020-06-02T12:45:07+00:00");
        Statistic statistic2 = new Statistic("Europe", "Portugal", 10199012, "+195", 11591, 57, 19868, "3224", 32894, "+11", "140", 1435, "79656", 812414, "2020-06-02", "2020-06-02T12:00:06+00:00");

        List<Statistic> allStatistic = Arrays.asList(statistic1, statistic2);

        covid19Service.addValue_cacheHistory_withDay(new KeyCacheHistoryWithDay("Portugal", "2020-06-02"), allStatistic);
        covid19Service.addValue_cacheHistory_withoutDay("Portugal", allStatistic);
    }

    
}
