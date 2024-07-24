import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
public class AppTest {
    // Write your tests here.

    private Book myBook = new Book("To Kill a Mockingbird", "Just a book", Book.Genre.NOVEL);
    
    /*
    These are just some generated Book object for a test
    I dont know why I'm getting 72.09% 
    */

    ///////---------------
    // Using this to test in case genere is null
    
    @Test
    public void testGetGenreCaseNull(){
        Book killBook = new Book("To Kill a Mockingbird", "Just a book");
        // Capture the standard output to verify the printed message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        Book.Genre expected = null;
        Book.Genre actual = killBook.getGenre();
        assertEquals(expected, actual);

        // Reset the standard output
        System.setOut(System.out);
    }   // It worked. Just had to test the output in case the genre was null, since it was possible to 
        // create a Book ojbect with a null genre! 
    ////////--------------

    // Testing getters

    @Test
    public void testGetTitle(){
        String expected = "To Kill a Mockingbird";
        String actual = myBook.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetGenre(){
        Book.Genre expected = Book.Genre.NOVEL;
        Book.Genre actual = myBook.getGenre();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDescription(){
        String expected = "Just a book";
        String actual = myBook.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsBorrowed(){
        boolean expected = false;
        boolean actual = myBook.isBorrowed();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetBorrowed(){
        
        boolean expected = true;
        boolean actual; 

        // Apply the setBorrowed function
        // We change the value to true
        myBook.setBorrowed(true);
        actual = myBook.isBorrowed();

        // Check
        assertEquals(expected, actual);


    }

    // Create a new book, just in case
    private Book myOtherBook = new Book("Moby-Dick", "Just a book", Book.Genre.ADVENTURE);

    // Creating a library for the tests
    Library myLibrary = new Library("LUT-tiedekirjasto", "Yliopistonkatu 34");
  


    // Testing the library functions.

    @Test
    public void testAddBook(){
        int expected = 2;
        int actual;

        // we add our books to myLibrary
        myLibrary.addBook(myBook);
        myLibrary.addBook(myOtherBook);
        // The library should have two books by now
        // The books are store inside ArrayList<Book> books
        actual = myLibrary.getAllBooks().size();

        assertEquals(expected, actual);

    }

    @Test
    public void testAddBookCaseNull() {
        // Capture the standard output to verify the printed message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Add a null book
        myLibrary.addBook(null);

        // Verify the message
        String expected = "Book not defined\n";
        String actual = outContent.toString();
        assertEquals(expected, actual);


        // Reset the standard output
        System.setOut(System.out);
    }
    
    @Test
    public void testGetBookByIdx(){
        myLibrary.addBook(myBook);
        myLibrary.addBook(myOtherBook);
        Book expected = myLibrary.getAllBooks().get(0);
        Book actual = myLibrary.getBookByIdx(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testBorrowBook(){
        myLibrary.addBook(myBook);
        myLibrary.addBook(myOtherBook);

        // The function borrowBook returns a Book
        // It modifies the setBorrowed for the Book
        // The print statement is of no interest
        // We should check that the returned Book has a isBorrowed return True
        // It takes the index of the book as a parameter
        Book borrowedBook = myLibrary.borrowBook(0);
        
        boolean expected = true;
        boolean actual = borrowedBook.isBorrowed();
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnBook(){
        myLibrary.addBook(myBook);
        myLibrary.addBook(myOtherBook);
        Book borrowedBook = myLibrary.borrowBook(0);

        boolean expected = false;

        // the function returnBook takes a Book as parameter
        // it return nothing. It just modifies the Book object inside myLibrary

        myLibrary.returnBook(borrowedBook);

        boolean actual = borrowedBook.isBorrowed();

        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetallBooks(){
        // The function returns books which is an ArrayList<Book>
        myLibrary.addBook(myBook);
        myLibrary.addBook(myOtherBook);
        // books is private
        // This is a bit tricky?

        // Easiest way is to create our own ArrayList and compare.
        ArrayList<Book> myBooks = new ArrayList<>();
        myBooks.add(myBook);
        myBooks.add(myOtherBook);

        int expected = myBooks.size();

        int actual = myLibrary.getAllBooks().size();


        assertEquals(expected, actual);

    }

    @Test
    public void testGetAvailableBooks(){

        // just gonna copy the books
        myLibrary.addBook(new Book("To Kill a Mockingbird", "Just a book", Book.Genre.NOVEL));
        myLibrary.addBook(new Book("Moby-Dick", "Just a book", Book.Genre.ADVENTURE));
        myLibrary.addBook(new Book("The Odyssey", "Just a book", Book.Genre.EPIC));
        myLibrary.addBook(new Book("Harry Potter", "Just a book", Book.Genre.FANTASY));

        // The functions returns a new list of books
        // The list has only the books which are available 
        // Meaning if Book.isBorrowed  returns false
        // Then it get added to the list.

        // We gonna need comparison library. 
        // This library will include only the books that wont be borrowed from myLibrary

        Library otherLibrary = new Library("TUL-tiedekirjasto", "Yliopistotie 43");
        //We use myLibrary to maintain the same objects
        otherLibrary.addBook(myLibrary.getBookByIdx(2));
        otherLibrary.addBook(myLibrary.getBookByIdx(3));

        ArrayList<Book> expected = otherLibrary.getAllBooks();

        // Now we borrow the the first two books.
        // Yes.. I'm lazy!

        Book firstBook = myLibrary.borrowBook(0);
        Book secondBook = myLibrary.borrowBook(1);

        ArrayList<Book> actual = myLibrary. getAvailableBooks();

        // Now we just compare

        assertEquals(expected, actual);
        
    }

    @Test
    public void testGetBooksByGenre(){
        // Function return ArrayList<Book>
        // It takes Book.Genre genre as parameter.
        myLibrary.addBook(new Book("To Kill a Mockingbird", "Just a book", Book.Genre.NOVEL));
        myLibrary.addBook(new Book("Moby-Dick", "Just a book", Book.Genre.ADVENTURE));
        myLibrary.addBook(new Book("The Odyssey", "Just a book", Book.Genre.EPIC));
        myLibrary.addBook(new Book("Harry Potter", "Just a book", Book.Genre.FANTASY));

        Library fantasyLibrary  = new Library("Fantasy Library", "Fantasy ave. 12");
        // Using the same object to maintain the memory address.
        fantasyLibrary.addBook(myLibrary.getBookByIdx(3));
        // We can just get the whole thing
        ArrayList<Book> expected = fantasyLibrary.getAllBooks();


        ArrayList<Book> actual = myLibrary.getBooksByGenre(Book.Genre.FANTASY);

        assertEquals(expected, actual);
    }



    // Lets just get getName and getAddress over with. 
    // I assume these are straight forward tests...

    @Test
    public void testGetName(){
        String expected = "LUT-tiedekirjasto";
        String actual = myLibrary.getName();
        assertEquals(expected, actual);
    }

   @Test
   public void testGetAddress(){
       String expected = "Yliopistonkatu 34";
       String actual = myLibrary.getAddress();
       assertEquals(expected, actual);
   }


    @Test
    public void testSetOpen(){
        myLibrary.setOpen(true);
        boolean expected = true;
        boolean actual = myLibrary.isOpen();
        assertEquals(expected, actual);

    }
    

}