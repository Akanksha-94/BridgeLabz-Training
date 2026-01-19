package address_book.repository;
import address_book.entity.Contact;
public class AddressBookRepository {
  private Contact contact;
  public void save(Contact contact){
    this.contact=contact;
  }
  public Contact getContact(){
    return contact;
  }
  public void edit(Contact updatedContact){
    this.contact=updatedContact;
  }
}