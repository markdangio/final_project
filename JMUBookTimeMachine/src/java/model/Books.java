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
    
    private String title, author, publisher, coverPhoto;
    private int edition;
    
    public Books (String title, String author, int edition, String publisher, String coverPhoto)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.coverPhoto = coverPhoto;
        this.edition = edition;
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
    
}
