

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {
  private static LibraryCatalog instance;
  private List<Book> books = new ArrayList<>();
  private List<Observer> observers = new ArrayList<>();

  // private constructor
  private LibraryCatalog() {
  }

  // thread-safe singleton
  public static synchronized LibraryCatalog getInstance() {
    if (instance == null) {
      instance = new LibraryCatalog();
    }
    return instance;
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  public void addBook(Book book) {
    books.add(book);
    notifyObservers(book.getTitle());
  }

  private void notifyObservers(String bookTitle) {
    for (Observer observer : observers) {
      observer.update("New book available: " + bookTitle);
    }
  }
}