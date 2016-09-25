/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supersimplestocks;

import java.util.Date;

/**
 *
 * @author J
 */
public class Trade {
    
    public final Date timestamp;
    public final Stock stock;
    public final double shares; 
    public enum Indicator {BUY, SELL}
    public final Indicator indicator;
    public final double price;
      

  /**
   * Constructor
     * @param stock
     * @param shares
     * @param indicator
     * @param price
   */
    public Trade(Stock stock, int shares, Indicator indicator, double price)
    {
        //The date is register at the momento when the object is created and, therefore, the trade is done
        this.timestamp = new Date();
        this.stock = stock;
        this.shares = (double)shares;
        this.indicator = indicator;
        this.price = price;
  }
    
}
 