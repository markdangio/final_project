/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 *
 * @author massarmh
 */
public class BooksActions {

    public static boolean addBook(String bookId, String title , String author, int edition, String coverPhoto, String publisher, String classId) {

        Books newBook = new Books(bookId, title, author, edition, coverPhoto, publisher, classId);
        return BooksPersistence.addBook(newBook);
    }
    /*
    public static boolean checkBooks(String title, String author, int edition) {

        return BooksPersistence.checkBooks(title, author, edition);
    }*/
}
