package com.tqs.stocks;

import com.tqs.stocks.StocksPortofolio;
import com.tqs.stocks.IStockMarketService;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.*;


@ExtendWith(MockitoExtension.class)
public class StocksPortofolioTest {
    @Mock 
    private IStockMarketService service;

    @InjectMocks
    private StocksPortofolio stocksPortofolio;

    @Test 
    public void testGetTotalValue(){
        when(service.lookUpPrice("OLX")).thenReturn(2.0);
        when(service.lookUpPrice("Amazon")).thenReturn(2.5);

        stocksPortofolio.addStock(new Stock("OLX", 5));
        stocksPortofolio.addStock(new Stock("Amazon", 6));

        assertThat(stocksPortofolio.getTotalValue(), is(25.0));
        verify(service, times(2)).lookUpPrice(anyString());
    }

}