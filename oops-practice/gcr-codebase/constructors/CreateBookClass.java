public class Book {

    // Attributes should be private for encapsulation
    private String title;
    private String author;
    private double price;

    // Default constructor
    public Book() {
        this.title = "NA";
        this.author = "NA";
        this.price = 0.0;
    }

    // Parameterized constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
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
        if (price >= 0) {
            this.price = price;
        }
    }

    // Method to display book info
    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: " + price);
    }

    // Main method to test Book class
    public static void main(String[] args) {
        // Default book
        Book b1 = new Book();
        b1.displayInfo();

        // Parameterized book
        Book b2 = new Book("Java Programming", "John Doe", 499.99);
        b2.displayInfo();

        // Update price using setter
        b2.setPrice(599.99);
        b2.displayInfo();
    }
}
