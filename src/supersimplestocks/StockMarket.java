/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supersimplestocks;

import supersimplestocks.Stock.Type;
import supersimplestocks.Trade.Indicator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author J
 */
public class StockMarket {
    
    //List of trades done
    List<Trade> market = new ArrayList<>();
    //List of stock in Global Beverage Corporation Exchange
    public final List<Stock> stockList = new ArrayList<>();
    
    //Load a list with the information given realte to stocks
    private void loadStocks() {
        stockList.add(new Stock("TEA", Type.COMMON, 0, 0, 100));
        stockList.add(new Stock("POP", Type.COMMON, 8, 0, 100));
        stockList.add(new Stock("ALE", Type.COMMON, 23, 0, 60));
        stockList.add(new Stock("GIN", Type.PREFERRED, 8, 2, 100));
        stockList.add(new Stock("JOE", Type.COMMON, 13, 0, 250));
    }
    
    // Return the dividen according to the type of stock
    private double dividend (Stock stock){
                
        if (stock.type == Type.COMMON){
            return stock.lastDividend;
        }
        
        if (stock.type == Type.PREFERRED){
           return ((stock.fixedDividend / 100.0) * stock.parValue);
        }
        
        return 0.0;
    }
    
    //Return the dividend yield for a stock and price given
    public double dividendYield (Stock stock, double price){
     
        return dividend(stock) / price;        
    }
    
    //Return P/E Ratio for a stock and price given
    public double peRatio (Stock stock, double price){
        
        return price / dividend(stock);
    }
    
    //Record a purchase on the list 
    public void recordBuy (Stock stock, int shares, double price){
        Trade trade  = new Trade (stock, shares, Indicator.BUY, price);
        this.market.add(trade);
    }
    
    //Record a sale on the list
    public void recordsell (Stock stock, int shares, double price){
        Trade trade  = new Trade (stock, shares, Indicator.SELL, price);
        this.market.add(trade);
    }
    
    //return the volume weighted stock price based on trades in past 15 minutes
    public double volumeWeightedStockPrice (){
        Date date = new Date();
        double tradexquantity =0.0;
        double shareses = 0.0;
        long timeInMillis = date.getTime() - (15*60*1000);
        
        //we get a new list with trades in past 15 minutes
        List<Trade> tradeByTime = market.stream().filter(trade -> (trade.timestamp.getTime() >= timeInMillis)).collect(Collectors.toList());
        
        for (Trade trade : tradeByTime){
            tradexquantity += (trade.price * trade.shares);
            shareses += trade.shares;            
        }
        
        return tradexquantity / shareses;
    }
    
    //return the geometric mean for a list of trades given
    private double geometricMean (List <Trade> tradesOfStock){
        double result = 1.0;
        int i = 0;
        for (Trade trade : tradesOfStock){
            result *= trade.price;
            i++;
        }
        
        result = Math.pow(result, 1.0/(double)i);
        return result;
    }
    
    //Write in console the result of every Share Index
    public void GBCEShareIndex (){
        double result;
        //Load the list with all stocks first
        loadStocks();
        
        System.out.println("GBCE All Share Index");
        //For each stock create a list of trades and get its shared index
        for (Stock stock : stockList){
            result = geometricMean(market.stream().filter(trade -> trade.stock.stockSymbol.equals(stock.stockSymbol)).collect(Collectors.toList()));
            System.out.println(stock.stockSymbol + ": " + result);
        }
    }
}
