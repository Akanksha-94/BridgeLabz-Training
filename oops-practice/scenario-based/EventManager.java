import java.util.*;

/**
 * EventManager - Ticket Price Optimizer
 * Demonstrates Quick Sort
 * 
 * Story: An event booking portal sorts millions of tickets by price before
 * showing the top 50
 * cheapest or most expensive. Since speed is crucial and data is unsorted,
 * Quick Sort is used
 * for its average-case performance.
 */

class Ticket implements Comparable<Ticket> {
  private String ticketId;
  private String eventName;
  private double price;
  private String seatCategory; // VIP, GENERAL, ECONOMY
  private boolean isBooked;
  private long createdTime;

  public Ticket(String ticketId, String eventName, double price, String seatCategory) {
    this.ticketId = ticketId;
    this.eventName = eventName;
    this.price = price;
    this.seatCategory = seatCategory;
    this.isBooked = false;
    this.createdTime = System.currentTimeMillis();
  }

  public String getTicketId() {
    return ticketId;
  }

  public String getEventName() {
    return eventName;
  }

  public double getPrice() {
    return price;
  }

  public String getSeatCategory() {
    return seatCategory;
  }

  public boolean isBooked() {
    return isBooked;
  }

  public void setBooked(boolean booked) {
    isBooked = booked;
  }

  @Override
  public int compareTo(Ticket other) {
    // Sort by price (ascending)
    return Double.compare(this.price, other.price);
  }

  @Override
  public String toString() {
    return String.format("ID: %-8s | Event: %-25s | Price: $%-8.2f | Category: %-8s | Booked: %s",
        ticketId, eventName, price, seatCategory, (isBooked ? "Yes" : "No"));
  }

  public String toDetailedString() {
    return String.format(
        "Ticket ID: %s\n" +
            "Event: %s\n" +
            "Price: $%.2f\n" +
            "Category: %s\n" +
            "Booked: %s\n" +
            "Created: %d",
        ticketId, eventName, price, seatCategory, (isBooked ? "Yes" : "No"), createdTime);
  }
}

class QuickSortTickets {
  private long comparisons;
  private long swaps;

  public QuickSortTickets() {
    this.comparisons = 0;
    this.swaps = 0;
  }

  /**
   * Quick Sort Implementation
   * Time Complexity: O(n log n) average case, O(n^2) worst case
   * Space Complexity: O(log n) for recursion stack
   * Best for: Large, random datasets
   */
  public void quickSort(List<Ticket> tickets) {
    if (tickets == null || tickets.isEmpty()) {
      return;
    }

    resetStatistics();
    quickSortHelper(tickets, 0, tickets.size() - 1);
  }

  private void quickSortHelper(List<Ticket> tickets, int low, int high) {
    if (low < high) {
      int pi = partition(tickets, low, high);

      // Recursively sort elements before and after partition
      quickSortHelper(tickets, low, pi - 1);
      quickSortHelper(tickets, pi + 1, high);
    }
  }

  private int partition(List<Ticket> tickets, int low, int high) {
    // Use middle element as pivot for better performance
    int mid = low + (high - low) / 2;
    swap(tickets, mid, high);

    double pivot = tickets.get(high).getPrice();
    int i = low - 1;

    for (int j = low; j < high; j++) {
      comparisons++;
      if (tickets.get(j).getPrice() < pivot) {
        i++;
        swap(tickets, i, j);
      }
    }

    swap(tickets, i + 1, high);
    return i + 1;
  }

  private void swap(List<Ticket> tickets, int i, int j) {
    if (i != j) {
      Ticket temp = tickets.get(i);
      tickets.set(i, tickets.get(j));
      tickets.set(j, temp);
      swaps++;
    }
  }

  public long getComparisons() {
    return comparisons;
  }

  public long getSwaps() {
    return swaps;
  }

  private void resetStatistics() {
    this.comparisons = 0;
    this.swaps = 0;
  }
}

public class EventManager {
  private List<Ticket> allTickets;
  private QuickSortTickets sorter;

  public EventManager() {
    this.allTickets = new ArrayList<>();
    this.sorter = new QuickSortTickets();
  }

  public void addTicket(Ticket ticket) {
    allTickets.add(ticket);
  }

  public void addMultipleTickets(List<Ticket> tickets) {
    allTickets.addAll(tickets);
  }

  public void sortTicketsByPrice() {
    System.out.println("\nSorting " + allTickets.size() + " tickets by price using Quick Sort...");
    long startTime = System.nanoTime();

    sorter.quickSort(allTickets);

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

    System.out.println("✓ Sorting completed in " + duration + "ms");
    System.out.println("  Comparisons: " + sorter.getComparisons());
    System.out.println("  Swaps: " + sorter.getSwaps());
  }

  public List<Ticket> getTopCheapest(int count) {
    sortTicketsByPrice();

    List<Ticket> cheapest = new ArrayList<>();
    for (int i = 0; i < Math.min(count, allTickets.size()); i++) {
      if (!allTickets.get(i).isBooked()) {
        cheapest.add(allTickets.get(i));
      }
    }

    System.out.println("✓ Retrieved top " + cheapest.size() + " cheapest available tickets\n");
    return cheapest;
  }

  public List<Ticket> getTopExpensive(int count) {
    sortTicketsByPrice();

    List<Ticket> expensive = new ArrayList<>();
    for (int i = allTickets.size() - 1; i >= 0 && expensive.size() < count; i--) {
      if (!allTickets.get(i).isBooked()) {
        expensive.add(allTickets.get(i));
      }
    }

    System.out.println("✓ Retrieved top " + expensive.size() + " most expensive available tickets\n");
    return expensive;
  }

  public List<Ticket> getTicketsInPriceRange(double minPrice, double maxPrice) {
    sortTicketsByPrice();

    List<Ticket> inRange = new ArrayList<>();
    for (Ticket ticket : allTickets) {
      if (ticket.getPrice() >= minPrice && ticket.getPrice() <= maxPrice) {
        inRange.add(ticket);
      }
    }

    System.out.println("✓ Found " + inRange.size() + " tickets in price range $" +
        String.format("%.2f", minPrice) + " - $" +
        String.format("%.2f", maxPrice) + "\n");
    return inRange;
  }

  public List<Ticket> getTicketsByCategory(String category) {
    List<Ticket> byCategory = new ArrayList<>();
    for (Ticket ticket : allTickets) {
      if (ticket.getSeatCategory().equalsIgnoreCase(category)) {
        byCategory.add(ticket);
      }
    }
    return byCategory;
  }

  public boolean bookTicket(String ticketId) {
    for (Ticket ticket : allTickets) {
      if (ticket.getTicketId().equals(ticketId)) {
        if (!ticket.isBooked()) {
          ticket.setBooked(true);
          System.out.println("✓ Ticket " + ticketId + " booked successfully");
          return true;
        } else {
          System.out.println("✗ Ticket " + ticketId + " is already booked");
          return false;
        }
      }
    }
    System.out.println("✗ Ticket not found: " + ticketId);
    return false;
  }

  public void displayAllTickets() {
    if (allTickets.isEmpty()) {
      System.out.println("\nNo tickets available\n");
      return;
    }

    System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                          ALL TICKETS                                  ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");

    for (int i = 0; i < allTickets.size(); i++) {
      System.out.println((i + 1) + ". " + allTickets.get(i));
    }

    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
  }

  public void displayTickets(List<Ticket> tickets, String title) {
    if (tickets.isEmpty()) {
      System.out.println("\n" + title + ": No tickets found\n");
      return;
    }

    System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
    System.out.println("║  " + String.format("%-65s", title) + "  ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");

    for (int i = 0; i < tickets.size(); i++) {
      System.out.println((i + 1) + ". " + tickets.get(i));
    }

    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
  }

  public void displayStatistics() {
    System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                        TICKET STATISTICS                              ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");

    int totalTickets = allTickets.size();
    int bookedTickets = 0;
    double minPrice = Double.MAX_VALUE;
    double maxPrice = Double.MIN_VALUE;
    double totalRevenue = 0;

    for (Ticket ticket : allTickets) {
      if (ticket.isBooked()) {
        bookedTickets++;
        totalRevenue += ticket.getPrice();
      }
      minPrice = Math.min(minPrice, ticket.getPrice());
      maxPrice = Math.max(maxPrice, ticket.getPrice());
    }

    System.out.println("Total Tickets: " + totalTickets);
    System.out.println("Booked Tickets: " + bookedTickets);
    System.out.println("Available Tickets: " + (totalTickets - bookedTickets));
    System.out.println("Booking Rate: " + String.format("%.2f", (bookedTickets * 100.0 / totalTickets)) + "%");
    System.out.println("Price Range: $" + String.format("%.2f", minPrice) + " - $" +
        String.format("%.2f", maxPrice));
    System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
  }

  public static void main(String[] args) {
    EventManager manager = new EventManager();

    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
    System.out.println("║           EVENT MANAGER - TICKET PRICE OPTIMIZER                     ║");
    System.out.println("║           (Quick Sort for Efficient Price Sorting)                   ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

    // PHASE 1: Add initial tickets
    System.out.println("\n--- PHASE 1: Adding Initial Tickets ---");
    String[] events = { "Concert 2026", "Theater Show", "Sports Event", "Comedy Night", "Music Festival" };
    String[] categories = { "VIP", "GENERAL", "ECONOMY" };

    Random random = new Random(42); // Seed for reproducibility
    for (int i = 0; i < 50; i++) {
      String ticketId = "TKT" + String.format("%04d", i + 1);
      String event = events[random.nextInt(events.length)];
      double price = 25 + (random.nextDouble() * 175); // $25 to $200
      String category = categories[random.nextInt(categories.length)];

      manager.addTicket(new Ticket(ticketId, event, price, category));
    }

    System.out.println("✓ Added 50 tickets with random prices");

    manager.displayAllTickets();
    manager.displayStatistics();

    // PHASE 2: Get top cheapest tickets
    System.out.println("--- PHASE 2: Finding Top 5 Cheapest Tickets ---");
    List<Ticket> cheapest = manager.getTopCheapest(5);
    manager.displayTickets(cheapest, "TOP 5 CHEAPEST TICKETS");

    // PHASE 3: Get top expensive tickets
    System.out.println("--- PHASE 3: Finding Top 5 Most Expensive Tickets ---");
    List<Ticket> expensive = manager.getTopExpensive(5);
    manager.displayTickets(expensive, "TOP 5 MOST EXPENSIVE TICKETS");

    // PHASE 4: Get tickets in price range
    System.out.println("--- PHASE 4: Finding Tickets in Price Range $50-$100 ---");
    List<Ticket> inRange = manager.getTicketsInPriceRange(50, 100);
    manager.displayTickets(inRange, "TICKETS IN RANGE $50-$100");

    // PHASE 5: Book some tickets
    System.out.println("--- PHASE 5: Booking Tickets ---");
    manager.bookTicket("TKT0001");
    manager.bookTicket("TKT0005");
    manager.bookTicket("TKT0010");
    manager.bookTicket("TKT0020");

    // PHASE 6: Display tickets by category
    System.out.println("\n--- PHASE 6: Tickets by Category ---");
    List<Ticket> vipTickets = manager.getTicketsByCategory("VIP");
    manager.displayTickets(vipTickets, "VIP TICKETS");

    // PHASE 7: Final statistics
    System.out.println("--- PHASE 7: Updated Statistics ---");
    manager.displayStatistics();

    System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                      SIMULATION COMPLETED                            ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
  }
}
