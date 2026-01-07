import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ==================== CUSTOM EXCEPTIONS ====================
class NoDriverAvailableException extends Exception {
  public NoDriverAvailableException(String message) {
    super(message);
  }

  public NoDriverAvailableException(String message, Throwable cause) {
    super(message, cause);
  }
}

class InsufficientWalletException extends Exception {
  public InsufficientWalletException(String message) {
    super(message);
  }
}

// ==================== USER CLASS ====================
class User {
  private String userId;
  private String name;
  private String email;
  private String phone;
  private double wallet;

  public User(String userId, String name, String email, String phone, double initialWallet) {
    this.userId = userId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.wallet = initialWallet;
  }

  public String getUserId() {
    return userId;
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

  public double getWallet() {
    return wallet;
  }

  public void addToWallet(double amount) {
    this.wallet += amount;
  }

  public void deductFromWallet(double amount) throws InsufficientWalletException {
    if (this.wallet < amount) {
      throw new InsufficientWalletException("Insufficient wallet balance! Available: Rs. " + wallet);
    }
    this.wallet -= amount;
  }

  @Override
  public String toString() {
    return String.format("User{id='%s', name='%s', email='%s', phone='%s', wallet=Rs.%.2f}",
        userId, name, email, phone, wallet);
  }
}

// ==================== DRIVER CLASS ====================
class Driver {
  private String driverId;
  private String name;
  private String email;
  private String phone;
  private String vehicleNumber;
  private String vehicleType;
  private boolean isAvailable;
  private double rating;
  private int totalRides;

  public Driver(String driverId, String name, String email, String phone,
      String vehicleNumber, String vehicleType) {
    this.driverId = driverId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.vehicleNumber = vehicleNumber;
    this.vehicleType = vehicleType;
    this.isAvailable = true;
    this.rating = 5.0;
    this.totalRides = 0;
  }

  public String getDriverId() {
    return driverId;
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

  public String getVehicleNumber() {
    return vehicleNumber;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public double getRating() {
    return rating;
  }

  public int getTotalRides() {
    return totalRides;
  }

  public void updateRating(double newRating) {
    this.rating = (this.rating * this.totalRides + newRating) / (this.totalRides + 1);
    this.totalRides++;
  }

  @Override
  public String toString() {
    return String.format("Driver{id='%s', name='%s', vehicle='%s (%s)', available=%s, rating=%.2f/5.0, rides=%d}",
        driverId, name, vehicleNumber, vehicleType, isAvailable, rating, totalRides);
  }
}

// ==================== FARE CALCULATOR INTERFACE (Abstraction)
// ====================
interface FareCalculator {
  double calculateFare(double distance, double baseFarePerKm);
}

// ==================== NORMAL HOUR FARE CALCULATOR (Polymorphism)
// ====================
class NormalHourFareCalculator implements FareCalculator {
  private static final double MULTIPLIER = 1.0;

  @Override
  public double calculateFare(double distance, double baseFarePerKm) {
    return distance * baseFarePerKm * MULTIPLIER;
  }
}

// ==================== PEAK HOUR FARE CALCULATOR (Polymorphism)
// ====================
class PeakHourFareCalculator implements FareCalculator {
  private static final double MULTIPLIER = 1.5; // 50% surge pricing

  @Override
  public double calculateFare(double distance, double baseFarePerKm) {
    return distance * baseFarePerKm * MULTIPLIER;
  }
}

// ==================== RIDE CLASS ====================
class Ride {
  private String rideId;
  private User user;
  private Driver driver;
  private String pickupLocation;
  private String dropoffLocation;
  private double distance;
  private double fare;
  private RideStatus status;
  private LocalDateTime bookingTime;
  private LocalDateTime completionTime;
  private double userRating;
  private boolean isPeakHour;

  enum RideStatus {
    BOOKED, ASSIGNED, IN_PROGRESS, COMPLETED, CANCELLED
  }

  public Ride(String rideId, User user, String pickupLocation, String dropoffLocation,
      double distance, boolean isPeakHour) {
    this.rideId = rideId;
    this.user = user;
    this.pickupLocation = pickupLocation;
    this.dropoffLocation = dropoffLocation;
    this.distance = distance;
    this.isPeakHour = isPeakHour;
    this.status = RideStatus.BOOKED;
    this.bookingTime = LocalDateTime.now();
    this.userRating = 0.0;
  }

  public String getRideId() {
    return rideId;
  }

  public User getUser() {
    return user;
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public String getPickupLocation() {
    return pickupLocation;
  }

  public String getDropoffLocation() {
    return dropoffLocation;
  }

  public double getDistance() {
    return distance;
  }

  public double getFare() {
    return fare;
  }

  public void setFare(double fare) {
    this.fare = fare;
  }

  public RideStatus getStatus() {
    return status;
  }

  public void setStatus(RideStatus status) {
    this.status = status;
  }

  public LocalDateTime getBookingTime() {
    return bookingTime;
  }

  public LocalDateTime getCompletionTime() {
    return completionTime;
  }

  public void setCompletionTime(LocalDateTime completionTime) {
    this.completionTime = completionTime;
  }

  public double getUserRating() {
    return userRating;
  }

  public void setUserRating(double userRating) {
    this.userRating = userRating;
  }

  public boolean isPeakHour() {
    return isPeakHour;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return String.format(
        "Ride{id='%s', user='%s', driver='%s', %s → %s, distance=%.1fkm, fare=Rs.%.2f, status=%s, rating=%.1f}",
        rideId, user.getName(), driver != null ? driver.getName() : "Unassigned",
        pickupLocation, dropoffLocation, distance, fare, status, userRating);
  }
}

// ==================== RIDE MANAGEMENT SYSTEM (MAIN) ====================
public class CabBookingManagementSystem {
  private Map<String, User> users;
  private Map<String, Driver> drivers;
  private Map<String, Ride> rides;
  private Map<String, List<Ride>> userRideHistory;
  private double baseFarePerKm;
  private int rideCounter;

  public CabBookingManagementSystem() {
    this.users = new HashMap<>();
    this.drivers = new HashMap<>();
    this.rides = new HashMap<>();
    this.userRideHistory = new HashMap<>();
    this.baseFarePerKm = 10.0;
    this.rideCounter = 1;
  }

  // ==================== USER CRUD OPERATIONS ====================

  public void registerUser(String userId, String name, String email, String phone, double initialWallet) {
    User user = new User(userId, name, email, phone, initialWallet);
    users.put(userId, user);
    userRideHistory.put(userId, new ArrayList<>());
    System.out.println("✓ User registered: " + user.getName());
  }

  public User getUser(String userId) {
    return users.get(userId);
  }

  public void updateUserWallet(String userId, double amount) throws NoDriverAvailableException {
    User user = users.get(userId);
    if (user == null) {
      throw new NoDriverAvailableException("User not found: " + userId);
    }
    user.addToWallet(amount);
    System.out.println(
        "✓ Wallet updated for " + user.getName() + ". New balance: Rs. " + String.format("%.2f", user.getWallet()));
  }

  public void deleteUser(String userId) {
    users.remove(userId);
    userRideHistory.remove(userId);
    System.out.println("✓ User deleted: " + userId);
  }

  public void displayAllUsers() {
    System.out.println("\n========== REGISTERED USERS ==========");
    if (users.isEmpty()) {
      System.out.println("No users registered yet.");
      return;
    }
    users.forEach((id, user) -> System.out.println(user));
  }

  // ==================== DRIVER CRUD OPERATIONS ====================

  public void registerDriver(String driverId, String name, String email, String phone,
      String vehicleNumber, String vehicleType) {
    Driver driver = new Driver(driverId, name, email, phone, vehicleNumber, vehicleType);
    drivers.put(driverId, driver);
    System.out.println("✓ Driver registered: " + driver.getName());
  }

  public Driver getDriver(String driverId) {
    return drivers.get(driverId);
  }

  public void updateDriverAvailability(String driverId, boolean isAvailable) throws NoDriverAvailableException {
    Driver driver = drivers.get(driverId);
    if (driver == null) {
      throw new NoDriverAvailableException("Driver not found: " + driverId);
    }
    driver.setAvailable(isAvailable);
    System.out.println("✓ Driver " + driver.getName() + " is now " + (isAvailable ? "AVAILABLE" : "UNAVAILABLE"));
  }

  public void deleteDriver(String driverId) {
    drivers.remove(driverId);
    System.out.println("✓ Driver deleted: " + driverId);
  }

  public void displayAllDrivers() {
    System.out.println("\n========== REGISTERED DRIVERS ==========");
    if (drivers.isEmpty()) {
      System.out.println("No drivers registered yet.");
      return;
    }
    drivers.forEach((id, driver) -> System.out.println(driver));
  }

  // ==================== RIDE BOOKING & MANAGEMENT ====================

  public String bookRide(String userId, String pickupLocation, String dropoffLocation,
      double distance, boolean isPeakHour) throws NoDriverAvailableException, InsufficientWalletException {
    User user = users.get(userId);
    if (user == null) {
      throw new NoDriverAvailableException("User not found: " + userId);
    }

    Driver assignedDriver = findAvailableDriver();
    if (assignedDriver == null) {
      throw new NoDriverAvailableException("No drivers available at the moment. Please try again later.");
    }

    String rideId = "RIDE-" + String.format("%05d", rideCounter++);
    Ride ride = new Ride(rideId, user, pickupLocation, dropoffLocation, distance, isPeakHour);

    FareCalculator fareCalculator = isPeakHour ? new PeakHourFareCalculator() : new NormalHourFareCalculator();
    double fare = fareCalculator.calculateFare(distance, baseFarePerKm);
    ride.setFare(fare);

    ride.setDriver(assignedDriver);
    ride.setStatus(Ride.RideStatus.ASSIGNED);
    assignedDriver.setAvailable(false);

    try {
      user.deductFromWallet(fare);
    } catch (InsufficientWalletException e) {
      assignedDriver.setAvailable(true);
      throw new NoDriverAvailableException("Booking failed: " + e.getMessage());
    }

    rides.put(rideId, ride);
    userRideHistory.get(userId).add(ride);

    System.out.println("\n✓ RIDE BOOKED SUCCESSFULLY!");
    System.out.println("  Ride ID: " + rideId);
    System.out.println("  Driver: " + assignedDriver.getName() + " (" + assignedDriver.getVehicleNumber() + ")");
    System.out.println("  Fare: Rs. " + String.format("%.2f", fare) + (isPeakHour ? " (Peak Hour 1.5x)" : ""));
    System.out.println("  Distance: " + distance + " km | " + pickupLocation + " → " + dropoffLocation);

    return rideId;
  }

  public void startRide(String rideId) throws NoDriverAvailableException {
    Ride ride = rides.get(rideId);
    if (ride == null) {
      throw new NoDriverAvailableException("Ride not found: " + rideId);
    }
    ride.setStatus(Ride.RideStatus.IN_PROGRESS);
    System.out.println("✓ Ride " + rideId + " has started! Driver: " + ride.getDriver().getName());
  }

  public void completeRide(String rideId, double userRating) throws NoDriverAvailableException {
    Ride ride = rides.get(rideId);
    if (ride == null) {
      throw new NoDriverAvailableException("Ride not found: " + rideId);
    }

    ride.setStatus(Ride.RideStatus.COMPLETED);
    ride.setCompletionTime(LocalDateTime.now());
    ride.setUserRating(userRating);

    Driver driver = ride.getDriver();
    driver.updateRating(userRating);
    driver.setAvailable(true);

    System.out.println("✓ Ride " + rideId + " completed! User rating: " + userRating
        + "/5.0 | Driver rating updated to: " + String.format("%.2f", driver.getRating()));
  }

  public void cancelRide(String rideId) throws NoDriverAvailableException {
    Ride ride = rides.get(rideId);
    if (ride == null) {
      throw new NoDriverAvailableException("Ride not found: " + rideId);
    }

    if (ride.getStatus() != Ride.RideStatus.BOOKED && ride.getStatus() != Ride.RideStatus.ASSIGNED) {
      throw new NoDriverAvailableException("Cannot cancel ride in " + ride.getStatus() + " status");
    }

    ride.setStatus(Ride.RideStatus.CANCELLED);
    ride.getDriver().setAvailable(true);

    User user = ride.getUser();
    user.addToWallet(ride.getFare());

    System.out.println("✓ Ride " + rideId + " cancelled! Refund: Rs. " + String.format("%.2f", ride.getFare()) + " to "
        + user.getName());
  }

  public void deleteRide(String rideId) {
    rides.remove(rideId);
    System.out.println("✓ Ride deleted: " + rideId);
  }

  // ==================== RIDE HISTORY ====================

  public void displayRideHistory(String userId) {
    User user = users.get(userId);
    if (user == null) {
      System.out.println("✗ User not found: " + userId);
      return;
    }

    List<Ride> history = userRideHistory.get(userId);
    System.out.println("\n========== RIDE HISTORY FOR " + user.getName().toUpperCase() + " ==========");

    if (history.isEmpty()) {
      System.out.println("No rides found.");
      return;
    }

    history.forEach(ride -> System.out.println(ride));
  }

  public void displayAllRides() {
    System.out.println("\n========== ALL RIDES ==========");
    if (rides.isEmpty()) {
      System.out.println("No rides found.");
      return;
    }
    rides.forEach((id, ride) -> System.out.println(ride));
  }

  // ==================== HELPER METHODS ====================

  private Driver findAvailableDriver() {
    for (Driver driver : drivers.values()) {
      if (driver.isAvailable()) {
        return driver;
      }
    }
    return null;
  }

  public void displaySystemStatistics() {
    System.out.println("\n========== SYSTEM STATISTICS ==========");
    System.out.println("Total Users: " + users.size());
    System.out.println("Total Drivers: " + drivers.size());
    System.out.println("Total Rides: " + rides.size());
    long completedRides = rides.values().stream()
        .filter(r -> r.getStatus() == Ride.RideStatus.COMPLETED)
        .count();
    System.out.println("Completed Rides: " + completedRides);
    double totalRevenue = rides.values().stream()
        .filter(r -> r.getStatus() == Ride.RideStatus.COMPLETED)
        .mapToDouble(Ride::getFare)
        .sum();
    System.out.println("Total Revenue: Rs. " + String.format("%.2f", totalRevenue));
    double avgRating = drivers.values().stream()
        .mapToDouble(Driver::getRating)
        .average()
        .orElse(0.0);
    System.out.println("Average Driver Rating: " + String.format("%.2f", avgRating) + "/5.0");
  }

  // ==================== MAIN METHOD - DEMONSTRATION ====================

  public static void main(String[] args) {
    System.out.println("╔══════════════════════════════════════════════════════════════╗");
    System.out.println("║   CAB BOOKING / RIDE MANAGEMENT SYSTEM - DEMONSTRATION      ║");
    System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

    CabBookingManagementSystem system = new CabBookingManagementSystem();

    try {
      // ==================== REGISTRATION ====================
      System.out.println(">>> REGISTERING USERS AND DRIVERS...\n");
      system.registerUser("USR001", "Rajesh Kumar", "rajesh@email.com", "9876543210", 5000.0);
      system.registerUser("USR002", "Priya Singh", "priya@email.com", "9876543211", 3000.0);
      system.registerUser("USR003", "Amit Patel", "amit@email.com", "9876543212", 7000.0);

      system.registerDriver("DRV001", "Suresh Sharma", "suresh@email.com", "9876543220",
          "MH12AB1234", "Sedan");
      system.registerDriver("DRV002", "Vikram Singh", "vikram@email.com", "9876543221",
          "MH12AB1235", "SUV");
      system.registerDriver("DRV003", "Ramesh Kumar", "ramesh@email.com", "9876543222",
          "MH12AB1236", "Hatchback");

      system.displayAllUsers();
      system.displayAllDrivers();

      // ==================== BOOKING RIDES ====================
      System.out.println("\n>>> BOOKING RIDES (Normal & Peak Hour)...");
      String ride1 = system.bookRide("USR001", "Dadar", "Andheri", 15.0, false);
      String ride2 = system.bookRide("USR002", "Bandra", "Colaba", 20.0, true);
      String ride3 = system.bookRide("USR003", "Powai", "Borivali", 25.0, false);

      // ==================== RIDE OPERATIONS ====================
      System.out.println("\n>>> STARTING AND COMPLETING RIDES...\n");
      system.startRide(ride1);
      system.completeRide(ride1, 4.5);

      system.startRide(ride2);
      system.completeRide(ride2, 5.0);

      system.startRide(ride3);
      system.completeRide(ride3, 4.0);

      // ==================== DISPLAY HISTORY ====================
      system.displayRideHistory("USR001");
      system.displayRideHistory("USR002");
      system.displayAllRides();

      // ==================== STATISTICS ====================
      system.displaySystemStatistics();

      // ==================== UPDATE WALLET ====================
      System.out.println("\n>>> UPDATING USER WALLET...\n");
      system.updateUserWallet("USR001", 2000.0);

      // ==================== CANCEL RIDE ====================
      System.out.println("\n>>> BOOKING AND CANCELING A RIDE...\n");
      String ride4 = system.bookRide("USR001", "Thane", "Mumbai Central", 30.0, false);
      system.cancelRide(ride4);

      // ==================== EXCEPTION HANDLING: NO DRIVER AVAILABLE
      // ====================
      System.out.println("\n>>> TESTING EXCEPTION: NO DRIVER AVAILABLE...\n");
      system.updateDriverAvailability("DRV001", false);
      system.updateDriverAvailability("DRV002", false);
      system.updateDriverAvailability("DRV003", false);

      try {
        system.bookRide("USR002", "Vile Parle", "Mahim", 10.0, false);
      } catch (NoDriverAvailableException e) {
        System.out.println("✓ Exception caught: " + e.getMessage());
      }

      // ==================== EXCEPTION HANDLING: INSUFFICIENT WALLET
      // ====================
      System.out.println("\n>>> TESTING EXCEPTION: INSUFFICIENT WALLET BALANCE...\n");
      system.updateDriverAvailability("DRV001", true);
      try {
        system.bookRide("USR002", "Airport", "Downtown", 100.0, true);
      } catch (InsufficientWalletException e) {
        System.out.println("✓ Exception caught: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("✓ Exception caught: " + e.getMessage());
      }

    } catch (Exception e) {
      System.out.println("✗ Error: " + e.getMessage());
      e.printStackTrace();
    }

     System.out.println("              DEMONSTRATION COMPLETED SUCCESSFULLY             ");
  }
}
