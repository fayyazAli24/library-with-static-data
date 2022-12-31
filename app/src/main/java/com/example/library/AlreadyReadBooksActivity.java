package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBooksActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_books);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"alreadyRead");
        recyclerView2.setAdapter(adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());

    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}