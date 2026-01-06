import java.util.ArrayList;
// Custom Exception
class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
class Book {
    String title;
    String author;
    boolean isAvailable;
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}
public class LibraryManagementSystem2 {
    static ArrayList<Book> books = new ArrayList<>();
    // Search book by partial title
    public static void searchBook(String keyword) {
        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(b.title + " by " + b.author +
                        " | Status: " + (b.isAvailable ? "Available" : "Checked Out"));
            }
        }
    }
    // Checkout book
    public static void checkoutBook(String title) throws BookNotAvailableException {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                if (!b.isAvailable) {
                    throw new BookNotAvailableException("Book is not available.");
                }
                b.isAvailable = false;
                System.out.println("Book checked out successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
    public static void main(String[] args) {
        books.add(new Book("Java Programming", "James Gosling"));
        books.add(new Book("Data Structures", "Mark Allen"));
        searchBook("java");
        try {
            checkoutBook("Java Programming");
            checkoutBook("Java Programming");
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}