import java.util.*;

public class LibraryManagementSystem {
  static class Book {
    String title, author, genre, id;
    boolean available;
    Book prev, next;

    Book(String t, String a, String g, String id, boolean av) {
      title = t;
      author = a;
      genre = g;
      this.id = id;
      available = av;
    }
  }

  private Book head, tail;

  public void addAtBeginning(String t, String a, String g, String id, boolean av) {
    Book n = new Book(t, a, g, id, av);
    if (head == null)
      head = tail = n;
    else {
      n.next = head;
      head.prev = n;
      head = n;
    }
  }

  public void addAtEnd(String t, String a, String g, String id, boolean av) {
    Book n = new Book(t, a, g, id, av);
    if (tail == null)
      head = tail = n;
    else {
      tail.next = n;
      n.prev = tail;
      tail = n;
    }
  }

  public void addAtPosition(String t, String a, String g, String id, boolean av, int pos) {
    if (pos <= 0) {
      addAtBeginning(t, a, g, id, av);
      return;
    }
    Book cur = head;
    int i = 0;
    while (cur != null && i < pos - 1) {
      cur = cur.next;
      i++;
    }
    if (cur == null || cur == tail) {
      addAtEnd(t, a, g, id, av);
      return;
    }
    Book n = new Book(t, a, g, id, av);
    n.next = cur.next;
    n.prev = cur;
    cur.next.prev = n;
    cur.next = n;
  }

  public boolean removeById(String id) {
    for (Book cur = head; cur != null; cur = cur.next)
      if (cur.id.equals(id)) {
        if (cur.prev != null)
          cur.prev.next = cur.next;
        else
          head = cur.next;
        if (cur.next != null)
          cur.next.prev = cur.prev;
        else
          tail = cur.prev;
        return true;
      }
    return false;
  }

  public List<Book> searchByTitle(String title) {
    List<Book> res = new ArrayList<>();
    for (Book cur = head; cur != null; cur = cur.next)
      if (cur.title.equalsIgnoreCase(title))
        res.add(cur);
    return res;
  }

  public List<Book> searchByAuthor(String author) {
    List<Book> res = new ArrayList<>();
    for (Book cur = head; cur != null; cur = cur.next)
      if (cur.author.equalsIgnoreCase(author))
        res.add(cur);
    return res;
  }

  public boolean updateAvailability(String id, boolean av) {
    for (Book cur = head; cur != null; cur = cur.next)
      if (cur.id.equals(id)) {
        cur.available = av;
        return true;
      }
    return false;
  }

  public void displayForward() {
    for (Book cur = head; cur != null; cur = cur.next)
      System.out.println(format(cur));
  }

  public void displayReverse() {
    for (Book cur = tail; cur != null; cur = cur.prev)
      System.out.println(format(cur));
  }

  public int count() {
    int c = 0;
    for (Book cur = head; cur != null; cur = cur.next)
      c++;
    return c;
  }

  private String format(Book b) {
    return String.format("%s | %s | %s | %s | available=%b", b.id, b.title, b.author, b.genre, b.available);
  }

  public static void main(String[] args) {
    LibraryManagementSystem lib = new LibraryManagementSystem();
    lib.addAtEnd("The Hobbit", "Tolkien", "Fantasy", "B001", true);
    lib.addAtEnd("1984", "Orwell", "Dystopia", "B002", true);
    lib.addAtBeginning("Clean Code", "Robert C. Martin", "Programming", "B003", true);
    System.out.println("Books forward:");
    lib.displayForward();
    System.out.println("Books reverse:");
    lib.displayReverse();
    System.out.println("Count: " + lib.count());
    lib.updateAvailability("B002", false);
    System.out.println("After checkout B002:");
    lib.displayForward();
  }
}
