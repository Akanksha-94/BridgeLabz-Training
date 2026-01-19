package address_book.controller;

import java.util.Scanner;
import address_book.services.AddressBookService;

public class AddressBookController {
  public static void main(String[] args) {
    AddressBookService addressBookService = new AddressBookService();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int choice = getUserChoice(scanner);
      switch (choice) {
        case 1:
          handleCreateUser(scanner, addressBookService);
          break;
        case 2:
          handleEditUser(scanner, addressBookService);
          break;
        case 3:
          handleDeleteUser(scanner, addressBookService);
          break;
        case 4:
          return;
        default:
          System.out.println("Invalid user Choice!! ");
          break;
      }
    }
  }

  static int getUserChoice(Scanner scanner) {
    System.out.println("1. Add new Contact");
    System.out.println("2. Edit Contact");
    System.out.println("3. Delete Contact");
    System.out.println("4. Exit");
    System.out.println("Enter your choice: ");
    int choice=scanner.nextInt();
    scanner.nextLine();
    return choice;
  }

  static void handleCreateUser(Scanner scanner, AddressBookService addressBookService) {

    System.out.println("Enter details to add new contact");
    System.out.println("Enter first name: ");
    String firstName = scanner.nextLine();
    System.out.println("Enter last name: ");
    String lastName = scanner.nextLine();
    System.out.println("Enter address: ");
    String address = scanner.nextLine();
    System.out.println("Enter city: ");
    String city = scanner.nextLine();
    System.out.println("Enter state: ");
    String state = scanner.nextLine();
    System.out.println("Enter zip: ");
    String zip = scanner.nextLine();
    System.out.println("Enter phone number: ");
    String phoneNumber = scanner.nextLine();
    System.out.println("Enter email: ");
    String email = scanner.nextLine();

    addressBookService.createContact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    System.out.println("\nContact Details:");
    addressBookService.displayContact();
  }

  static void handleEditUser(Scanner scanner, AddressBookService addressBookService) {
    System.out.println("Enter first name: ");
    String firstName = scanner.next();
    System.out.println("Enter Updated details: ");
    String newFirstName = scanner.nextLine();
    System.out.print("Enter last name: ");
    String newLastName = scanner.nextLine();
    System.out.print("Enter address: ");
    String address = scanner.nextLine();
    System.out.print("Enter city: ");
    String city = scanner.nextLine();
    System.out.print("Enter state: ");
    String state = scanner.nextLine();
    System.out.print("Enter zip: ");
    String zip = scanner.nextLine();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.nextLine();
    System.out.print("Enter email: ");
    String email = scanner.nextLine();

    addressBookService.editContactByName(firstName, newFirstName, newLastName, address, city, state, zip, phoneNumber,
        email);
  }
  static void handleDeleteUser(Scanner scanner, AddressBookService addressBookService){
    System.out.println("Enter first name: ");
    String firstName=scanner.nextLine();
    addressBookService.deleteContactByName(firstName);
  }
}