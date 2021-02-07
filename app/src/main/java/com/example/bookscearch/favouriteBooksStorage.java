package com.example.bookscearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class favouriteBooksStorage extends SQLiteOpenHelper {

    private static final String dataBaseName = "favouriteBooksStorage.db";
    private static final String tableName ="Book";
    private static final String COLUMN_bookTitle ="BookTitle";
    private static final String COLUMN_authorName = "AuthorName";
    private static final String COLUMN_bookImg = "Bookimage";
    private static final String COLUMN_bookDescription = "BookDescription";

    public favouriteBooksStorage(@Nullable Context context) {
        super(context, dataBaseName, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE "+tableName+"( "+COLUMN_bookTitle+" TEXT ,"+COLUMN_authorName+" TEXT , "+COLUMN_bookImg+" TEXT , "+COLUMN_bookDescription+" TEXT); ";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ tableName +";");
        onCreate(db);
    }

    public boolean addToFavourite(String bookTitle ,String authorName ,String bookImage , String bookDescription ){

        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_bookTitle , bookTitle);
        contentValues.put(COLUMN_authorName , authorName);
        contentValues.put(COLUMN_bookImg , bookImage);
        contentValues.put(COLUMN_bookDescription , bookDescription);
        int result;
        result = (int) db.insert(tableName ,null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showFavouriteList() {

        SQLiteDatabase db =this.getReadableDatabase();

        Cursor result = null;
        if(db != null) {
            result = db.rawQuery("SELECT * FROM " + tableName + "", null);
        }
        return result;
    }
    public void removeAllFavourite(){
        String removeQuery = "DROP TABLE "+tableName+";";
        SQLiteDatabase db = this.getWritableDatabase() ;
        db.execSQL(removeQuery);
    }
}
