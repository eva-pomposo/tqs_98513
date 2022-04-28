package com.tqs.covid19Service.controller;

import java.util.Arrays;
import java.util.List;

import com.tqs.covid19Service.Covid19ServiceApplication;
import com.tqs.covid19Service.model.Cache;
import com.tqs.covid19Service.model.Country;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Covid19ServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class Covid19RestControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Cache cache;

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

    private void createTestCountry(String name1, String name2) {
        Country country1 = new Country(name1);
        Country country2 = new Country(name2);
        List<Country> allCountries = Arrays.asList(country1, country2);

        cache.addValue_cacheCountry(allCountries);
    }

    
}
