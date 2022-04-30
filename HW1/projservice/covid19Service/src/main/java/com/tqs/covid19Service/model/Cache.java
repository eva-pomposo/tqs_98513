package com.tqs.covid19Service.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cache {
    private static final Logger log = LoggerFactory.getLogger(Cache.class);

    private int hits = 0;
    private int misses = 0;
    private int requests = 0;

    private Map<String, List<Statistic>> cacheHistory_withoutDay = new HashMap<>();
    private Map<KeyCacheHistoryWithDay, List<Statistic>> cacheHistory_withDay = new HashMap<>();
    private List<Country> cacheCountry = new ArrayList<>();
    private Map<String, Long> timeToLive_cacheHistory_withoutDay = new HashMap<>();
    private Map<KeyCacheHistoryWithDay, Long> timeToLive_cacheHistory_withDay = new HashMap<>();
    private Long timeToLive_cacheCountry = 0L;

    private long lifeTime;

    public Cache(int lifeTime) {
        this.lifeTime = lifeTime * 1000;
        clearCacheData_afterLifeTime();
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getRequests() {
        return requests;
    }

    public Map<String, List<Statistic>> getCacheHistory_withoutDay() {
        return cacheHistory_withoutDay;
    }

    public Map<KeyCacheHistoryWithDay, List<Statistic>> getCacheHistory_withDay() {
        return cacheHistory_withDay;
    }

    public List<Country> getCacheCountry() {
        return cacheCountry;
    }
    
    public boolean addValue_cacheHistory_withoutDay(String key, List<Statistic> statistics){
        timeToLive_cacheHistory_withoutDay.put(key, System.currentTimeMillis() + this.lifeTime);
        cacheHistory_withoutDay.put(key, statistics);
        return true;
    }

    public boolean addValue_cacheHistory_withDay(KeyCacheHistoryWithDay key, List<Statistic> statistics){
        timeToLive_cacheHistory_withDay.put(key, System.currentTimeMillis() + this.lifeTime);
        cacheHistory_withDay.put(key, statistics);
        return true;
    }

    public boolean addValue_cacheCountry(List<Country> countries){
        timeToLive_cacheCountry = System.currentTimeMillis() + this.lifeTime;
        cacheCountry = countries;
        return true;
    }

    public boolean deleteValue_cacheHistory_withoutDay(String key){
        if(cacheHistory_withoutDay.containsKey(key)){
            cacheHistory_withoutDay.remove(key);
            timeToLive_cacheHistory_withoutDay.remove(key);
            return true;
        }
        return false;
    }

    public boolean deleteValue_cacheHistory_withDay(KeyCacheHistoryWithDay key){
        if(cacheHistory_withDay.containsKey(key)){
            cacheHistory_withDay.remove(key);
            timeToLive_cacheHistory_withDay.remove(key);
            return true;
        }
        return false;
    }

    public boolean deleteValue_cacheCountry(){
        cacheCountry = new ArrayList<>();
        timeToLive_cacheCountry = 0L;
        return true;
    }

    public List<Statistic> getStatisticsWithoutDay_FromCache(String key){
        if(cacheHistory_withoutDay.containsKey(key) && timeToLive_cacheHistory_withoutDay.get(key) > System.currentTimeMillis()){
            this.requests++;
            this.hits++;
            return cacheHistory_withoutDay.get(key);
        }
        this.requests++;
        this.misses++;
        return null;
    }

    public List<Statistic> getStatisticsWithDay_FromCache(KeyCacheHistoryWithDay key){
        if(cacheHistory_withDay.containsKey(key) && timeToLive_cacheHistory_withDay.get(key) > System.currentTimeMillis()){
            this.requests++;
            this.hits++;
            return cacheHistory_withDay.get(key);
        }
        this.requests++;
        this.misses++;
        return null;
    }

    public List<Country> getCountries_FromCache(){
        if( !cacheCountry.isEmpty() && timeToLive_cacheCountry > System.currentTimeMillis()){
            this.requests++;
            this.hits++;
            return cacheCountry;
        }
        this.requests++;
        this.misses++;
        return null;
    }

    public Thread clearCacheData_afterLifeTime(){
        Thread thread = new Thread(){
            @Override
            public void run(){
                while (true){

                    for(String key: cacheHistory_withoutDay.keySet()){
                        if(timeToLive_cacheHistory_withoutDay.get(key) < System.currentTimeMillis()){
                            log.info("[CACHE] Delete {} statistics to cache.", key);
                            deleteValue_cacheHistory_withoutDay(key);
                        }
                    }

                    for(KeyCacheHistoryWithDay key: cacheHistory_withDay.keySet()){
                        if(timeToLive_cacheHistory_withDay.get(key) < System.currentTimeMillis()){
                            log.info("[CACHE] Delete statistics from {} on day {} to cache.", key.getCountry(), key.getDay());
                            deleteValue_cacheHistory_withDay(key);
                        }
                    }

                    if( !timeToLive_cacheCountry.equals(0L) && timeToLive_cacheCountry < System.currentTimeMillis()){
                    //if(timeToLive_cacheCountry < System.currentTimeMillis()){
                        log.info("[CACHE] Delete all countries to cache.");
                        deleteValue_cacheCountry();
                    }

                    try {
                        Thread.sleep(lifeTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }


                }
            }
        };
        thread.start();
        return thread;
    }

    public void clearCache(){
        cacheHistory_withoutDay.clear();
        cacheHistory_withDay.clear();
        cacheCountry = new ArrayList<>();
        timeToLive_cacheHistory_withoutDay.clear();
        timeToLive_cacheHistory_withDay.clear();
        timeToLive_cacheCountry = 0L;
    }
}
