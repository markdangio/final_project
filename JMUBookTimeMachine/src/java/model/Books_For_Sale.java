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
    
    private String sellerId, bookId, saleId, postedDate, reserverId;
    private double price;
    private int sold;
    
    public Books_For_Sale(String sellerId, String bookId, String saleId, String postedDate, double price, int sold, String reserverId)
    {
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.saleId = saleId;
        this.postedDate = postedDate;
        this.price = price;
        this.sold = sold;
       this.reserverId = reserverId;
    }
    
    public String getSellerId() {
        return sellerId;
    }
    public String getBookId() {
        return bookId;
    }
    
    public String getSaleId() {
        return saleId;
    }
    

    public String getPostedDate() {
        return postedDate;
    }

    public double getPrice() {
        return price;
    }

    public int getSold() {
        return sold;
    }
    
    public String getReserverId() {
        return reserverId;
    }
}
