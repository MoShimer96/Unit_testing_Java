package com.example;

import java.util.ArrayList;

public class Library {
    private String name;
    private String address;
    private boolean open;
    private ArrayList<Book> books = new ArrayList<>();

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        } else {
            System.out.println("Book not defined");
        }
    }

    public Book getBookByIdx(int idx) {
        return books.get(idx);
    }

    public Book borrowBook(int idx) {
        Book book = books.get(idx);
        book.setBorrowed(true);
        System.out.println("Borrowed book: " + book.getTitle());
        return book;
    }

    public void returnBook(Book book) {
        book.setBorrowed(false);
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> available = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                available.add(book);
            }
        }
        return available;
    }

    public ArrayList<Book> getBooksByGenre(Book.Genre genre) {
        ArrayList<Book> matches = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre() == genre) {
                matches.add(book);
            }
        }

        return matches;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
