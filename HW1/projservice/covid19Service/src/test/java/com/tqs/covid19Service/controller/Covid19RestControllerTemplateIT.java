package com.tqs.covid19Service.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.KeyCacheHistoryWithDay;
import com.tqs.covid19Service.model.Statistic;
import com.tqs.covid19Service.service.Covid19Service;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Covid19RestControllerTemplateIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Covid19Service covid19Service;

    @AfterEach
    public void resetCache() {
        covid19Service.clearCache();
    }

    @Test
    void givenCountries_whenGetCountries_thenStatus200ThroughCache() {
        createTestCountry("Portugal", "Spain");

        ResponseEntity<List<Country>> response = restTemplate
        .exchange("/api/countries", HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2).extracting(Country::getName).containsExactly("Portugal", "Spain");
    }

    @Test
    void givenCountries_whenGetCountries_thenStatus200ThroughExternalAPI() {
        ResponseEntity<List<Country>> response = restTemplate
        .exchange("/api/countries", HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(233).extracting(Country::getName).contains("Afghanistan", "Zimbabwe");
        
    }

    @Test
    void getCacheStatisticsTest() {

        //Devido ao dois testes anteriores tenho 1 misse e 1 hit, eu limpo a cache mas os valores do hits, misses e requests nao
        ResponseEntity<Map<String, Integer>> response = restTemplate
        .exchange("/api/cache", HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Integer>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(3).containsEntry("hits", 1).containsEntry("misses", 1).containsEntry("requests", 2);
    }

    @Test
    void whenValidCountryAndDay_thenStatus200WithHistoryThroughExternalAPI() {

        ResponseEntity<List<Statistic>> response = restTemplate
        .exchange("/api/history/Portugal/2020-06-02", HttpMethod.GET, null, new ParameterizedTypeReference<List<Statistic>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2).extracting(Statistic::getCountry).containsExactly("Portugal", "Portugal");
    }

    @Test
    void whenValidCountryAndDay_thenStatus200WithHistoryThroughCache() {
        createTestHistory();

        ResponseEntity<List<Statistic>> response = restTemplate
        .exchange("/api/history/Portugal/2020-06-02", HttpMethod.GET, null, new ParameterizedTypeReference<List<Statistic>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2).extracting(Statistic::getCountry).containsExactly("Portugal", "Portugal");
    }

    @Test
    void whenInvalidCountryAndDay_thenStatus200() {

        ResponseEntity<List<Statistic>> response = restTemplate
        .exchange("/api/history/x/x", HttpMethod.GET, null, new ParameterizedTypeReference<List<Statistic>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(0);
    }

    @Test
    void whenValidCountry_thenStatus200WithHistoryThroughCache() {
        createTestHistory();

        ResponseEntity<List<Statistic>> response = restTemplate
        .exchange("/api/history/Portugal", HttpMethod.GET, null, new ParameterizedTypeReference<List<Statistic>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2).extracting(Statistic::getCountry).containsExactly("Portugal", "Portugal");
    }

    @Test
    void whenValidCountry_thenStatus200WithHistoryExternalAPI() {

        ResponseEntity<List<Statistic>> response = restTemplate
        .exchange("/api/history/Portugal", HttpMethod.GET, null, new ParameterizedTypeReference<List<Statistic>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1592).extracting(Statistic::getCountry).contains("Portugal", "Portugal");
    }

    @Test
    void whenInvalidCountry_thenStatus200() {

        ResponseEntity<List<Statistic>> response = restTemplate
        .exchange("/api/history/x", HttpMethod.GET, null, new ParameterizedTypeReference<List<Statistic>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(0);

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
