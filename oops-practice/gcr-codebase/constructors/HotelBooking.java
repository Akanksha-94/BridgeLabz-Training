public class HotelBooking {

    // Private attributes for encapsulation
    private String guestName;
    private String roomType;
    private int nights;


    
    // Default constructor
    public HotelBooking() {
        this.guestName = "Unknown";
        this.roomType = "Standard";
        this.nights = 1;
    }

    // Parameterized constructor
    public HotelBooking(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        if (nights > 0) {
            this.nights = nights;
        } else {
            this.nights = 1; // default to 1 night if invalid
            System.out.println("Invalid number of nights. Set to 1 by default.");
        }
    }

    // Copy constructor
    public HotelBooking(HotelBooking other) {
        this.guestName = other.guestName;
        this.roomType = other.roomType;
        this.nights = other.nights;
    }

    // Getters and setters
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        if (nights > 0) {
            this.nights = nights;
        } else {
            System.out.println("Invalid number of nights. Keeping previous value: " + this.nights);
        }
    }

    // Method to display booking info
    public void displayBooking() {
        System.out.println("Guest Name: " + guestName + ", Room Type: " + roomType + ", Nights: " + nights);
    }

    // Main method to test the class
    public static void main(String[] args) {
        // Using default constructor
        HotelBooking booking1 = new HotelBooking();
        booking1.displayBooking();

        // Using parameterized constructor
        HotelBooking booking2 = new HotelBooking("Alice", "Deluxe", 3);
        booking2.displayBooking();

        // Using copy constructor
        HotelBooking booking3 = new HotelBooking(booking2);
        booking3.displayBooking();

        // Update booking using setters
        booking3.setGuestName("Bob");
        booking3.setRoomType("Suite");
        booking3.setNights(5);
        booking3.displayBooking();
    }
}
