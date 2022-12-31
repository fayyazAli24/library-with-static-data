package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        RecyclerView recyclerView4 = findViewById(R.id.recyclerView4);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"favourites");
        recyclerView4.setAdapter(adapter);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());

    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
