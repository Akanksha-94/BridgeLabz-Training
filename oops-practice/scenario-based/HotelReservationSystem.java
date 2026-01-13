import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Hotel Reservation System
 * Real Scenario: Hotels manage room bookings, check-ins, check-outs, and billing.
 * 
 * Concepts Used:
 * - OOP: Room, Guest, Reservation
 * - Inheritance: Room → DeluxeRoom / StandardRoom
 * - Interface: PricingStrategy
 * - Polymorphism: Seasonal pricing
 * - Exception Handling: RoomNotAvailableException
 */

// Custom Exception
class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}

// Guest class
class Guest {
  private String guestId;
  private String name;
  private String email;
  private String phone;
  private String address;

  public Guest(String guestId, String name, String email, String phone, String address) {
    this.guestId = guestId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
  }

  public String getGuestId() {
    return guestId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  @Override
  public String toString() {
    return String.format("Guest ID: %s | Name: %s | Email: %s | Phone: %s",
        guestId, name, email, phone);
  }
}

// Pricing Strategy Interface
interface PricingStrategy {
  double calculatePrice(double basePrice, int nights);

  String getStrategyName();
}

// Regular Season Pricing
class RegularSeasonPricing implements PricingStrategy {
  @Override
  public double calculatePrice(double basePrice, int nights) {
    return basePrice * nights;
  }

  @Override
  public String getStrategyName() {
    return "Regular Season";
  }
}

// Peak Season Pricing
class PeakSeasonPricing implements PricingStrategy {
  private double peakMultiplier = 1.5;

  @Override
  public double calculatePrice(double basePrice, int nights) {
    return basePrice * peakMultiplier * nights;
  }

  @Override
  public String getStrategyName() {
    return "Peak Season (1.5x)";
  }
}

// Discount Season Pricing
class DiscountSeasonPricing implements PricingStrategy {
  private double discountMultiplier = 0.7;

  @Override
  public double calculatePrice(double basePrice, int nights) {
    return basePrice * discountMultiplier * nights;
  }

  @Override
  public String getStrategyName() {
    return "Discount Season (0.7x)";
  }
}

// Room base class
abstract class Room {
  protected String roomNumber;
  protected String roomType;
  protected double basePrice;
  protected boolean isAvailable;
  protected int capacity;

  public Room(String roomNumber, String roomType, double basePrice, int capacity) {
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    this.basePrice = basePrice;
    this.isAvailable = true;
    this.capacity = capacity;
  }

  public String getRoomNumber() {
    return roomNumber;
  }

  public String getRoomType() {
    return roomType;
  }

  public double getBasePrice() {
    return basePrice;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setAvailable(boolean available) {
    this.isAvailable = available;
  }

  abstract double calculatePrice(int nights, PricingStrategy strategy);

  abstract String getDescription();

  @Override
  public String toString() {
    return String.format("Room %s [%s] - $%.2f/night | Capacity: %d | Available: %s",
        roomNumber, roomType, basePrice, capacity, (isAvailable ? "Yes" : "No"));
  }
}

// Standard Room
class StandardRoom extends Room {
  public StandardRoom(String roomNumber, double basePrice) {
    super(roomNumber, "Standard", basePrice, 2);
  }

  @Override
  double calculatePrice(int nights, PricingStrategy strategy) {
    return strategy.calculatePrice(basePrice, nights);
  }

  @Override
  String getDescription() {
    return "Standard Room: Basic amenities, 2 beds";
  }
}

// Deluxe Room
class DeluxeRoom extends Room {
  public DeluxeRoom(String roomNumber, double basePrice) {
    super(roomNumber, "Deluxe", basePrice, 4);
  }

  @Override
  double calculatePrice(int nights, PricingStrategy strategy) {
    return strategy.calculatePrice(basePrice, nights);
  }

  @Override
  String getDescription() {
    return "Deluxe Room: Premium amenities, King bed, Jacuzzi";
  }
}

// Reservation
class Reservation {
  private String reservationId;
  private Guest guest;
  private Room room;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private String status; // CONFIRMED, CHECKED_IN, CHECKED_OUT, CANCELLED
  private double totalCost;

  public Reservation(String reservationId, Guest guest, Room room,
      LocalDate checkInDate, LocalDate checkOutDate, double totalCost) {
    this.reservationId = reservationId;
    this.guest = guest;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.status = "CONFIRMED";
    this.totalCost = totalCost;
  }

  public String getReservationId() {
    return reservationId;
  }

  public Guest getGuest() {
    return guest;
  }

  public Room getRoom() {
    return room;
  }

  public String getStatus() {
    return status;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getNights() {
    return java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
  }

  @Override
  public String toString() {
    return String.format("%-15s | Guest: %-20s | Room: %-8s | Check-in: %s | Check-out: %s | Status: %s | Cost: $%.2f",
        reservationId, guest.getName(), room.getRoomNumber(),
        checkInDate, checkOutDate, status, totalCost);
  }
}

public class HotelReservationSystem {
  private Map<String, Room> rooms;
  private Map<String, Guest> guests;
  private Map<String, Reservation> reservations;
  private int reservationCounter;

  public HotelReservationSystem() {
    this.rooms = new HashMap<>();
    this.guests = new HashMap<>();
    this.reservations = new HashMap<>();
    this.reservationCounter = 0;
  }

  public void addRoom(Room room) {
    rooms.put(room.getRoomNumber(), room);
    System.out.println("✓ Room added: " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
  }

  public void registerGuest(Guest guest) {
    guests.put(guest.getGuestId(), guest);
    System.out.println("✓ Guest registered: " + guest.getName());
  }

  public Reservation makeReservation(String guestId, String roomNumber,
      LocalDate checkInDate, LocalDate checkOutDate,
      PricingStrategy pricingStrategy) throws RoomNotAvailableException {
    Guest guest = guests.get(guestId);
    Room room = rooms.get(roomNumber);

    if (guest == null) {
      throw new RoomNotAvailableException("Guest not found");
    }
    if (room == null) {
      throw new RoomNotAvailableException("Room not found");
    }
    if (!room.isAvailable()) {
      throw new RoomNotAvailableException("Room is not available");
    }

    long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    double totalCost = room.calculatePrice((int) nights, pricingStrategy);

    reservationCounter++;
    String reservationId = "RES" + String.format("%05d", reservationCounter);
    Reservation reservation = new Reservation(reservationId, guest, room,
        checkInDate, checkOutDate, totalCost);

    room.setAvailable(false);
    reservations.put(reservationId, reservation);

    System.out.println("✓ Reservation confirmed: " + reservationId + " | Cost: $" +
        String.format("%.2f", totalCost));
    return reservation;
  }

  public void checkIn(String reservationId) {
    Reservation reservation = reservations.get(reservationId);
    if (reservation != null) {
      reservation.setStatus("CHECKED_IN");
      System.out.println("✓ Check-in completed for " + reservationId);
    }
  }

  public void checkOut(String reservationId) {
    Reservation reservation = reservations.get(reservationId);
    if (reservation != null) {
      reservation.setStatus("CHECKED_OUT");
      Room room = reservation.getRoom();
      room.setAvailable(true);
      System.out.println("✓ Check-out completed for " + reservationId);
    }
  }

  public void cancelReservation(String reservationId) {
    Reservation reservation = reservations.get(reservationId);
    if (reservation != null) {
      reservation.setStatus("CANCELLED");
      Room room = reservation.getRoom();
      room.setAvailable(true);
      System.out.println("✓ Reservation cancelled: " + reservationId);
    }
  }

  public void displayAvailableRooms() {
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    AVAILABLE ROOMS                                ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Room room : rooms.values()) {
      if (room.isAvailable()) {
        System.out.println(room);
      }
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public void displayAllRooms() {
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                      ALL ROOMS                                    ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Room room : rooms.values()) {
      System.out.println(room);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public void displayReservations() {
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    ALL RESERVATIONS                               ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Reservation res : reservations.values()) {
      System.out.println(res);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public static void main(String[] args) {
    HotelReservationSystem hotel = new HotelReservationSystem();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║       HOTEL RESERVATION SYSTEM - DEMONSTRATION                   ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Add rooms
    System.out.println("--- PHASE 1: Add Rooms ---");
    hotel.addRoom(new StandardRoom("101", 100.0));
    hotel.addRoom(new StandardRoom("102", 100.0));
    hotel.addRoom(new DeluxeRoom("201", 200.0));
    hotel.addRoom(new DeluxeRoom("202", 200.0));

    hotel.displayAllRooms();

    // PHASE 2: Register guests
    System.out.println("--- PHASE 2: Register Guests ---");
    Guest guest1 = new Guest("G001", "John Doe", "john@email.com", "555-1001", "123 Main St");
    Guest guest2 = new Guest("G002", "Jane Smith", "jane@email.com", "555-1002", "456 Oak Ave");

    hotel.registerGuest(guest1);
    hotel.registerGuest(guest2);

    // PHASE 3: Make reservations with different pricing strategies
    System.out.println("\n--- PHASE 3: Make Reservations ---");
    try {
      hotel.makeReservation("G001", "101", LocalDate.now().plusDays(1),
          LocalDate.now().plusDays(4), new PeakSeasonPricing());
      hotel.makeReservation("G002", "201", LocalDate.now().plusDays(2),
          LocalDate.now().plusDays(5), new RegularSeasonPricing());
    } catch (RoomNotAvailableException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    hotel.displayAvailableRooms();

    // PHASE 4: Check-in
    System.out.println("--- PHASE 4: Guest Check-In ---");
    hotel.checkIn("RES00001");
    hotel.checkIn("RES00002");

    // PHASE 5: Display reservations
    System.out.println("--- PHASE 5: Display Reservations ---");
    hotel.displayReservations();

    // PHASE 6: Check-out
    System.out.println("--- PHASE 6: Guest Check-Out ---");
    hotel.checkOut("RES00001");

    hotel.displayAvailableRooms();

    // PHASE 7: Try to make another reservation
    System.out.println("--- PHASE 7: Make Another Reservation ---");
    try {
      hotel.makeReservation("G001", "101", LocalDate.now().plusDays(10),
          LocalDate.now().plusDays(14), new DiscountSeasonPricing());
    } catch (RoomNotAvailableException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    hotel.displayReservations();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                           ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
  }
}
