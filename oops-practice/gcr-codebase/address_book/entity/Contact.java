package address_book.entity;

public class Contact {
  private String firstName;
  private String lastName;
  private String address;
  private String city;
  private String state;
  private String zip;
  private String phonenumber;
  private String email;

  
  public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phonenumber, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.city = city;
      this.state = state;
      this.zip = zip;
      this.phonenumber = phonenumber;
      this.email = email;
  }

  public String getFirstName(){
      return firstName;
  }
  public String setFirstName(){
      return firstName ;
  }
  public String getLastName(){
      return lastName;
  }
  public String setLastName(){
      return lastName ;
  }
  public String getAddress(){
    return address;
  }
  public String setAddress(){
    return address;
  }
  public String getCity(){
    return city;
  }
  public String seCity(){
    return city;
  }
  public String getState(){
    return state;
  }
  public String setState(){
    return state;
  }
  public String getZip(){
    return zip;
  }
  public String setZip(){
    return zip;
  }
  public String getPhoneNumber(){
    return phonenumber;
  }
  public String setPhoneNumber(){
    return phonenumber;
  }
  public String getEmail(){
    return email;
  }
  public String setEmail(){
    return email;
  }

  @Override
  public String toString() {
    return "Contact [firstName= " + firstName + ", lastName= " + lastName + ", address= " + address + ", city= " + city
        + ", state= " + state + ", zip= " + zip + ", phonenumber= " + phonenumber + ", email= " + email + "]";
  }



}