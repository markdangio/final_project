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
    
        /**
     * Initialize a User object.
     *
     * @param bookId The book id
     * @param title The book title
     * @param author The author of the book 
     * @param edition The edition of the book 
     * @param publisher The publisher of the book 
     * @param coverPhoto The cover photo
     * @param postedDate The posted date
     * @param price The price of the book 
     * @param sold Sold book
     * @param sellerId The sellers Id
     * @param saleId The saleId
     * @param reserverId The reserver id
     */
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

        /**
     * Get the book id.
     *
     * @return The book id
     */
    public String getBookId() {
        return bookId;
    }
    
        /**
     * Get the title of the book.
     *
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the author of the book.
     *
     * @return The author of the book
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Get the publisher of the book
     *
     * @return The publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Get the cover photo of the book.
     *
     * @return The cover photo of the book
     */
    public String getCoverPhoto() {
        return coverPhoto;
    }

    /**
     * Get the edition of the book
     *
     * @return The edition of the book 
     */
    public int getEdition() {
        return edition;
    }
    
    /**
     * Get the posted date
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
     * @return The sold
     */    
    public int getSold() {
        return sold;
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
     * Get the sale id.
     *
     * @return The sale id
     */
    public String getSaleId() {
        return saleId;
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
