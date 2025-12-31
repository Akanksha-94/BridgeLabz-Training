public class Book {

    // Private attributes
    private String title;
    private String author;
    private double price;
    private boolean available;

    // Default constructor
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.price = 0.0;
        this.available = true; // book is available by default
    }

    // Parameterized constructor
    public Book(String title, String author, double price, boolean available) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.available = available;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price >= 0){
            this.price = price;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Method to borrow the book
    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println("You have successfully borrowed \"" + title + "\".");
        } else {
            System.out.println("Sorry, \"" + title + "\" is currently not available.");
        }
    }

    // Method to display book details
    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author +
                           ", Price: " + price + ", Available: " + available);
    }

    // Main method to test the Book class
    public static void main(String[] args) {
        // Creating a book using parameterized constructor
        Book b1 = new Book("Java Programming", "John Doe", 499.99, true);
        b1.displayBookInfo();

        // Borrow the book
        b1.borrowBook();  // Should succeed
        b1.displayBookInfo();

        // Try to borrow again
        b1.borrowBook();  // Should say not available

        // Create another book using default constructor
        Book b2 = new Book();
        b2.displayBookInfo();
    }
}
