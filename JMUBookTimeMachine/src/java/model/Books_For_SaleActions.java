/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author massarmh
 */
public class Books_For_SaleActions {

    /**
     * Adding a book for sale
     * 
     * @param bookId The book id
     * @param sellerId The seller id
     * @param saleId The sale id
     * @param postedDate The posted date 
     * @param price The price
     * @param sold sold book
     * @param reserverId  The reserver id 
     */
    public static boolean addBooks_For_Sale(String sellerId, String bookId, String saleId, String postedDate, double price, int sold, String reserverId) {

        Books_For_Sale newBFS = new Books_For_Sale(sellerId, bookId, saleId, postedDate, price, sold, reserverId);
        return Books_For_SalePersistence.addBook_For_Sale(newBFS);
    }
    
    /**
     * Search Book for sale
     *
     * @param books The title
     * @param userId The author
     * 
     * @return books and user
     */
    public static ArrayList<BookInfo> searchBook_For_Sale(ArrayList<Books> books, String userId) {

        return Books_For_SalePersistence.searchBook_For_Sale(books, userId);
    }
    
    /**
     * Search Book for sale reserved
     *
     * @param reserverId The reserver id
     * 
     * @return reserver Id 
     */
    public static ArrayList<BookInfo> searchBook_For_SaleReserved(String reserverId) {

        return Books_For_SalePersistence.searchBook_For_SaleReserved(reserverId);
    }
    
    /**
     * Search Book for sale selling
     *
     * @param sellerId
     * 
     * @return seller id of the book
     */
    public static ArrayList<BookInfo> searchBook_For_SaleSelling(String sellerId) {

        return Books_For_SalePersistence.searchBook_For_SaleSelling(sellerId);
    }
    
    /**
     * reserved book
     *
     * @param saleId The sale Id
     * @param reserverId The reserver Id
     * 
     * @return sale Id and reserver Id
     */
    public static boolean reserveBook_For_Sale(String saleId, String reserverId) {

        return Books_For_SalePersistence.reserveBook_For_Sale(saleId, reserverId);
    }
            
    /**
     * Search Book
     *
     * @param saleId sale id
     * 
     * @return sale id
     */
    public static boolean sellBooks_For_Sale(String saleId) {

        return Books_For_SalePersistence.sellBooks_For_Sale(saleId);
    }
}
