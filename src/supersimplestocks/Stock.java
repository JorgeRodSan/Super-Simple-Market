/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supersimplestocks;

/**
 *
 * @author J
 */
public class Stock {
    
    public enum Type {COMMON, PREFERRED}
    public final String stockSymbol;
    public final Type type;
    public final double lastDividend;
    public final double fixedDividend;
    public final double parValue;

   /**
   * Constructor
     * @param stockSymbol
     * @param type
     * @param lastDividend
     * @param fixedDividend
     * @param parValue
   */  
    public Stock(String stockSymbol, Type type, int lastDividend, int fixedDividend, int parValue)
    {
        this.stockSymbol = stockSymbol;
        this.type = type;
        //Value in pennies
        this.lastDividend = (double)lastDividend / 100;
        this.fixedDividend = (double)fixedDividend;
        //Value in pennies
        this.parValue = (double)parValue / 100;
  }
    
}
