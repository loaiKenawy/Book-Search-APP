package com.example.bookscearch;

public class BookInfoPresenter implements BookInfoContract.Presenter{

    BookInfoContract.View view;

    public BookInfoPresenter(BookInfoContract.View view) {
        this.view = view;
    }

    @Override
    public void getBookInfo(String bookTitle, String bookSubTitle, String bookImg, String description) {

    }
}
