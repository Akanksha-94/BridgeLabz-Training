import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Custom Exception
class TableAlreadyReservedException extends Exception {
    public TableAlreadyReservedException(String message) {
        super(message);
    }
}

// Table class
class Table {
    private int tableNumber;
    private int capacity;
    private boolean isReserved;
    private LocalDateTime reservedUntil;

    public Table(int tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.isReserved = false;
        this.reservedUntil = null;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isReserved() {
        return isReserved && reservedUntil.isAfter(LocalDateTime.now());
    }

    public void setReserved(LocalDateTime until) {
        this.isReserved = true;
        this.reservedUntil = until;
    }

    public void cancelReservation() {
        this.isReserved = false;
        this.reservedUntil = null;
    }

    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }

    @Override
    public String toString() {
        return "Table{" +
                "Number=" + tableNumber +
                ", Capacity=" + capacity +
                ", Reserved=" + isReserved() +
                (isReserved() ? ", Until=" + reservedUntil.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "") +
                "}";
    }
}

// Reservation class
class Reservation {
    private int reservationId;
    private int tableNumber;
    private String customerName;
    private LocalDateTime reservationTime;
    private LocalDateTime endTime;
    private int partySize;

    private static int idCounter = 1000;

    public Reservation(int tableNumber, String customerName, LocalDateTime reservationTime, LocalDateTime endTime, int partySize) {
        this.reservationId = idCounter++;
        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.reservationTime = reservationTime;
        this.endTime = endTime;
        this.partySize = partySize;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getPartySize() {
        return partySize;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Reservation{" +
                "ID=" + reservationId +
                ", Table=" + tableNumber +
                ", Customer='" + customerName + '\'' +
                ", From=" + reservationTime.format(formatter) +
                ", To=" + endTime.format(formatter) +
                ", PartySize=" + partySize +
                "}";
    }
}

// Restaurant Reservation System
public class RestaurantTableReservationSystem {
    private Map<Integer, Table> tables;
    private List<Reservation> reservations;

    public RestaurantTableReservationSystem(int numberOfTables, int tableCapacity) {
        this.tables = new HashMap<>();
        this.reservations = new ArrayList<>();

        // Initialize tables
        for (int i = 1; i <= numberOfTables; i++) {
            tables.put(i, new Table(i, tableCapacity));
        }
    }

    public void reserveTable(int tableNumber, String customerName, LocalDateTime from, LocalDateTime to, int partySize) 
            throws TableAlreadyReservedException {
        
        // Validate table existence
        if (!tables.containsKey(tableNumber)) {
            throw new IllegalArgumentException("Table " + tableNumber + " does not exist");
        }

        Table table = tables.get(tableNumber);

        // Check if table capacity is sufficient
        if (table.getCapacity() < partySize) {
            throw new IllegalArgumentException("Table capacity (" + table.getCapacity() + ") is less than party size (" + partySize + ")");
        }

        // Check if table is already reserved for the given time slot
        if (isTableReservedInTimeSlot(tableNumber, from, to)) {
            throw new TableAlreadyReservedException("Table " + tableNumber + " is already reserved for the requested time slot");
        }

        // Reserve the table
        table.setReserved(to);
        Reservation reservation = new Reservation(tableNumber, customerName, from, to, partySize);
        reservations.add(reservation);

        System.out.println("✓ Table reserved successfully!");
        System.out.println(reservation);
    }

    private boolean isTableReservedInTimeSlot(int tableNumber, LocalDateTime from, LocalDateTime to) {
        for (Reservation res : reservations) {
            if (res.getTableNumber() == tableNumber) {
                // Check for time overlap
                if (!(to.isBefore(res.getReservationTime()) || from.isAfter(res.getEndTime()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void cancelReservation(int reservationId) {
        boolean found = false;
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                int tableNumber = res.getTableNumber();
                tables.get(tableNumber).cancelReservation();
                reservations.remove(res);
                found = true;
                System.out.println("✓ Reservation " + reservationId + " cancelled successfully");
                break;
            }
        }
        if (!found) {
            System.out.println("✗ Reservation " + reservationId + " not found");
        }
    }

    public void showAvailableTables(LocalDateTime from, LocalDateTime to) {
        System.out.println("\n--- Available Tables from " + from + " to " + to + " ---");
        boolean hasAvailable = false;

        for (Integer tableNum : tables.keySet()) {
            if (!isTableReservedInTimeSlot(tableNum, from, to)) {
                System.out.println("✓ " + tables.get(tableNum));
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {
            System.out.println("✗ No tables available for the requested time slot");
        }
    }

    public void showAllReservations() {
        System.out.println("\n--- All Reservations ---");
        if (reservations.isEmpty()) {
            System.out.println("No reservations");
            return;
        }
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }

    public void showTableStatus() {
        System.out.println("\n--- Table Status ---");
        for (Integer tableNum : tables.keySet()) {
            System.out.println(tables.get(tableNum));
        }
    }

    // Main method
    public static void main(String[] args) {
        RestaurantTableReservationSystem restaurant = new RestaurantTableReservationSystem(5, 4);

        try {
            System.out.println("=== Restaurant Table Reservation System ===\n");

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime later2Hours = now.plusHours(2);
            LocalDateTime later3Hours = now.plusHours(3);
            LocalDateTime later5Hours = now.plusHours(5);

            // Show available tables
            restaurant.showAvailableTables(now, later2Hours);

            // Reserve table 1
            System.out.println("\n--- Attempting to reserve Table 1 ---");
            restaurant.reserveTable(1, "John Doe", now, later2Hours, 3);

            // Try to reserve same table in overlapping time (should fail)
            System.out.println("\n--- Attempting to reserve Table 1 again (overlap) ---");
            try {
                restaurant.reserveTable(1, "Jane Smith", now.plusMinutes(30), later3Hours, 2);
            } catch (TableAlreadyReservedException e) {
                System.out.println("✗ Exception caught: " + e.getMessage());
            }

            // Reserve different table
            System.out.println("\n--- Attempting to reserve Table 2 ---");
            restaurant.reserveTable(2, "Jane Smith", now, later2Hours, 4);

            // Show available tables
            restaurant.showAvailableTables(now, later2Hours);

            // Show all reservations
            restaurant.showAllReservations();

            // Show table status
            restaurant.showTableStatus();

            // Cancel a reservation
            System.out.println("\n--- Cancelling Reservation 1000 ---");
            restaurant.cancelReservation(1000);

            // Show available tables after cancellation
            restaurant.showAvailableTables(now, later2Hours);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
