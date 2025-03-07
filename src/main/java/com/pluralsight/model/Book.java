package com.pluralsight.model;

public class Book {
    private long id;
    private String title;
    private int rating;

    // Getter and Setter for 'id'
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getter and Setter for 'title'
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Constructor (optional)
    public Book() {
    }

    public Book(long id, String title, int rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}