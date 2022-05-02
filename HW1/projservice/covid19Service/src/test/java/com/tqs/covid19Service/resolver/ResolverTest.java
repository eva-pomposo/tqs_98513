package com.tqs.covid19Service.resolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.List;

import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.Statistic;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ResolverTest {
    private Resolver resolver;
    Country country;
    Statistic statistic;

    @BeforeEach
    void setUp() {
        resolver = new Resolver();
        country = new Country("Portugal");
        statistic = new Statistic("Asia", "China", 1439323776, "+1824", 26774, 318, 175431, "144", 207081, "+48", "3", 4876, "111163", 160000000, "2022-04-27", "2022-04-27T19:45:05+00:00");
    }

    @Test
    void callEXternalAPI_InvalidUrl() {
        assertThrows(ConnectException.class, () -> {
            resolver.callEXternalAPI("xx");
        });
    }

    @Test
    void callEXternalAPI_ValidUrl() throws URISyntaxException, IOException, InterruptedException {
        assertThat(resolver.callEXternalAPI("/countries")).isNotEmpty();
    }

    @Test
    void convertStringtoListCountriesTest(){
        List<Country> countries = resolver.convertStringtoListCountries("{'response':['Portugal']}");
        assertEquals(1, countries.size());
        assertEquals(true, countries.contains(country));
    }

    @Test
    void convertStringtoListCountries_InvalidDataTest(){
        assertThrows(JSONException.class, () -> {
            resolver.convertStringtoListCountries("xx");
        });

    }

    @Test
    void convertStringtoHistoryTest(){
        List<Statistic> statistics = resolver.convertStringtoHistory("{'response':[{'continent':'Asia','country':'China','population':1439323776,'cases':{'new':'+1824','active':26774,'critical':318,'recovered':175431,'1M_pop':'144','total':207081},'deaths':{'new':'+48','1M_pop':'3','total':4876},'tests':{'1M_pop':'111163','total':160000000},'day':'2022-04-27','time':'2022-04-27T19:45:05+00:00'}]}");
        assertEquals(1, statistics.size());
        assertEquals(true, statistics.contains(statistic));
    }
}