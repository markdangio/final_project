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
public class Books {
    
    private String bookId, title, author, publisher, coverPhoto, classId;
    private int edition;
    
    public Books (String bookId, String title, String author, int edition, String publisher, String coverPhoto, String classId)
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.coverPhoto = coverPhoto;
        this.edition = edition;
        this.classId = classId;
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
    
    public String getClassId() {
        return classId;
    }
    
}
