package com.example.AddressBook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.AddressBook.model.Person;

public class AddressBookSystem {
	HashMap<String,AddressBook> BookName=new HashMap<>();
	
	public void addNewBook(String name,AddressBook addressbook) {
		BookName.put(name,addressbook);
	}
	
	public boolean searchBookName(String name) {
			if(BookName.containsKey(name))
				return true;
			else 
				return false;
		}
		
	public AddressBook getBook(String name) {
		return BookName.get(name);
	}
	
	public List<Person> searchByCity(String City,String state) {
		
		List<Person> p=new ArrayList<>();
		
		for(Map.Entry<String, AddressBook> entry:BookName.entrySet()) {
			String key=entry.getKey();
			AddressBook val=entry.getValue();
			
			p.addAll(val.findContactByCityAndState(City, state));
		}
		return p;
	}

}

