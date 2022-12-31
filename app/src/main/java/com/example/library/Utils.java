package com.example.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    /*
    keys for using in shared prefernces
     */
    private static final String ALL_BOOKS_KEYS = "allBooksKeys";
    private static final String ALREADY_READ_BOOKS = "already_read_books_keys";
    private static final String WANT_TO_READ_BOOKS="want_to_read_books_keys";
    private static final String FAVOURITES ="favourites_keys";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books_keys";

    //    using the utils class and creating an instance to handle the singleton pattern
    private static Utils instance;
    private  SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    //    handling our arraylist in the constructor
    public Utils(Context context) {

//        instance of shared preferences is made like this
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
        if (null == getAllBooks()) {
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString(FAVOURITES,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }



    // creating our instance through this method no matter how many times it is called it will point to the first instance
    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        }
        instance = new Utils(context);
        return instance;
    }

    //    initializing data in this method
    private void initData() {
        //TODO add initial data

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("the hobbit", "J.R.R TOLKEIT", "fiction", "https://images1.penguinrandomhouse.com/cover/9780345445605", "A fiction story about men , elves,dwarves,orcs"
                , 1550, 1));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", "fiction", "https://static.wikia.nocookie.net/harrypotter/images/7/7a/Harry_Potter_and_the_Philosopher%27s_Stone_%E2%80%93_Bloomsbury_2014_Children%27s_Edition_%28Paperback_and_Hardcover%29.jpg/revision/latest/top-crop/width/360/height/360?cb=20170109041611", "a story about wizards",
                500, 2));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEYS,gson.toJson(books));
        editor.commit();
    }

    //    using Type interface to change the incoming text from shared prefernces into book object again
    public  ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken <ArrayList<Book>>(){}.getType();
        ArrayList<Book> books= gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEYS,null),type);
        return books;
    }
    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken <ArrayList<Book>>(){}.getType();
        ArrayList<Book> books= gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }
    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken <ArrayList<Book>>(){}.getType();
        ArrayList<Book> books= gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }
    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken <ArrayList<Book>>(){}.getType();
        ArrayList<Book> books= gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }
    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken <ArrayList<Book>>(){}.getType();
        ArrayList<Book> books= gson.fromJson(sharedPreferences.getString(FAVOURITES,null),type);
        return books;
    }

    /*
    searching book via id
     */
    public Book getBookId(int id) {
        ArrayList <Book> books = getAllBooks();
        if (null !=books){
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }



    /*
    function used for enabled and disabled button for the buttons in AllbookyActivity
     */
    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }
    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }
    public boolean addCurrentlyReading(Book book ){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }
    public boolean addFavourites(Book book){
        ArrayList<Book> books =getFavoriteBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITES);
                editor.putString(FAVOURITES,gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }

    // functions for handling delete button
    public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null!=books){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(null!=books){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null!=books){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromFavourites(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(null!=books){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITES);
                        editor.putString(FAVOURITES,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
