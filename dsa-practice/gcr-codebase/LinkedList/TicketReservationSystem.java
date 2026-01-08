import java.time.*;
import java.util.*;

public class TicketReservationSystem {
  static class Ticket {
    String ticketId, customer, movie, seat;
    LocalDateTime time;
    Ticket next;

    Ticket(String tid, String c, String m, String s) {
      ticketId = tid;
      customer = c;
      movie = m;
      seat = s;
      time = LocalDateTime.now();
    }
  }

  private Ticket tail; // circular: tail.next is head

  public void addReservation(String tid, String customer, String movie, String seat) {
    Ticket t = new Ticket(tid, customer, movie, seat);
    if (tail == null) {
      tail = t;
      t.next = t;
    } else {
      t.next = tail.next;
      tail.next = t;
      tail = t;
    }
  }

  public boolean removeByTicketId(String tid) {
    if (tail == null)
      return false;
    Ticket prev = tail, cur = tail.next;
    do {
      if (cur.ticketId.equals(tid)) {
        if (cur == tail && cur.next == tail) {
          tail = null;
          return true;
        }
        prev.next = cur.next;
        if (cur == tail)
          tail = prev;
        return true;
      }
      prev = cur;
      cur = cur.next;
    } while (cur != tail.next);
    return false;
  }

  public void displayAll() {
    if (tail == null)
      return;
    Ticket cur = tail.next;
    do {
      System.out.println(format(cur));
      cur = cur.next;
    } while (cur != tail.next);
  }

  public List<Ticket> searchByCustomerOrMovie(String key) {
    List<Ticket> res = new ArrayList<>();
    if (tail == null)
      return res;
    Ticket cur = tail.next;
    do {
      if (cur.customer.equalsIgnoreCase(key) || cur.movie.equalsIgnoreCase(key))
        res.add(cur);
      cur = cur.next;
    } while (cur != tail.next);
    return res;
  }

  public int count() {
    if (tail == null)
      return 0;
    int c = 0;
    Ticket cur = tail.next;
    do {
      c++;
      cur = cur.next;
    } while (cur != tail.next);
    return c;
  }

  private String format(Ticket t) {
    return String.format("%s | %s | %s | seat=%s | booked=%s", t.ticketId, t.customer, t.movie, t.seat,
        t.time.toString());
  }

  public static void main(String[] args) {
    TicketReservationSystem tr = new TicketReservationSystem();
    tr.addReservation("T001", "Alice", "Avengers", "A1");
    tr.addReservation("T002", "Bob", "Avengers", "A2");
    tr.addReservation("T003", "Carol", "Dune", "B1");
    System.out.println("All tickets:");
    tr.displayAll();
    System.out.println("Search for Avengers:");
    for (var t : tr.searchByCustomerOrMovie("Avengers"))
      System.out.println(tr.format(t));
    System.out.println("Count: " + tr.count());
    tr.removeByTicketId("T002");
    System.out.println("After removal:");
    tr.displayAll();
  }
}
