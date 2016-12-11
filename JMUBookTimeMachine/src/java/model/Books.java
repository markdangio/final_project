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
    
    /**
     * Initialize a book object.
     *
     * @param bookId The book id
     * @param title The title
     * @param author The author
     * @param edition The edition 
     * @param publisher The publisher
     * @param coverPhoto The cover photo
     * @param classId  The class id 
     */
    
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

    /**
     * Get the book id.
     *
     * @return The book id
     */
    public String getBookId() {
        return bookId;
    }
    
    /**
     * Get the title
     *
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the author.
     *
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the publisher.
     *
     * @return The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Get the cover photo.
     *
     * @return The cover photo
     */
    public String getCoverPhoto() {
        return coverPhoto;
    }

    /**
     * Get the edition.
     *
     * @return The edition id
     */
    public int getEdition() {
        return edition;
    }
    
    /**
     * Get the class id.
     *
     * @return The class id
     */
    public String getClassId() {
        return classId;
    }
    
}
