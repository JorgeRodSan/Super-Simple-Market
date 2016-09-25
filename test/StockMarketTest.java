/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import supersimplestocks.Stock;
import supersimplestocks.Stock.Type;
import supersimplestocks.StockMarket;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author J
 */
public class StockMarketTest {
    StockMarket market = new StockMarket();
    //delta value necessary to make asserequal
    double delta = 0.0;
    public StockMarketTest () {
    }
    
    //Create a list in marcket stock with trades
    public void loadTrades() {
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testDividendYield() {
        Stock stockT = new Stock("TEA", Type.COMMON, 0, 0, 100);
        Stock stockP = new Stock("GIN", Type.PREFERRED, 8, 2, 100);
        
        assertEquals(0.0, market.dividendYield(stockT, 5), delta);
        assertEquals(0.01, market.dividendYield(stockP, 2), delta);
    }
    
    
    @Test
    public void testPeRatio() {
        Stock stockC = new Stock("POP", Type.COMMON, 8, 0, 100);
        Stock stockP = new Stock("GIN", Type.PREFERRED, 8, 2, 100);
        
        assertEquals(62.5, market.peRatio(stockC, 5), delta);
        assertEquals(100.0, market.peRatio(stockP, 2), delta);
    }
    
    @Test
    public void testVolumeWeightedStockPrice() {
        Stock stockC = new Stock("POP", Type.COMMON, 8, 0, 100);
        Stock stockP = new Stock("GIN", Type.PREFERRED, 8, 2, 100);
        
        market.recordBuy(stockC, 1, 1.0);
        market.recordBuy(stockP, 1, 2.0);
        assertEquals(1.5, market.volumeWeightedStockPrice(), delta);
        market.recordsell(stockC, 3, 2.0);
        assertEquals(1.8, market.volumeWeightedStockPrice(), delta);
    }
    
    @Test
    public void testGBCEShareIndex() {
        Stock stockG = new Stock("GIN", Type.PREFERRED, 8, 2, 100);
        Stock stockJ = new Stock("JOE", Type.COMMON, 13, 0, 250);
        Stock stockP = new Stock("POP", Type.COMMON, 8, 0, 100);
        Stock stockT = new Stock("TEA", Type.COMMON, 0, 0, 100);
        Stock stockA = new Stock("ALE", Type.COMMON, 23, 0, 60);
        
        market.recordBuy(stockG, 1, 1.0);
        market.recordBuy(stockG, 1, 2.0);
        market.recordsell(stockJ, 3, 2.0);
        market.recordsell(stockJ, 3, 2.0);
        market.recordBuy(stockA, 1, 1.0);
        market.recordBuy(stockA, 1, 2.0);
        market.recordsell(stockP, 3, 2.0);
        market.recordsell(stockP, 3, 2.0);
        market.recordsell(stockT, 3, 2.0);
        market.recordsell(stockT, 3, 2.0);
        
        market.GBCEShareIndex();
    }
    
}
