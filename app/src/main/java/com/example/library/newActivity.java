package com.example.library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class newActivity extends AppCompatActivity {
    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        booksRecView=findViewById(R.id.booksRecView);

        adapter=new BookRecViewAdapter(this,"allBooks");
        booksRecView=findViewById(R.id.booksRecView);
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getAllBooks());


    }
}