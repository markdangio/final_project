/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;
/**
 *
 * @author massarmh
 */
public class Books_For_Sale {
    
    private Date postedDate;
    private double price;
    private int sold;
    
    public Books_For_Sale(Date postedDate, double price, int sold)
    {
        this.postedDate = postedDate;
        this.price = price;
        this.sold = sold;
       
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public double getPrice() {
        return price;
    }

    public int getSold() {
        return sold;
    }
}
