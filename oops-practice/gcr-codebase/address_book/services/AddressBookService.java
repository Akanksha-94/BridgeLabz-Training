package address_book.services;
import address_book.entity.Contact;
import address_book.repository.AddressBookRepository;
public class AddressBookService {
  AddressBookRepository addressBookRepository=new AddressBookRepository();
  public void createContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
    Contact contact=new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    addressBookRepository.save(contact);
    System.out.println("Contact saved successfully!");
  }
  public void displayContact(){
    Contact contact=addressBookRepository.getContact();
    System.out.println(contact);
  }
  public void editContactByName(String firstName , String newFirstName ,String newLastName, String address, String city, String state, String zip, String phoneNumber , String email){
    Contact contact=addressBookRepository.getContact();
    if(firstName.equals(contact.getFirstName())){
      Contact updatedContact =new Contact(newFirstName, newLastName, address, city, state, zip, phoneNumber, email);
      addressBookRepository.edit(updatedContact);
      System.out.println("Contact updated successfully");
    }else{
      System.out.println("No contact with this name.");
    }
  }
  public void deleteContactByName(String firstName){
    Contact contact = addressBookRepository.getContact();
    if(firstName.equals(contact.getFirstName())){
      addressBookRepository.edit(null);
      System.out.println("Contact deleted successfully");
    }else{
      System.out.println("No contact with this name.");
    }
  }
}