package application;

import java.io.Serializable;
import java.util.Calendar;

public class Adoption extends Category  implements Serializable{
	
	private boolean neutered,chipped,vaccinated,reserved;
	String status;
	Calendar date;
	PersonList interested;
	
	public Adoption(Calendar date) {
		super(date,"Adoption");
		neutered = false;
		chipped = false;
		vaccinated = false;
		reserved = false;
		interested = new PersonList();
	}
	/////////////////////////////////GETTERS//////////////////////////////
	public boolean getNeutered() {
		return neutered;
	}

	public boolean getChipped() {
		return chipped;
	}

	public boolean getVaccinated() {
		return vaccinated;
	}
	
	public boolean getReserved() {
		return reserved;
	}
	
	public String getStatus() {
		return status;
	}

	////////////////////////////////////SETTERS//////////////////////////////
	public void setChipped(boolean chipped) {
		this.chipped = chipped;
	}

	public void setNeutered(boolean neutered) {
		this.neutered = neutered;
	}

	
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setInterested(PersonList p){
		this.interested = p;
	}
	
	///////////////////////////////////Print to string //////////////////////////////////
	public String toString(){
		String output = "\n Adoption Date: "+this.getDate().getTime(); output+="\n Neutered: "+getNeutered();
		output+="\n Chipped: "+getChipped(); output+="\n Vaccinated: "+getVaccinated();
		output+="\n Reserved: "+getReserved(); output+="\n Status: "+getStatus();
		
		String personInterested = "";
		for(Person p:interested.people){
			personInterested+="\n"+p.getName() + p.getPhoneNumber();
		}
		if(personInterested.equals("")){
			personInterested = "No interested People";
		}
		output+="\n Interested Parties: "+personInterested;
		return output;
	}
	public void print(){
		System.out.println(toString());
	}
}
