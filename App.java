package com.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // populate library
        Library library = new Library("LUT-tiedekirjasto", "Yliopistonkatu 34");
        library.addBook(new Book("To Kill a Mockingbird", "Just a book", Book.Genre.NOVEL));
        library.addBook(new Book("Moby-Dick", "Just a book", Book.Genre.ADVENTURE));
        library.addBook(new Book("The Odyssey", "Just a book", Book.Genre.EPIC));
        library.addBook(new Book("Harry Potter", "Just a book", Book.Genre.FANTASY));

        ArrayList<Book> borrowedBooks = new ArrayList<>();

        boolean exit = false;
        while (!exit) {
            System.out.println("1) List all books, 2) List available books, 3) Borrow book, 4) Return book, 0) End");

            if (sc.hasNext()) {
                int i = 0;
                String stringInput = sc.nextLine();
                i = Integer.parseInt(stringInput);

                switch (i) {
                    case 1:
                        System.out.println("Listing all books");
                        System.out.println("-----------------");
                        ArrayList<Book> books = library.getAllBooks();
                        int n = 0;
                        for (Book book : books) {
                            System.out.println(n++ + ": " + book.getTitle());
                        }
                        break;

                    case 2:
                        System.out.println("Listing available books");
                        System.out.println("-----------------");
                        ArrayList<Book> available = library.getAvailableBooks();
                        int m = 0;
                        for (Book book : available) {
                            System.out.println(m++ + ": " + book.getTitle());
                        }
                        break;

                    case 3:
                        System.out.println("Which book would you like to rent? (index)");
                        int idx = Integer.parseInt(sc.nextLine());
                        Book borrowedBook = library.borrowBook(idx);
                        borrowedBooks.add(borrowedBook);
                        break;

                    case 4:
                        System.out.println("You have currently borrowed following books:");
                        int j = 0;
                        for (Book book : borrowedBooks) {
                            System.out.println(j++ + ": " + book.getTitle());
                        }
                        System.out.println("Which one would you like to return? (index)");
                        int id = Integer.parseInt(sc.nextLine());
                        Book book = borrowedBooks.remove(id);
                        library.returnBook(book);
                        break;
                    case 0:
                        System.out.println("Ending app");
                        sc.close();
                        exit = true;
                        break;
                }
            }
        }
    }
}
