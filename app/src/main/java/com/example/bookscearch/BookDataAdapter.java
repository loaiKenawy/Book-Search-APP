package com.example.bookscearch;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookDataAdapter extends RecyclerView.Adapter<BookDataAdapter.bookDataViewHolder> {

    private List<Book> bookList;
    private static final String TAG ="Adapter" ;


    private onItemClickListener listenerObj;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener Listener) {
        listenerObj = Listener;
    }

    public BookDataAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }


    public class bookDataViewHolder extends RecyclerView.ViewHolder {

        TextView bookTitle, bookSupTitle ;
        ImageView bookImg;
        public bookDataViewHolder(@NonNull View itemView , onItemClickListener listener) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.title);
            bookSupTitle = itemView.findViewById(R.id.subTitle);
            bookImg = itemView.findViewById(R.id.book_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public BookDataAdapter.bookDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_hint_info, parent, false);
        bookDataViewHolder viewfinder = new bookDataViewHolder(v , listenerObj);
        return viewfinder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookDataAdapter.bookDataViewHolder holder, int position) {
        Book obj = bookList.get(position);
        holder.bookTitle.setText(obj.getBookTitle());
        holder.bookSupTitle.setText(obj.getBookSubTitle());
        Log.d(TAG, "point 01 put the image into the recyclerView image view ");
        Picasso.get().load(obj.getBookImg()).resize(128 ,166).into(holder.bookImg);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
