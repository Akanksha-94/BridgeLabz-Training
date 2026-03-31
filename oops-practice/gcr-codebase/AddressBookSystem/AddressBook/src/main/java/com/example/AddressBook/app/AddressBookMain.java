package com.example.AddressBook.app;

import com.example.AddressBook.model.Person;
import com.example.AddressBook.service.AddressBook;
import com.example.AddressBook.service.AddressBookSystem;
import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AddressBookSystem system=new AddressBookSystem();
        
        AddressBook currentBook=null;
        String currentBookName=null;
        
        boolean run = true;

        while (run) {

            System.out.println("\n==============================");
            System.out.println("Current AddressBook: " + 
                (currentBookName == null ? "NONE" : currentBookName));
            System.out.println("==============================");

            System.out.println("1. Create new AddressBook");
            System.out.println("2. Select AddressBook");
            System.out.println("3. Add Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Display Contacts");
            System.out.println("6. Delete Contact");
            System.out.println("7. Search By City and State");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
            
            	//UC6:Adding new AddressBook
            	case 1://Create new AddressBook
            		System.out.println("Enter the addressBook name");
            		String newName=sc.nextLine();
            		
            		//UC7:Ensure there is no Duplicate Entry.
            		if(system.searchBookName(newName)) {
            			System.out.println("AddressBook already exists");
            		}else {
            			system.addNewBook(newName, new AddressBook());
            			System.out.println("AddressBook created successfully");
            		}
            		break;
            	
            	case 2://Select addressBook
            		System.out.println("Enter the AddressBook name to be work on:");
            		String selectName=sc.nextLine();
            		
            		if(!system.searchBookName(selectName)) {
            			System.out.println("Book not found");
            		}else {
            			currentBook=system.getBook(selectName);
            			currentBookName=selectName;
            			System.out.println("Selected AddressBook: "+selectName);
            		}
            		break;


                case 3: // ADD CONTACT
                    if (currentBook == null) {
                        System.out.println("Select an AddressBook first.");
                        break;
                    }

                    System.out.print("First Name: ");
                    String fn = sc.nextLine();

                    System.out.print("Last Name: ");
                    String ln = sc.nextLine();

                    System.out.print("Address: ");
                    String address = sc.nextLine();

                    System.out.print("City: ");
                    String city = sc.nextLine();

                    System.out.print("State: ");
                    String state = sc.nextLine();

                    System.out.print("Zip: ");
                    String zip = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    Person p = new Person(fn, ln, address, city, state, zip, phone, email);
                    currentBook.addContact(p);

                    System.out.println("Contact added.");
                    break;

                case 4: // EDIT CONTACT
                    if (currentBook == null) {
                        System.out.println("Select an AddressBook first.");
                        break;
                    }

                    System.out.print("First Name to edit: ");
                    String efn = sc.nextLine();

                    System.out.print("Last Name to edit: ");
                    String eln = sc.nextLine();

                    Person editPerson = currentBook.findContactByName(efn, eln);

                    if (editPerson == null) {
                        System.out.println("Contact not found.");
                    } else {
                        System.out.print("New Address: ");
                        editPerson.setAddress(sc.nextLine());

                        System.out.print("New City: ");
                        editPerson.setCity(sc.nextLine());

                        System.out.print("New State: ");
                        editPerson.setState(sc.nextLine());

                        System.out.print("New Zip: ");
                        editPerson.setZip(sc.nextLine());

                        System.out.print("New Phone: ");
                        editPerson.setPhoneNumber(sc.nextLine());

                        System.out.print("New Email: ");
                        editPerson.setEmail(sc.nextLine());

                        System.out.println("Contact updated.");
                    }
                    break;

                case 5: // DISPLAY
                    if (currentBook == null) {
                        System.out.println("Select an AddressBook first.");
                        break;
                    }
                    currentBook.displayContacts();
                    break;

                case 6: // DELETE
                    if (currentBook == null) {
                        System.out.println("Select an AddressBook first.");
                        break;
                    }

                    System.out.print("First Name to delete: ");
                    String dfn = sc.nextLine();

                    System.out.print("Last Name to delete: ");
                    String dln = sc.nextLine();

                    boolean deleted = currentBook.deleteContactByName(dfn, dln);

                    if (deleted) {
                        System.out.println("Contact deleted.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                    
                case 7:
                	System.out.println("Enter the state");
                	String s=sc.nextLine();
                	System.out.println("Enter the city");
                	String c=sc.nextLine();
                	
                	List<Person> result=new ArrayList<>();
                	
                	result=system.searchByCity(c, s);
                	
                	if(result.isEmpty()) {
                		System.out.println("No person found with city :"+s+"and state : "+ c);
                
                	} else {
                		for(Person person:result) {
                			System.out.println("Name :"+person.getFirstName()+" "+person.getLastName());
                			System.out.println("Address :"+person.getAddress());
                			System.out.println("City :"+person.getCity());
                			System.out.println("State :"+person.getState());
                			System.out.println("Email :"+person.getEmail());
                		}
                	}
                	

                case 8:
                    run = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
