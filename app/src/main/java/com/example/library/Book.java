package com.example.library;

public class Book {
    private String name,author,shortDescription,imageURL,longDescription;
    private int pages,id;
    boolean isExpanded;

    public Book(String name, String author, String shortDescription, String imageURL, String longDescription, int pages,int id) {
        this.name = name;
        this.author = author;
        this.shortDescription = shortDescription;
        this.imageURL = imageURL;
        this.longDescription = longDescription;
        this.pages = pages;
        this.id=id;
        isExpanded=false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", pages=" + pages +
                '}';
    }
}

