package com.tqs.covid19Service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.tqs.covid19Service.model.Cache;
import com.tqs.covid19Service.model.Country;
import com.tqs.covid19Service.model.KeyCacheHistoryWithDay;
import com.tqs.covid19Service.model.Statistic;
import com.tqs.covid19Service.resolver.Resolver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Covid19ServiceTest {
    Country country1;
    Country country2;
    Country country3;
    Statistic statistic1;
    Statistic statistic2;
    List<Country> allCountries;
    List<Statistic> allStatistic;
    List<Statistic> emptyStatisticList;
    KeyCacheHistoryWithDay key;

    @Mock( lenient = true)
    private Cache cache;

    @Mock( lenient = true)
    private Resolver resolver;

    @InjectMocks
    private Covid19Service service;

    @BeforeEach
    public void setUp() {

        country1 = new Country("Portugal");
        country2 = new Country("Albania");
        country3 = new Country("Angola");
        allCountries = Arrays.asList(country1, country2, country3);
        
        statistic1 = new Statistic("Europe", "Portugal", 10198931, "+195", 11590, 58, 19869, "3225", 32895, "+12", "141", 1436, "79657", 812415, "2020-06-02", "2020-06-02T12:45:07+00:00");
        statistic2 = new Statistic("Europe", "Portugal", 10199012, "+195", 11591, 57, 19868, "3224", 32894, "+11", "140", 1435, "79656", 812414, "2020-06-02", "2020-06-02T12:00:06+00:00");
        allStatistic = Arrays.asList(statistic1, statistic2);

        key = new KeyCacheHistoryWithDay("Portugal", "2020-06-02");

        emptyStatisticList = new ArrayList<>();
    }
    
    @Test
    void given3Countries_whengetCountries_thenReturn3RecordsThroughCache() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getCountries_FromCache()).thenReturn(allCountries);

        List<Country> countries = service.getCountries();
        verifyGetCountries_FromCacheIsCalledOnce();
        assertThat(countries).hasSize(3).extracting(Country::getName).contains(country1.getName(), country2.getName(), country3.getName());

    }

    @Test
    void given3Countries_whengetCountries_thenReturn3RecordsThroughExternalAPI() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getCountries_FromCache()).thenReturn(null);
        Mockito.when(resolver.callEXternalAPI("/countries") ).thenReturn("Portugal, Albania, Angola");
        Mockito.when(resolver.convertStringtoListCountries( "Portugal, Albania, Angola" ) ).thenReturn(allCountries);

        List<Country> countries = service.getCountries();
        verifyGetCountries_FromCacheIsCalledOnce();
        verifyCallEXternalAPI_ResolverIsCalledOnce(); 
        verifyConvertStringtoListCountries_ResolverIsCalledOnce(); 
        verifyAddValue_cacheCountry_IsCalledOnce();
       
        assertThat(countries).hasSize(3).extracting(Country::getName).contains(country1.getName(), country2.getName(), country3.getName());
    }

    @Test
    void getCacheStatisticsTest() {
        Mockito.when(cache.getHits()).thenReturn(1);
        Mockito.when(cache.getMisses()).thenReturn(1);
        Mockito.when(cache.getRequests()).thenReturn(2);

        Map<String, Integer> cacheStatistics = service.getCacheStatistics();
        verifyGetHitsMissesRequests_IsCalledOnce();

        assertThat(cacheStatistics).hasSize(3).containsEntry("hits", 1).containsEntry("misses", 1).containsEntry("requests", 2);
    }

    @Test
    void whenValidCountry_thenHistoryShouldBeFoundThroughCache() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getStatisticsWithoutDay_FromCache("Portugal")).thenReturn(allStatistic);

        List<Statistic> history = service.getHistory("Portugal");
        verifyGetStatisticsWithoutDay_FromCacheIsCalledOnce();

        assertThat(history).hasSize(2).extracting(Statistic::getCountry).contains(statistic1.getCountry(), statistic2.getCountry());
    }

    @Test
    void whenValidCountry_thenHistoryShouldBeFoundThroughExternalAPI() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getStatisticsWithoutDay_FromCache("Portugal")).thenReturn(null);
        Mockito.when(resolver.callEXternalAPI("/history?country=Portugal") ).thenReturn("statistic");
        Mockito.when(resolver.convertStringtoHistory( "statistic" ) ).thenReturn(allStatistic);

        List<Statistic> history = service.getHistory("Portugal");
        verifyGetStatisticsWithoutDay_FromCacheIsCalledOnce();
        verifyCallEXternalAPI_ResolverIsCalledOnce();
        verifyConvertStringtoHistory_ResolverIsCalledOnce();
        verifyAddValue_cacheHistory_withoutDay_IsCalledOnce();

        assertThat(history).hasSize(2).extracting(Statistic::getCountry).contains(statistic1.getCountry(), statistic2.getCountry());
    }

    @Test
    void whenInValidCountry_thenHistoryShouldNotBeFound() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getStatisticsWithoutDay_FromCache("Portugal")).thenReturn(null);
        Mockito.when(resolver.callEXternalAPI("/history?country=Portugal") ).thenReturn("statistic");
        Mockito.when(resolver.convertStringtoHistory( "statistic" ) ).thenReturn(emptyStatisticList);

        List<Statistic> history = service.getHistory("Portugal");

        assertThat(history).isEmpty();

        verifyGetStatisticsWithoutDay_FromCacheIsCalledOnce();
        verifyCallEXternalAPI_ResolverIsCalledOnce();
        verifyConvertStringtoHistory_ResolverIsCalledOnce();
        verifyAddValue_cacheHistory_withoutDay_IsCalledOnce();
    }

    @Test
    void whenValidCountryAndDay_thenHistoryShouldBeFoundThroughCache() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getStatisticsWithDay_FromCache(key)).thenReturn(allStatistic);

        List<Statistic> history = service.getHistory("Portugal", "2020-06-02");
        verifyGetStatisticsWithDay_FromCacheIsCalledOnce();

        assertThat(history).hasSize(2).extracting(Statistic::getCountry).contains(statistic1.getCountry(), statistic2.getCountry());
    }

    @Test
    void whenValidCountryAndDay_thenHistoryShouldBeFoundThroughExternalAPI() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getStatisticsWithDay_FromCache(key)).thenReturn(null);
        Mockito.when(resolver.callEXternalAPI("/history?country=Portugal&day=2020-06-02") ).thenReturn("statistic");
        Mockito.when(resolver.convertStringtoHistory( "statistic" ) ).thenReturn(allStatistic);

        List<Statistic> history = service.getHistory("Portugal", "2020-06-02");
        verifyGetStatisticsWithDay_FromCacheIsCalledOnce();
        verifyCallEXternalAPI_ResolverIsCalledOnce();
        verifyConvertStringtoHistory_ResolverIsCalledOnce();
        verifyAddValue_cacheHistory_withDay_IsCalledOnce();

        assertThat(history).hasSize(2).extracting(Statistic::getCountry).contains(statistic1.getCountry(), statistic2.getCountry());
    }

    @Test
    void whenInValidCountryAndDay_thenHistoryShouldNotBeFound() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(cache.getStatisticsWithDay_FromCache(key)).thenReturn(null);
        Mockito.when(resolver.callEXternalAPI("/history?country=Portugal&day=2020-06-02") ).thenReturn("statistic");
        Mockito.when(resolver.convertStringtoHistory( "statistic" ) ).thenReturn(emptyStatisticList);

        List<Statistic> history = service.getHistory("Portugal", "2020-06-02");

        assertThat(history).isEmpty();

        verifyGetStatisticsWithDay_FromCacheIsCalledOnce();
        verifyCallEXternalAPI_ResolverIsCalledOnce();
        verifyConvertStringtoHistory_ResolverIsCalledOnce();
        verifyAddValue_cacheHistory_withDay_IsCalledOnce();
    }

    private void verifyGetStatisticsWithoutDay_FromCacheIsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).getStatisticsWithoutDay_FromCache(Mockito.anyString());
    }

    private void verifyGetStatisticsWithDay_FromCacheIsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).getStatisticsWithDay_FromCache( Mockito.any() );
    }

    private void verifyGetCountries_FromCacheIsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).getCountries_FromCache();
    }

    private void verifyConvertStringtoListCountries_ResolverIsCalledOnce() throws URISyntaxException, IOException, InterruptedException {
        Mockito.verify(resolver, VerificationModeFactory.times(1)).convertStringtoListCountries(resolver.callEXternalAPI(Mockito.anyString()));
    }

    private void verifyConvertStringtoHistory_ResolverIsCalledOnce() throws URISyntaxException, IOException, InterruptedException {
        Mockito.verify(resolver, VerificationModeFactory.times(1)).convertStringtoHistory(resolver.callEXternalAPI(Mockito.anyString()));
    }

    private void verifyCallEXternalAPI_ResolverIsCalledOnce() throws URISyntaxException, IOException, InterruptedException {
        Mockito.verify(resolver, VerificationModeFactory.times(1)).callEXternalAPI(Mockito.anyString());
    }

    private void verifyAddValue_cacheCountry_IsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).addValue_cacheCountry(Mockito.anyList());
    }

    private void verifyAddValue_cacheHistory_withoutDay_IsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).addValue_cacheHistory_withoutDay(Mockito.anyString(), Mockito.anyList());
    }

    private void verifyAddValue_cacheHistory_withDay_IsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).addValue_cacheHistory_withDay(Mockito.any(), Mockito.anyList());
    }

    private void verifyGetHitsMissesRequests_IsCalledOnce() {
        Mockito.verify(cache, VerificationModeFactory.times(1)).getHits();
        Mockito.verify(cache, VerificationModeFactory.times(1)).getMisses();
        Mockito.verify(cache, VerificationModeFactory.times(1)).getRequests();
    }




}
