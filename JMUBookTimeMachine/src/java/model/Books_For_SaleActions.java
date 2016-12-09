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

    public static boolean addBooks_For_Sale(String sellerId, String bookId, String saleId, String postedDate, double price, int sold, String reserverId) {

        Books_For_Sale newBFS = new Books_For_Sale(sellerId, bookId, saleId, postedDate, price, sold, reserverId);
        return Books_For_SalePersistence.addBook_For_Sale(newBFS);
    }
    /*
    public static boolean checkBooks_For_Sale(Date postedDate, int sold) {

        return Books_For_SalePersistence.checkBooks_For_Sale(postedDate, sold);
    }*/
    
    public static ArrayList<BookInfo> searchBook_For_Sale(ArrayList<Books> books, String userId) {

        return Books_For_SalePersistence.searchBook_For_Sale(books, userId);
    }
    
    public static ArrayList<BookInfo> searchBook_For_SaleReserved(String reserverId) {

        return Books_For_SalePersistence.searchBook_For_SaleReserved(reserverId);
    }
    
    public static ArrayList<BookInfo> searchBook_For_SaleSelling(String sellerId) {

        return Books_For_SalePersistence.searchBook_For_SaleSelling(sellerId);
    }
    
    public static boolean reserveBook_For_Sale(String saleId, String reserverId) {

        return Books_For_SalePersistence.reserveBook_For_Sale(saleId, reserverId);
    }
}
