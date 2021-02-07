package com.example.bookscearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class home_page extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private favouriteBooksStorage bookDB;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bookDB = new favouriteBooksStorage(this);
        navMenu();
        flipImages();
    }

    public void flipImages(){
        int images[] = {R.drawable.first_image , R.drawable.second_image};
        for(int i = 0;i < images.length ; i++) {
            viewFlipper = findViewById(R.id.viewFlipper);
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        }

    }

    public void navMenu(){

        drawerLayout = (DrawerLayout) findViewById(R.id.ddd_111);
        actionBar = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        actionBar.setDrawerIndicatorEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout.addDrawerListener(actionBar);
        actionBar.syncState();
        final NavigationView navView = (NavigationView)findViewById(R.id.nav_view) ;
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.home_nav_btn){

                    intent = new Intent(home_page.this, home_page.class);

                }else if(id == R.id.favorite_nav_btn){

                    intent = new Intent(home_page.this, display_books_activity.class);
                    intent.putExtra("category","favourite");
                    startActivity(intent);

                }else if (id == R.id.sport_nav_btn){

                    intent = new Intent(home_page.this, display_books_activity.class);
                    intent.putExtra("category","Sport");
                    startActivity(intent);

                }
                else if(id == R.id.life_nav_btn){

                    intent = new Intent(home_page.this, display_books_activity.class);
                    intent.putExtra("category","life");
                    startActivity(intent);

                }
                else if(id == R.id.love_nav_btn){

                    intent = new Intent(home_page.this, display_books_activity.class);
                    intent.putExtra("category","love");
                    startActivity(intent);

                }
                else if(id == R.id.time_nav_btn){

                    intent = new Intent(home_page.this, display_books_activity.class);
                    intent.putExtra("category","time");
                    startActivity(intent);

                }
                else if(id == R.id.remove_nav_btn){
                    bookDB.removeAllFavourite();
                    Toast.makeText(home_page.this , "Favoutite List removed",Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBar.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
