import java.util.ArrayList;
// Custom Exception
class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
// Contact class
class Contact {
    String name;
    String phoneNumber;
    Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
public class PhoneContactOrganizer {
    private static final ArrayList<Contact> contacts = new ArrayList<>();
    // Validate phone number
    public static void validatePhoneNumber(String phone) throws InvalidPhoneNumberException {
        if (!phone.matches("\\d{10}")) {
            throw new InvalidPhoneNumberException("Phone number must be exactly 10 digits.");
        }
    }
    // Add contact
    public static void addContact(String name, String phone) throws InvalidPhoneNumberException {
        validatePhoneNumber(phone);
        for (Contact c : contacts) {
            if (c.phoneNumber.equals(phone)) {
                System.out.println("Duplicate contact not allowed.");
                return;
            }
        }
        contacts.add(new Contact(name, phone));
        System.out.println("Contact added successfully.");
    }
    // Search contact
    public static void searchContact(String phone) {
        for (Contact c : contacts) {
            if (c.phoneNumber.equals(phone)) {
                System.out.println("Found: " + c.name + " - " + c.phoneNumber);
                return;
            }
        }
        System.out.println("Contact not found.");
    }
    // Delete contact
    public static void deleteContact(String phone) {
        contacts.removeIf(c -> c.phoneNumber.equals(phone));
        System.out.println("Contact deleted (if existed).");
    }
    public static void main(String[] args) {
        try {
            addContact("Amit", "9876543210");
            addContact("Neha", "9876543211");
            searchContact("9876543210");
            deleteContact("9876543211");
        } catch (InvalidPhoneNumberException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}