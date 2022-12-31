package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAllBooks, btnCurrentlyReadingBooks, btnAlreadyReadBooks, btnYourWishlist, btnFavourites, btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newActivity.class);
                startActivity(intent);
            }
        });

        btnAlreadyReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });
        btnFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });
        btnCurrentlyReadingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });
        btnYourWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WantToReadActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and developed by fayyaz ali with the help of free code camp" +
                        " much love for my teacher meisam be sure to visit his website");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra("url", "https://google.com/");
                        startActivity(intent);

                    }
                });
                builder.create().show();
            }
        });
        Utils.getInstance(this);
    }

    private void initView() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReadingBooks = findViewById(R.id.btnCurrentlyReadingBooks);
        btnAlreadyReadBooks = findViewById(R.id.btnAlreadyReadBooks);
        btnYourWishlist = findViewById(R.id.btnYourWishlist);
        btnFavourites = findViewById(R.id.btnFavourites);
        btnAbout = findViewById(R.id.btnAbout);
    }
}