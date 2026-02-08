import java.util.*;

// Observer Pattern - Interface
interface Observer {
    void update(String message);
}

// User Interface extending Observer
interface User extends Observer {
    void showRole();
    String getName();
}

// Concrete User Classes
class Student implements User {
    private String name;
    
    public Student(String name) {
        this.name = name;
    }
    
    @Override
    public void showRole() {
        System.out.println(name + " is a Student.");
    }
    
    @Override
    public void update(String msg) {
        System.out.println(name + " notified: " + msg);
    }
    
    @Override
    public String getName() {
        return name;
    }
}

class Faculty implements User {
    private String name;
    
    public Faculty(String name) {
        this.name = name;
    }
    
    @Override
    public void showRole() {
        System.out.println(name + " is a Faculty.");
    }
    
    @Override
    public void update(String msg) {
        System.out.println(name + " notified: " + msg);
    }
    
    @Override
    public String getName() {
        return name;
    }
}

class Librarian implements User {
    private String name;
    
    public Librarian(String name) {
        this.name = name;
    }
    
    @Override
    public void showRole() {
        System.out.println(name + " is a Librarian.");
    }
    
    @Override
    public void update(String msg) {
        System.out.println(name + " notified: " + msg);
    }
    
    @Override
    public String getName() {
        return name;
    }
}

// Factory Pattern - User Factory
class UserFactory {
    public static User createUser(String type, String name) {
        switch (type.toLowerCase()) {
            case "student":
                return new Student(name);
            case "faculty":
                return new Faculty(name);
            case "librarian":
                return new Librarian(name);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}

// Builder Pattern - Book
class Book {
    private String title;
    private String author;
    private String edition;
    private String genre;
    private String publisher;
    
    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.edition = builder.edition;
        this.genre = builder.genre;
        this.publisher = builder.publisher;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', edition='" + edition + 
               "', genre='" + genre + "', publisher='" + publisher + "'}";
    }
    
    public static class BookBuilder {
        private String title;
        private String author;
        private String edition;
        private String genre;
        private String publisher;
        
        public BookBuilder(String title) {
            this.title = title;
        }
        
        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }
        
        public BookBuilder edition(String edition) {
            this.edition = edition;
            return this;
        }
        
        public BookBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }
        
        public BookBuilder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }
        
        public Book build() {
            return new Book(this);
        }
    }
}

// Singleton Pattern - Library Catalog
class LibraryCatalog {
    private static LibraryCatalog instance;
    private List<Book> books = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    
    private LibraryCatalog() {}
    
    public static synchronized LibraryCatalog getInstance() {
        if (instance == null) {
            instance = new LibraryCatalog();
        }
        return instance;
    }
    
    public void addBook(Book book) {
        books.add(book);
        notifyObservers("New book available: " + book.getTitle());
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
    
    public void displayCatalog() {
        System.out.println("\n=== Library Catalog ===");
        if (books.isEmpty()) {
            System.out.println("No books in catalog.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
    }
}

// Main Application
public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Smart University Library Management System ===\n");
        
        // Singleton - Get catalog instance
        LibraryCatalog catalog = LibraryCatalog.getInstance();
        System.out.println("Library Catalog initialized (Singleton)\n");
        
        // Factory - Create users
        System.out.println("=== Creating Users (Factory Pattern) ===");
        User student1 = UserFactory.createUser("student", "Alice");
        User student2 = UserFactory.createUser("student", "Charlie");
        User faculty = UserFactory.createUser("faculty", "Dr. Bob");
        User librarian = UserFactory.createUser("librarian", "Ms. Diana");
        
        student1.showRole();
        student2.showRole();
        faculty.showRole();
        librarian.showRole();
        
        // Observer - Subscribe users to notifications
        System.out.println("\n=== Subscribing Users to Notifications (Observer Pattern) ===");
        catalog.addObserver(student1);
        catalog.addObserver(faculty);
        catalog.addObserver(librarian);
        System.out.println("Alice, Dr. Bob, and Ms. Diana subscribed to notifications.\n");
        
        // Builder - Create books with optional attributes
        System.out.println("=== Adding Books to Catalog (Builder Pattern) ===\n");
        
        Book book1 = new Book.BookBuilder("Design Patterns")
                .author("GoF")
                .edition("2nd")
                .genre("Software Engineering")
                .publisher("Addison-Wesley")
                .build();
        catalog.addBook(book1);
        
        System.out.println();
        
        Book book2 = new Book.BookBuilder("Data Structures and Algorithms")
                .author("Cormen, Leiserson, Rivest, Stein")
                .edition("3rd")
                .genre("Computer Science")
                .build();
        catalog.addBook(book2);
        
        System.out.println();
        
        Book book3 = new Book.BookBuilder("Clean Code")
                .author("Robert C. Martin")
                .genre("Software Development")
                .build();
        catalog.addBook(book3);
        
        // Display catalog
        catalog.displayCatalog();
        
        // Demonstrate singleton - same instance
        System.out.println("\n=== Verifying Singleton ===");
        LibraryCatalog catalog2 = LibraryCatalog.getInstance();
        System.out.println("Same catalog instance? " + (catalog == catalog2));
        System.out.println("Books in catalog: " + catalog2.getBooks().size());
    }
}