package com.example;

public class Book {
    enum Genre {
        FICTION,
        NOVEL,
        THRILLER,
        SCIENCE_FICTION,
        FANTASY,
        MYSTERY,
        HORROR,
        ROMANCE,
        ADVENTURE,
        EPIC
    }

    private String title;
    private String description;
    private Genre genre;
    private boolean borrowed = false;

    public Book(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Book(String title, String description, Genre genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean isBorrowed() {
        return borrowed;
    }
}
