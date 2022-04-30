package com.tqs.covid19Service.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tqs.covid19Service.model.Cache;
import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.KeyCacheHistoryWithDay;

import org.springframework.stereotype.Service;
import com.tqs.covid19Service.model.Statistic;
import com.tqs.covid19Service.resolver.Resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Covid19Service {

    private static final Logger log = LoggerFactory.getLogger(Covid19Service.class);

    private Cache cache = new Cache(60);
    
    private Resolver resolver = new Resolver();

    public void addValue_cacheCountry(List<Country> allCountries){
        cache.addValue_cacheCountry(allCountries);
    }

    public void addValue_cacheHistory_withDay(KeyCacheHistoryWithDay key, List<Statistic> allStatistic){
        cache.addValue_cacheHistory_withDay(key, allStatistic);
    }

    public void addValue_cacheHistory_withoutDay(String key, List<Statistic> allStatistic){
        cache.addValue_cacheHistory_withoutDay(key, allStatistic);
    }

    public void clearCache(){
        cache.clearCache();
    }

    public List<Statistic> getHistory(String country) throws URISyntaxException, IOException, InterruptedException {
        List<Statistic> statistics =  cache.getStatisticsWithoutDay_FromCache(country);

        if (statistics == null) {
            log.info("Get statistics from {} through external API.", country);

            String response = "";
            try {
                response = resolver.callEXternalAPI("/history?country=" + country);
            } catch (Exception e) {
                log.error("There was an error while calling the external API: {}", e.getMessage());
            }


            log.info("Pass the external API response to a created API response.");
            statistics = resolver.convertStringtoHistory(response); 
            log.info("[CACHE] Add {} statistics to cache.", country);
            cache.addValue_cacheHistory_withoutDay(country, statistics);
            return statistics;
        }

        log.info("[CACHE] Get {} statistics through cache.", country);
        return statistics;
    }

    public List<Statistic> getHistory(String country, String day) throws URISyntaxException, IOException, InterruptedException {
        KeyCacheHistoryWithDay key = new KeyCacheHistoryWithDay(country, day);
        List<Statistic> statistics = cache.getStatisticsWithDay_FromCache(key);

        if (statistics == null){
            log.info("Get statistics from {} on day {} through external API.", country, day);

            String response = "";
            try {
                response = resolver.callEXternalAPI("/history?country=" + country + "&day=" + day);
            } catch (Exception e) {
                log.error("There was an error while calling the external API: {}", e.getMessage());
            }

            log.info("Pass the external API response to a created API response.");
            statistics = resolver.convertStringtoHistory(response); 
            log.info("[CACHE] Add statistics from {} on day {} to cache.", country, day);
            cache.addValue_cacheHistory_withDay(key, statistics);
            return statistics;
        }

        log.info("[CACHE] Get statistics from {} on day {} through cache.", country, day);
        return statistics;
    }

    public List<Country> getCountries() throws URISyntaxException, IOException, InterruptedException{
        List<Country> countries = cache.getCountries_FromCache();
        
        if (countries == null) {
            log.info("Get all countries through external API.");

            String response = "";
            try {
                response = resolver.callEXternalAPI("/countries");
            } catch (Exception e) {
                log.error("There was an error while calling the external API: {}", e.getMessage());
            }

            log.info("Pass the external API response to a created API response.");
            countries = resolver.convertStringtoListCountries(response); 
            log.info("[CACHE] Add all countries to cache.");
            cache.addValue_cacheCountry(countries);
            return countries;
        }

        log.info("[CACHE] Get all countries through cache.");
        return countries;
    }

    public Map<String, Integer> getCacheStatistics(){
        HashMap<String, Integer> statistics = new HashMap<>();
        statistics.put("hits", cache.getHits());
        statistics.put("misses", cache.getMisses());
        statistics.put("requests", cache.getRequests());
        return statistics;
    }
}
