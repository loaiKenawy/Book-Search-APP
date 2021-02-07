package com.example.bookscearch;

public interface BookInfoContract {
    interface View{
        void onSuccess(String message);
        void onERROR(String message);
    }
    interface Presenter{
        void getBookInfo(String bookTitle, String bookSubTitle ,String bookImg ,String description );
    }
}
