package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView textBookName, textAuthorName, textDescription, textLongDescription, textPages;
    private Button btnCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAddFavourites;
    private ImageView imageBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        initViews();

    /*
    getting the id from recycler view and using fuction from utils class for searching
    using id from the recycler view as searching parameter and making sure intent is not null
     */
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookId(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavourites(incomingBook);
                    handleWantToRead(incomingBook);
                }
            }
        }

    }

    private void handleCurrentlyReading(final Book book) {
        ArrayList<Book> currentlyReading = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b : currentlyReading) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BooksActivity.this).addCurrentlyReading(book)) {
                        Toast.makeText(BooksActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BooksActivity.this, CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BooksActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void handleWantToRead(final Book book) {
        ArrayList<Book> handleWantToRead = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToRead = false;
        for (Book b : handleWantToRead) {
            if (b.getId() == book.getId()) {
                existInWantToRead = true;
            }
        }
        if (existInWantToRead) {
            btnWantToRead.setEnabled(false);
        } else {
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BooksActivity.this).addToWantToRead(book)) {
                        Toast.makeText(BooksActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BooksActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BooksActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void handleFavourites(final Book book) {
        ArrayList<Book> handleFavourites = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavourites = false;
        for (Book b : handleFavourites) {
            if (b.getId() == book.getId()) {
                existInFavourites = true;
            }
        }
        if (existInFavourites) {
            btnAddFavourites.setEnabled(false);
        } else {
            btnAddFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BooksActivity.this).addFavourites(book)) {
                        Toast.makeText(BooksActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BooksActivity.this, FavouritesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BooksActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*
    Enabled and disabled already read books button
    adding book to alreadyReadBook arraylist
     */

    @SuppressLint("ResourceAsColor")
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAlreadyRead.setEnabled(false);

        } else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BooksActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BooksActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BooksActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BooksActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*
    setting the data of the book
     */
    private void setData(Book book) {
        textBookName.setText(book.getName());
        textAuthorName.setText(book.getAuthor());
        textPages.setText(String.valueOf(book.getPages()));
        textDescription.setText(book.getShortDescription());
        textLongDescription.setText(book.getLongDescription());
        Glide.with(this)
                .asBitmap().load(book.getImageURL())
                .into(imageBook);
    }

    /*
    linking the ui elements with the books java file
     */
    private void initViews() {
        textBookName = findViewById(R.id.textBookName);
        textAuthorName = findViewById(R.id.textAuthorName);
        textDescription = findViewById(R.id.textDescription);
        textLongDescription = findViewById(R.id.textLongDescription);
        textPages = findViewById(R.id.textPages);

        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddFavourites = findViewById(R.id.btnAddFavourites);

        imageBook = findViewById(R.id.imageBook);
    }
}
