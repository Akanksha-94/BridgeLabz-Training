import java.util.*;

public class MovieManagementSystem {
  static class Movie {
    String title, director;
    int year;
    double rating;
    Movie prev, next;

    Movie(String t, String d, int y, double r) {
      title = t;
      director = d;
      year = y;
      rating = r;
    }
  }

  private Movie head, tail;

  public void addAtBeginning(String t, String d, int y, double r) {
    Movie n = new Movie(t, d, y, r);
    if (head == null) {
      head = tail = n;
    } else {
      n.next = head;
      head.prev = n;
      head = n;
    }
  }

  public void addAtEnd(String t, String d, int y, double r) {
    Movie n = new Movie(t, d, y, r);
    if (tail == null) {
      head = tail = n;
    } else {
      tail.next = n;
      n.prev = tail;
      tail = n;
    }
  }

  public void addAtPosition(String t, String d, int y, double r, int pos) {
    if (pos <= 0) {
      addAtBeginning(t, d, y, r);
      return;
    }
    Movie cur = head;
    int idx = 0;
    while (cur != null && idx < pos - 1) {
      cur = cur.next;
      idx++;
    }
    if (cur == null || cur == tail) {
      addAtEnd(t, d, y, r);
      return;
    }
    Movie n = new Movie(t, d, y, r);
    n.next = cur.next;
    n.prev = cur;
    cur.next.prev = n;
    cur.next = n;
  }

  public boolean removeByTitle(String title) {
    Movie cur = head;
    while (cur != null) {
      if (cur.title.equals(title)) {
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
      cur = cur.next;
    }
    return false;
  }

  public List<Movie> searchByDirector(String director) {
    List<Movie> res = new ArrayList<>();
    for (Movie c = head; c != null; c = c.next)
      if (c.director.equalsIgnoreCase(director))
        res.add(c);
    return res;
  }

  public List<Movie> searchByRating(double rating) {
    List<Movie> res = new ArrayList<>();
    for (Movie c = head; c != null; c = c.next)
      if (Double.compare(c.rating, rating) == 0)
        res.add(c);
    return res;
  }

  public void updateRating(String title, double newRating) {
    for (Movie c = head; c != null; c = c.next)
      if (c.title.equals(title)) {
        c.rating = newRating;
        return;
      }
  }

  public void displayForward() {
    for (Movie c = head; c != null; c = c.next)
      System.out.println(format(c));
  }

  public void displayReverse() {
    for (Movie c = tail; c != null; c = c.prev)
      System.out.println(format(c));
  }

  private String format(Movie m) {
    return String.format("%s | %s | %d | %.1f", m.title, m.director, m.year, m.rating);
  }

  public static void main(String[] args) {
    MovieManagementSystem mms = new MovieManagementSystem();
    mms.addAtEnd("Inception", "Nolan", 2010, 8.8);
    mms.addAtBeginning("Interstellar", "Nolan", 2014, 8.6);
    mms.addAtEnd("The Matrix", "Wachowski", 1999, 8.7);
    System.out.println("Forward:");
    mms.displayForward();
    System.out.println("Reverse:");
    mms.displayReverse();
    System.out.println("Search by Director Nolan:");
    for (var mv : mms.searchByDirector("Nolan"))
      System.out.println(mms.format(mv));
    mms.updateRating("Inception", 9.0);
    System.out.println("After update:");
    mms.displayForward();
    mms.removeByTitle("Interstellar");
    System.out.println("After removal:");
    mms.displayForward();
  }
}
