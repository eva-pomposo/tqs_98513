package com.tqs.covid19Service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheTest {
    private Cache cache;
    Statistic statistic1;
    Statistic statistic2;
    List<Statistic> allStatistic1;
    Statistic statistic3;
    Statistic statistic4;
    List<Statistic> allStatistic2;
    KeyCacheHistoryWithDay key;
    Country country1;
    Country country2;
    Country country3;
    List<Country> allCountries;

    @BeforeEach
    void setUp() {
        cache = new Cache(1);

        statistic1 = new Statistic("Europe", "Portugal", 10198931, "+195", 11590, 58, 19869, "3225", 32895, "+12", "141", 1436, "79657", 812415, "2020-06-02", "2020-06-02T12:45:07+00:00");
        statistic2 = new Statistic("Europe", "Portugal", 10199012, "+195", 11591, 57, 19868, "3224", 32894, "+11", "140", 1435, "79656", 812414, "2020-06-02", "2020-06-02T12:00:06+00:00");
        allStatistic1 = Arrays.asList(statistic1, statistic2);

        statistic3 = new Statistic("Europe", "Spain", 10198931, "+195", 11590, 58, 19869, "3225", 32895, "+12", "141", 1436, "79657", 812415, "2020-06-02", "2020-06-02T12:45:07+00:00");
        statistic4 = new Statistic("Europe", "Spain", 10199012, "+195", 11591, 57, 19868, "3224", 32894, "+11", "140", 1435, "79656", 812414, "2020-06-02", "2020-06-02T12:00:06+00:00");
        allStatistic2 = Arrays.asList(statistic3, statistic4);

        key = new KeyCacheHistoryWithDay("Portugal", "2020-06-02");

        country1 = new Country("Portugal");
        country2 = new Country("Albania");
        country3 = new Country("Angola");
        allCountries = Arrays.asList(country1, country2, country3);
    }

    @AfterEach
    void tearDown(){
        cache.clearCache();
    }

    @Test
    void addValue_cacheHistory_withoutDayTest(){
        assertEquals(0, cache.getCacheHistory_withoutDay().size() );
        cache.addValue_cacheHistory_withoutDay("Portugal", allStatistic1);
        assertEquals(1, cache.getCacheHistory_withoutDay().size() );
        assertEquals(true, cache.getCacheHistory_withoutDay().containsKey("Portugal") );
        assertEquals(true, cache.getCacheHistory_withoutDay().get("Portugal").equals(allStatistic1) );
    }

    @Test
    void addValue_cacheHistory_withDayTest(){
        assertEquals(0, cache.getCacheHistory_withDay().size() );
        cache.addValue_cacheHistory_withDay(key, allStatistic1);
        assertEquals(1, cache.getCacheHistory_withDay().size() );
        assertEquals(true, cache.getCacheHistory_withDay().containsKey(key) );
        assertEquals(true, cache.getCacheHistory_withDay().get(key).equals(allStatistic1) );
    }

    @Test
    void addValue_cacheCountryTest(){
        assertEquals(0, cache.getCacheCountry().size() );
        cache.addValue_cacheCountry(allCountries);
        assertEquals(3, cache.getCacheCountry().size() );
        assertEquals(true, cache.getCacheCountry().equals(allCountries));
    }

    @Test
    void deleteValue_cacheHistory_withoutDayTest(){
        assertEquals(0, cache.getCacheHistory_withoutDay().size() );
        cache.addValue_cacheHistory_withoutDay("Portugal", allStatistic1);
        cache.deleteValue_cacheHistory_withoutDay("Portugal");
        assertEquals(0, cache.getCacheHistory_withoutDay().size() );
        assertEquals(false, cache.getCacheHistory_withoutDay().containsKey("Portugal") );
    }

    @Test
    void deleteValue_cacheHistory_withDayTest(){
        assertEquals(0, cache.getCacheHistory_withDay().size() );
        cache.addValue_cacheHistory_withDay(key, allStatistic1);
        cache.deleteValue_cacheHistory_withDay(key);
        assertEquals(0, cache.getCacheHistory_withDay().size() );
        assertEquals(false, cache.getCacheHistory_withDay().containsKey(key) );
    }

    @Test
    void deleteValue_cacheCountryTest(){
        assertEquals(0, cache.getCacheCountry().size() );
        cache.addValue_cacheCountry(allCountries);
        cache.deleteValue_cacheCountry();
        assertEquals(0, cache.getCacheCountry().size() );
        assertEquals(false, cache.getCacheCountry().equals(allCountries));
    }

    @Test
    void getStatisticsWithoutDay_FromCacheTest(){
        cache.addValue_cacheHistory_withoutDay("Portugal", allStatistic1);
        List<Statistic> statistics1 = cache.getStatisticsWithoutDay_FromCache("Portugal");
        List<Statistic> statistics2 = cache.getStatisticsWithoutDay_FromCache("xx");
        assertEquals(1, cache.getHits());
        assertEquals(1, cache.getMisses());
        assertEquals(2, cache.getRequests());
        assertEquals(statistics1, allStatistic1);
        assertEquals(statistics2, null);
    }

    @Test
    void getStatisticsWithDay_FromCacheTest(){
        cache.addValue_cacheHistory_withDay(key, allStatistic1);
        List<Statistic> statistics1 = cache.getStatisticsWithDay_FromCache(key);
        List<Statistic> statistics2 = cache.getStatisticsWithDay_FromCache(new KeyCacheHistoryWithDay("xx", "2001-12-12"));
        assertEquals(1, cache.getHits());
        assertEquals(1, cache.getMisses());
        assertEquals(2, cache.getRequests());
        assertEquals(statistics1, allStatistic1);
        assertEquals(statistics2, null);
    }

    @Test
    void getCountries_FromCacheTest(){
        List<Country> countries1 = cache.getCountries_FromCache();
        cache.addValue_cacheCountry(allCountries);
        List<Country> countries2 = cache.getCountries_FromCache();
        assertEquals(1, cache.getHits());
        assertEquals(1, cache.getMisses());
        assertEquals(2, cache.getRequests());
        assertEquals(countries1, null);
        assertEquals(countries2, allCountries);
    }

    @Test
    void clearCacheData_afterLifeTimeTest() throws InterruptedException {
        cache.addValue_cacheHistory_withoutDay("Portugal", allStatistic1);
        cache.addValue_cacheHistory_withDay(key, allStatistic1);
        cache.addValue_cacheCountry(allCountries);
        Thread.sleep(3000);
        assertEquals(0, cache.getCacheHistory_withoutDay().size() );
        assertEquals(0, cache.getCacheHistory_withDay().size() );
        assertEquals(0, cache.getCacheCountry().size() );
    }
}
