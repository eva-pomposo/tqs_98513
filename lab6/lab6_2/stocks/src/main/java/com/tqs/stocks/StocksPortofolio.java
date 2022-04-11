package com.tqs.stocks;

import java.util.ArrayList;
import java.util.List;

import com.tqs.stocks.Stock;

public class StocksPortofolio {
    private List<Stock> stocks;
    private IStockMarketService stockmarket;

    public StocksPortofolio(IStockMarketService stockmarket) {
        this.stockmarket = stockmarket;
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public double getTotalValue(){
        double value = 0;

        for( Stock stock: stocks ){
            value += stock.getQuantity() * stockmarket.lookUpPrice(stock.getLabel());
        }

        return value; 
    }
    
}
