package com.example.bookscearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class display_books_activity extends AppCompatActivity {

    private final String Sport = "https://run.mocky.io/v3/f393cb45-4081-4e33-a45e-c2966a90f2f8";
    private final String Life = "https://run.mocky.io/v3/b5adf54a-9e8a-47eb-a055-7ba3249aae9c";
    private final String Love = "https://run.mocky.io/v3/a1f58cce-5dc9-44e7-a5a8-1daa0824250e";
    private final String Time = "https://run.mocky.io/v3/e36cc68e-5e78-4f11-950b-fc6570bc5d03";

    private favouriteBooksStorage favouriteBooksStorage = new favouriteBooksStorage(this);

    private RecyclerView bookHintCardRecyclerView ;
    private BookDataAdapter adapter;
    private ArrayList<Book> item;
    private RequestQueue mQueue;

    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_books_activity);

        item = new ArrayList<>();
        mQueue = Volley.newRequestQueue(getApplicationContext());
        bookHintCardRecyclerView = (RecyclerView) findViewById(R.id.bookHintCardRecyclerView);
        adapter = new BookDataAdapter(item);
        bookHintCardRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bookHintCardRecyclerView.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        String category = b.getString("category");
        selectCategory(category);

        adapter.setOnItemClickListener(new BookDataAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showBookInfo(
                         item.get(position).getBookTitle()
                        ,item.get(position).getBookSubTitle()
                        ,item.get(position).getBookImg()
                        ,item.get(position).getDescription());
            }
        });
    }

    public void selectCategory(String category){
        switch (category){
            case "Sport":{
                displaySpecificCategory(Sport);
                break;
            }
            case "life":{
                displaySpecificCategory(Life);
                break;
            }
            case "love":{
                displaySpecificCategory(Love);
                break;
            }
            case "time":{
                displaySpecificCategory(Time);
                break;
            }
            case "favourite":{
                displayFavBooks();
                break;
            }
        }
    }

    public void showBookInfo(String title , String Author , String bookImg , String description){
        intent2 = new Intent(display_books_activity.this , display_book_description_activity.class);
        intent2.putExtra("title",title);
        intent2.putExtra("Author",Author);
        intent2.putExtra("bookImg",bookImg);
        intent2.putExtra("description",description);
        startActivity(intent2);
    }

    public void displaySpecificCategory(String JSONuUrl){
        JsonObjectRequest Request = new JsonObjectRequest(com.android.volley.Request.Method.GET, JSONuUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            for(int i = 0 ; i <= items.length() ;i++){
                                JSONObject itemObj = items.getJSONObject(i);
                                JSONObject volumeInfo = itemObj.getJSONObject("volumeInfo");
                                //JSONArray author = volumeInfo.getJSONArray("authors");
                                String Author = volumeInfo.getString("authors");
                                String title = volumeInfo.getString("title");
                                String description = volumeInfo.getString("description");
                                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                                String imageLink = imageLinks.getString("thumbnail");
                                    item.add(new Book(title, "By:"+Author 
                                            ,"https://books.google.com.eg/books/content?id=uLuXy725C_AC&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE72dEhO9ro1UnsB_8UyV5Ca-oq1zML-A2Yez8Qg549lcJbHI9KQ3VaDDshsBREylyzwToqkv3o8ZG6fdcrpOfS_9goy8KTaZuwNPY_lvNrj-dkayBNAhZF3HM36-matUreN7dWMQ" 
                                            ,description));
                                    adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(Request);
    }
    
    public void displayFavBooks(){
        Cursor cursor = favouriteBooksStorage.showFavouriteList();
        if(cursor.getCount() == 0){

            Toast.makeText(display_books_activity.this, "NO BOOKS TO SHOW", Toast.LENGTH_SHORT).show();

        }else{


            while(cursor.moveToNext()){
                String bookTitle  = cursor.getString(0);
                String authorName =cursor.getString(1);
                String bookImage = cursor.getString(2);
                String des = cursor.getString(3);
                item.add(new Book(bookTitle ,authorName, bookImage , des));
            }
        }


    }


}