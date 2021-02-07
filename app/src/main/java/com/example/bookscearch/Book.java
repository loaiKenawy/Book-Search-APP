package com.example.bookscearch;

public class Book {

    private String bookTitle , bookSubTitle , bookImg , description;

    public Book(String bookTitle, String bookSubTitle ,String bookImg ,String description ) {

        this.bookTitle = bookTitle;
        this.bookSubTitle = bookSubTitle;
        this.bookImg = bookImg;
        this.description = description;

    }

    public String getBookImg() {
        return bookImg;
    }

    public String getBookSubTitle() {
        return bookSubTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getDescription() {
        return description;
    }
}
