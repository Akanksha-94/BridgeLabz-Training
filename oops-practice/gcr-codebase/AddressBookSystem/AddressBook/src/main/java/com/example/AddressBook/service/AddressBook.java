package com.example.AddressBook.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.AddressBook.model.Person;


public class AddressBook {
	private ArrayList<Person> contacts=new ArrayList<>();
	
	public void addContact(Person person) {
		contacts.add(person);
	}
	
	public Person findContactByName(String firstName,String lastName) {
		for(Person person:contacts) {
			if(person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				return person;
			}
		}
		return null;
	}
	
	public List<Person> findContactByCityAndState(String City,String State) {
		
		List<Person> result=new ArrayList<>();
		
		for(Person person:contacts) {
			if(person.getCity().equalsIgnoreCase(City) && person.getState().equalsIgnoreCase(State)) {
				result.add(person);
			}
		}
		return result;
	}
	
	//UC4(Deletion by name)
	public boolean deleteContactByName(String firstName,String lastName) {
		
		Iterator<Person> i=contacts.iterator();
		
		while(i.hasNext()) {
			Person person=i.next();
			
			if(person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				i.remove();
				return true;
			}
		}
		return false;
	}
	
	
	public void displayContacts() {
		for(Person person:contacts) {
			System.out.println(person.getFirstName()+" "+person.getLastName());
			System.out.println("Address : "+person.getAddress());
			System.out.println("State : "+person.getState());
			System.out.println("City :"+person.getCity());
			System.out.println("Code :"+person.getZip());
			System.out.println("PhoneNumber :"+person.getPhoneNumber());
			System.out.println("Email :"+person.getEmail());
			System.out.println("DOB :"+person.getDOB());
			System.out.println("--------------------------");
		}
	}
}
