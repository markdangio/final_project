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
   
        
    /**
     * Initialize a book_for_sale object.
     *
     * @param bookId The book id
     * @param sellerId The seller id
     * @param saleId The sale id
     * @param postedDate The posted date 
     * @param price The price
     * @param sold sold book
     * @param reserverId  The reserver id 
     */
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
    
    /**
     * Get the seller id.
     *
     * @return The seller id
     */
    public String getSellerId() {
        return sellerId;
    }
    /**
     * Get the book id.
     *
     * @return The book id
     */
    public String getBookId() {
        return bookId;
    }
    
    /**
     * Get the sale id.
     *
     * @return The sale id
     */
    public String getSaleId() {
        return saleId;
    }
    
    /**
     * Get the posted date.
     *
     * @return The posted date
     */
    public String getPostedDate() {
        return postedDate;
    }

    /**
     * Get the price
     *
     * @return The price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the sold.
     *
     * @return The sold book
     */
    public int getSold() {
        return sold;
    }
    
    /**
     * Get the reserver id.
     *
     * @return The reserver id
     */
    public String getReserverId() {
        return reserverId;
    }
}
