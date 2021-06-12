package com.library.Model;

import java.time.Year;

// This class could be created using a builder design pattern
/// but I am trying to provide less overhead in the heap
public class Book {
    String isbn;
    String title;
    String authorName;
    String publisherName;
    String publicationYear;
    Integer price;
    String category;
    Integer numberCopies;
    Integer threshold;
    public Book(){

    }
    public Book(String isbn, String title, String authorName, String publisherName, String publicationYear, Integer price, String category, Integer numberCopies, Integer threshold) {
        this.isbn = isbn;
        this.title = title;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publicationYear = publicationYear;
        this.price = price;
        this.category = category;
        this.numberCopies = numberCopies;
        this.threshold = threshold;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!title.isEmpty())
            this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumberCopies() {
        return numberCopies;
    }

    public void setNumberCopies(Integer number_copies) {
        this.numberCopies = number_copies;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}
