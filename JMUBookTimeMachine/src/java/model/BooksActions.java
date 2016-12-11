/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author massarmh
 */
public class BooksActions {

    /**
     * Adding a book
     *
     * @param bookId The book id
     * @param title The title
     * @param author The author
     * @param edition The edition 
     * @param publisher The publisher
     * @param coverPhoto The cover photo
     * @param classId  The class id 
     */
    public static boolean addBook(String bookId, String title , String author, int edition, String coverPhoto, String publisher, String classId) {

        Books newBook = new Books(bookId, title, author, edition, coverPhoto, publisher, classId);
        return BooksPersistence.addBook(newBook);
    }

    /**
     * Check Book
     *
     * @param title The title
     * @param author The author
     * @param edition The edition 
     * @param publisher The publisher
     */
    public static boolean checkBook(String title, String author, int edition, String publisher) {

        return BooksPersistence.checkBook(title, author, edition, publisher);
    }
    
    /**
     * Get book id
     *
     * @param title The title
     * @param author The author
     * @param edition The edition 
     * @param publisher The publisher
     */
    public static String getBookId(String title, String author, int edition, String publisher) {

        return BooksPersistence.getBookId(title, author, edition, publisher);
    }
    
    /**
     * Get book
     *
     * @param bookId The book id
     */
    
    public static Books getBook(String bookId) {

        return BooksPersistence.getBook(bookId);
    }
    
    /**
     * Search Book
     *
     * @param title The title
     * @param author The author
     * @param edition The edition 
     * @param publisher The publisher
     */
    public static ArrayList<Books> searchBook(String title, String author, int edition, String publisher) {

        return BooksPersistence.searchBook(title, author, edition, publisher);
    }
}
