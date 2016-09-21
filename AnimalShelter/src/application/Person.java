package application;

import java.io.Serializable;

public class Person  implements Serializable{

	private String name,address,email;
	private int phoneNumber;


	public Person(String name, String address, String email, int phoneNumber) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;

	}
	//////////////////////////////////////SETTERS///////////////////////////////////////////////
	public void setName(String name){
		this.name = name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setPhoneNumber(int phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	////////////////////////////////////////GETTERS///////////////////////////////////////////////
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getAddress(){
		return address;
	}
	
	public int getPhoneNumber(){
		return phoneNumber;
	}
	
	
	//////////////////////////// print to string //////////////////////////////////
	
    public String toString()
    {
        String output = "Person's name is : "+ this.name + "\n" + "Phone number: "+this.phoneNumber +"\n" + "Address: " +this.address+"\n" + "Their email is: " + this.email + "";
        return output;
    }
    
    public void print()
    {
        System.out.println(toString());
    }
    
	
}

