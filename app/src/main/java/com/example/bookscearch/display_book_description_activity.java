package com.example.bookscearch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class display_book_description_activity extends AppCompatActivity {

    private static final String TAG ="display_book_description_activity" ;

    private favouriteBooksStorage favouriteBooksStorage = new favouriteBooksStorage(this);

    private TextView bookTitle, bookSupTitle ,bookDescription ;
    private ImageView bookImg;
    private ImageButton favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_book_description_activity);

        favButton =findViewById(R.id.favButton);

        show();

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookToFavourite();
            }
        });
    }
    private void show(){

        bookTitle = findViewById(R.id.title2);
        bookSupTitle = findViewById(R.id.subTitle2);
        bookDescription = findViewById(R.id.description_view);
        bookImg = findViewById(R.id.book_img2);
        bookTitle.setText(getIntent().getExtras().getString("title"));
        bookSupTitle.setText(getIntent().getExtras().getString("Author"));
        bookDescription.setText(getIntent().getExtras().getString("description"));
        Log.d(TAG, "point 02 put the image into description image View ");
        Picasso.get().load(getIntent().getExtras().getString("bookImg")).into(bookImg);


    }
    private void addBookToFavourite(){

        String title , author,image , description;
        title = getIntent().getExtras().getString("title");
        author = getIntent().getExtras().getString("Author");
        image = getIntent().getExtras().getString("bookImg");
        description = getIntent().getExtras().getString("description");

       boolean results = favouriteBooksStorage.addToFavourite(title,author,image,description);

       if (results == true){
           Toast.makeText(display_book_description_activity.this,"book added to favourite", Toast.LENGTH_SHORT).show();
       }
    }
}