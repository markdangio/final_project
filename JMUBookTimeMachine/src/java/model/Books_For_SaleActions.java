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
public class Books_For_SaleActions {

    public static boolean addBooks_For_Sale(Date postedDate, double price, int sold) {

        Books_For_Sale newBFS = new Books_For_Sale(postedDate, price, sold);
        return Books_For_SalePersistence.addBook_For_Sale(newBFS);
    }
    /*
    public static boolean checkBooks_For_Sale(Date postedDate, int sold) {

        return Books_For_SalePersistence.checkBooks_For_Sale(postedDate, sold);
    }*/
}
