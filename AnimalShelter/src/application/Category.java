package application;

import java.io.Serializable;
import java.util.Calendar;

public class Category  implements Serializable{
	
	Person contact;
	Calendar date;
	private String type;


	public Category(Calendar date,String type) {
		this.date = date;
		this.type = type;
	}
//////////////////////////////////////GETTERS///////////////////////////////////////////////
	public String getType() {
		return type;
	}

	public Person getPerson(){
		return contact;
	}
	
	public Calendar getDate(){
		return date;
	}
/////////////////////////////////////////SETTERS///////////////////////////////////////////
	public void setType(String type) {
		this.type = type;
	}

	public void setPerson(Person p){
		this.contact = p;
	}
	
	public void setDate(Calendar date){
		this.date=date;
	}

////////////////////////////////////////print to string//////////////////////////////////	
	public String toString(){
		String output = "\nTime and Date is : " + date.getTime();
		return output;
	}

	public void print()
	{
		System.out.println(toString());
	}
		
}