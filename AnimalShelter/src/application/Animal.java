package application;

import java.io.Serializable;

public class Animal  implements Serializable{

	private int ID,age;
	private String name,description,aType,breed,colour;
	private boolean gender;
	private static int id = 1;
	Category myDog;

	Animal(String name, boolean gender, String description,String aType) {
		this.name = name;
		this.gender = gender;
		this.description = description;
		this.aType = aType;
		this.ID = id;
		id++;
		myDog = null;
	}

	Animal(String name,int age,boolean gender, String description,String aType, String breed,String colour){
		this.ID = id;
		id++;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.description = description;
		this.aType = aType;
		this.breed = breed;
		this.colour = colour;
	}
	
	//////////////////////////GETTERS////////////////////////
	
	public Category getCategory()
	{
		return myDog;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getBreed()
	{
		return breed;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getColour()
	{
		return colour;
	}
	
	public String getaType()
	{
		return aType;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public boolean getGender()
	{
		return gender;
	}
	
	////////////////////////////////////SETTERS////////////////////////////////
	
	public void setCategory(Category myDog) {
		this.myDog = myDog;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public void setBreed(String breed)
	{
		this.breed = breed;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	
	public void setaType(String aType)
	{
		this.aType = aType;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}	
	
	public void setGender(boolean gender)
	{
		this.gender = gender;
	}
	
    public String toString()
    {
        String output = "Name of Animal: " + this.name + " " + "ID of Animal: "+ " " + this.ID + 
        				"Age of Animal: "+ " " + this.age + "Type of Animal: "+ " " + this.aType + 
        				"Breed of Animal: " + " "+this.breed + "Gender of Animal: "+ " " + this.gender +
        				"Description of Animal: "+ " "+ this.description;  
       
        return output;
    }
    
    public void print()
    {
        System.out.println(toString());
    }
}