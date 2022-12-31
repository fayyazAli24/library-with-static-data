package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_book);
        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "currentlyReading");
        recyclerView3.setAdapter(adapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}