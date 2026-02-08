package com.example.AddressBook.model;

public class Person {
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String state;
		private String zip;
		private String phoneNumber;
		private String email;
		private String DOB;
		
		public Person(String firstName, String lastName, String address, String city, String state, String zip,
				String phoneNumber, String email) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}
		
		public void setAddress(String address) {
			this.address=address;
		}

	 
	    public void setCity(String city) {
	    	this.city=city;
	    }

	   
	    public void setState(String state) {
	    	this.state=state;
	    }

	   
	   public void setZip(String zip) {
		   this.zip=zip;
	   }

	    public void setPhoneNumber(String phoneNumber) {
	    	this.phoneNumber=phoneNumber;
	    }

	    public void setEmail(String email) {
	    	this.email=email;
	    }
	    
	    public void setDOB(String dOB) {
			DOB = dOB;
		}
	    
	    public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	    
	    public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		
		public String getAddress() {
			return address;
		}

		public String getState() {
			return state;
		}
		
		public String getCity() {
			return city;
		}
		
		public String getZip() {
			return zip;
		}
		
		public String getPhoneNumber() {
			return phoneNumber;
		}
		
		public String getEmail() {
			return email;
		}
		
		public String getDOB() {
			return DOB;
		}
		
		public String getFirstName() {
			return firstName;
		}



		public String getLastName() {
			return lastName;
		}
		
	}

