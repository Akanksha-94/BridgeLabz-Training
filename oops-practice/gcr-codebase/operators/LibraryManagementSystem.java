public class LibraryManagementSystem {

    public static void main(String[] args) {

        // Creating Book object
        Book b1 = new Book("Effective Java", "Joshua Bloch", "978-0134685991");

        // Display Library Name
        Book.displayLibraryName();

        // Check using instanceof before displayingNo Chalkya. Control shift control shift. Control shift P clean. Control. Create Java project Java. Clean karma. Tools Majaoki harbor tools. Adds up the project build grid, slab, silk roof. Select the project location. 
        if (b1 instanceof Book) {
            b1.displayDetails();
        }
    }
}

class Book {

    // 1. Static variable (shared)
    static String libraryName = "Egmore Library";

    // 3. final variable
    private final String isbn;

    // Instance variables
    private String title;
    private String author;

    // 2. Using 'this' in constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    // Static method
    public static void displayLibraryName() {
        System.out.println("Library Name: " + libraryName);
    }

    // Method to display book details
    public void displayDetails() {
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("ISBN: " + this.isbn);
    }
}
