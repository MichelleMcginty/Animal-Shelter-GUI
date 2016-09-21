package application;


import java.io.Serializable;
import java.util.Calendar;

public class Lost extends Category  implements Serializable{


	private String location;
	
	public Lost(Calendar date,String location) {
		super(date,"Lost");
		this.location = location;
		
	}

	public String getLocation(){
		return location;
	}

	public String toString(){
		String output = "\n Time and Date is : " + date.getTime()+ "  " + "\n Location "+ location;
		return output;
	}

	public void print()
	{
		System.out.println(toString());
	}

}
