/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author massarmh
 */
public class BookInfo {
    
    private String bookId, title, author, publisher, coverPhoto, classId, postedDate, sellerId, saleId, reserverId;
    private int edition, sold;
    private double price;
    
    public BookInfo (String bookId, String title, String author, int edition, String publisher, String coverPhoto, String postedDate, double price, int sold, String sellerId, String saleId, String reserverId)
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.coverPhoto = coverPhoto;
        this.edition = edition;
        this.postedDate = postedDate;
        this.price = price;
        this.sold = sold;
        this.sellerId = sellerId;
        this.saleId = saleId;
        this.reserverId = reserverId;
    }

    public String getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public int getEdition() {
        return edition;
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
    
    public String getSellerId() {
        return sellerId;
    }
    
    public String getSaleId() {
        return saleId;
    }
    
    public String getReserverId() {
        return reserverId;
    }
    
}
