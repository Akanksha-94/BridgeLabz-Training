import java.util.*;

/**
 * SmartShelf - Real-Time Book Arrangement
 * Demonstrates Insertion Sort
 * 
 * Story: In a digital library kiosk, as users add books to their reading list,
 * the system must keep the list sorted alphabetically by title.
 * Since books are added one at a time and the list is mostly sorted, Insertion
 * Sort fits perfectly.
 */

class Book implements Comparable<Book> {
  private String bookId;
  private String title;
  private String author;
  private double price;
  private int yearPublished;

  public Book(String bookId, String title, String author, double price, int yearPublished) {
    this.bookId = bookId;
    this.title = title;
    this.author = author;
    this.price = price;
    this.yearPublished = yearPublished;
  }

  public String getBookId() {
    return bookId;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public double getPrice() {
    return price;
  }

  public int getYearPublished() {
    return yearPublished;
  }

  @Override
  public int compareTo(Book other) {
    // Sort alphabetically by title
    return this.title.compareToIgnoreCase(other.title);
  }

  @Override
  public String toString() {
    return String.format("%-20s | %-25s | %-15s | $%-8.2f | %d",
        bookId, title, author, price, yearPublished);
  }

  public String toLongString() {
    return String.format(
        "Book ID: %s\n" +
            "Title: %s\n" +
            "Author: %s\n" +
            "Price: $%.2f\n" +
            "Year: %d",
        bookId, title, author, price, yearPublished);
  }
}

class SmartShelf {
  private List<Book> books;
  private int comparisons;
  private int swaps;

  public SmartShelf() {
    this.books = new ArrayList<>();
    this.comparisons = 0;
    this.swaps = 0;
  }

  /**
   * Insertion Sort - optimized for adding books one at a time to a mostly sorted
   * list
   * Time Complexity: O(n) for nearly sorted, O(n^2) worst case
   * Space Complexity: O(1)
   */
  public void addBook(Book newBook) {
    books.add(newBook);
    insertionSort();
    System.out.println("✓ Added: " + newBook.getTitle() + " (List sorted - " +
        comparisons + " comparisons, " + swaps + " swaps)");
    resetStatistics();
  }

  private void insertionSort() {
    int n = books.size();

    // Start from second element (index 1)
    for (int i = 1; i < n; i++) {
      Book key = books.get(i);
      int j = i - 1;

      // Compare and shift elements
      while (j >= 0) {
        comparisons++;
        if (books.get(j).compareTo(key) > 0) {
          books.set(j + 1, books.get(j));
          swaps++;
          j--;
        } else {
          break;
        }
      }

      // Insert the key at correct position
      books.set(j + 1, key);
    }
  }

  /**
   * Alternative: Sort books by different criteria
   */
  public void sortByAuthor() {
    books.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
    System.out.println("✓ Books sorted by author");
  }

  public void sortByPrice() {
    books.sort(Comparator.comparingDouble(Book::getPrice));
    System.out.println("✓ Books sorted by price");
  }

  public void sortByYear() {
    books.sort(Comparator.comparingInt(Book::getYearPublished));
    System.out.println("✓ Books sorted by publication year");
  }

  public void sortByTitle() {
    books.sort(Book::compareTo);
    System.out.println("✓ Books sorted by title");
  }

  public Book removeBook(String bookId) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getBookId().equals(bookId)) {
        Book removed = books.remove(i);
        System.out.println("✓ Removed: " + removed.getTitle());
        return removed;
      }
    }
    System.out.println("✗ Book not found: " + bookId);
    return null;
  }

  public Book searchBook(String title) {
    for (Book book : books) {
      if (book.getTitle().equalsIgnoreCase(title)) {
        return book;
      }
    }
    return null;
  }

  public List<Book> searchByAuthor(String author) {
    List<Book> results = new ArrayList<>();
    for (Book book : books) {
      if (book.getAuthor().equalsIgnoreCase(author)) {
        results.add(book);
      }
    }
    return results;
  }

  public List<Book> getBooksInPriceRange(double minPrice, double maxPrice) {
    List<Book> results = new ArrayList<>();
    for (Book book : books) {
      if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
        results.add(book);
      }
    }
    return results;
  }

  public void displayAllBooks() {
    if (books.isEmpty()) {
      System.out.println("\nSmartShelf is empty - no books available\n");
      return;
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                        SMARTSHELF CONTENTS                         ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");
    System.out.printf("%-5s | %-20s | %-25s | %-15s | %-8s | %s\n",
        "#", "Book ID", "Title", "Author", "Price", "Year");
    System.out.println("╟────────────────────────────────────────────────────────────────────╢");

    for (int i = 0; i < books.size(); i++) {
      System.out.printf("%-5d | %s\n", (i + 1), books.get(i));
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
    System.out.println("Total books: " + books.size() + "\n");
  }

  public void displayBookDetails(String bookId) {
    for (Book book : books) {
      if (book.getBookId().equals(bookId)) {
        System.out.println("\n" + book.toLongString() + "\n");
        return;
      }
    }
    System.out.println("✗ Book not found: " + bookId);
  }

  public int getBookCount() {
    return books.size();
  }

  private void resetStatistics() {
    this.comparisons = 0;
    this.swaps = 0;
  }

  public void printStatistics() {
    System.out.println("Total books in shelf: " + books.size());
    System.out.println("Books are sorted alphabetically by title");
  }
}

public class SmartShelfDemo {
  public static void main(String[] args) {
    SmartShelf shelf = new SmartShelf();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║              SMARTSHELF - DIGITAL LIBRARY KIOSK                    ║");
    System.out.println("║              Real-Time Book Arrangement (Insertion Sort)           ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    // Demo: Add books one at a time - they get inserted in sorted order
    System.out.println("--- PHASE 1: Adding Books to Shelf ---");
    shelf.addBook(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", 10.99, 1925));
    shelf.addBook(new Book("B002", "To Kill a Mockingbird", "Harper Lee", 12.99, 1960));
    shelf.addBook(new Book("B003", "1984", "George Orwell", 13.99, 1949));
    shelf.addBook(new Book("B004", "Pride and Prejudice", "Jane Austen", 9.99, 1813));
    shelf.addBook(new Book("B005", "The Catcher in the Rye", "J.D. Salinger", 11.99, 1951));
    shelf.addBook(new Book("B006", "Brave New World", "Aldous Huxley", 14.99, 1932));

    shelf.displayAllBooks();

    // Search operations
    System.out.println("--- PHASE 2: Search Operations ---");
    Book found = shelf.searchBook("1984");
    if (found != null) {
      System.out.println("✓ Found: " + found.getTitle());
    }

    System.out.println("\nBooks by George Orwell:");
    List<Book> authorBooks = shelf.searchByAuthor("George Orwell");
    for (Book b : authorBooks) {
      System.out.println("  • " + b);
    }

    // Price range search
    System.out.println("\nBooks in price range $10-$13:");
    List<Book> priceBooks = shelf.getBooksInPriceRange(10, 13);
    for (Book b : priceBooks) {
      System.out.println("  • " + b);
    }

    // Add more books
    System.out.println("\n--- PHASE 3: Adding More Books ---");
    shelf.addBook(new Book("B007", "Moby Dick", "Herman Melville", 15.99, 1851));
    shelf.addBook(new Book("B008", "Alice in Wonderland", "Lewis Carroll", 8.99, 1865));
    shelf.addBook(new Book("B009", "Jane Eyre", "Charlotte Brontë", 11.99, 1847));

    shelf.displayAllBooks();

    // Display details
    System.out.println("--- PHASE 4: Book Details ---");
    shelf.displayBookDetails("B002");

    // Sort by different criteria
    System.out.println("--- PHASE 5: Sorting by Different Criteria ---");
    shelf.sortByAuthor();
    shelf.displayAllBooks();

    shelf.sortByPrice();
    shelf.displayAllBooks();

    shelf.sortByYear();
    shelf.displayAllBooks();

    shelf.sortByTitle(); // Back to alphabetical
    shelf.displayAllBooks();

    // Remove a book
    System.out.println("--- PHASE 6: Remove Book ---");
    shelf.removeBook("B004");
    shelf.displayAllBooks();

    // Statistics
    System.out.println("--- PHASE 7: Shelf Statistics ---");
    shelf.printStatistics();

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                      SIMULATION COMPLETED                          ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
  }
}
