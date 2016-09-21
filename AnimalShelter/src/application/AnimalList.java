package application;

import java.io.Serializable;
import java.util.ArrayList;

public class AnimalList implements Serializable{
	
	private ArrayList <Animal> animals = null;

	public AnimalList()
	{
		animals = new ArrayList <Animal>();
	}
	
	public void addAnimal(Animal a){
		animals.add(a);
	}

	public ArrayList <Animal> getList(){
		return animals;
	}

	public void removeAnimal(int i){

		if((i>-1) && (i < animals.size()))
			animals.remove(i);
	}

	public boolean removeAnimalByName(String name){
		boolean removed = false;
		name = name.toUpperCase();
		for(int i=0; i<animals.size(); i++)
			if(getAnimal(i).getName().toUpperCase().equals(name)){
				animals.remove(i);
				removed = true; }
		return removed;
	}
	
	public Animal searchAnimal(int id){
		for(Animal a :animals){
			if(a.getID()==id){
				return a;
			}
		}

		return null;
	}

	public int getAnimalbyPosition(int id){
		int i = 0;
		for(Animal a :animals){
			if(a.getID()==id){
				return i; 
			}
			i++;
		}
		
		return -1;
	}
	
	public Animal getAnimal(int i)
	{
		if((i>-1) && (i < animals.size()))
		{
			return animals.get(i);
		}
		return null;
	}

	public int getSize()
	{
		return animals.size();
	}
	
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		String output = "";
		
		for(Animal an:animals)
		{
			System.out.println(an.toString());
			if(an.getCategory() != null)
			{
				
				output +="\n \n Information about Animal\n"
						+"\nName of Animal:" + an.getName() +"\n ID of Animal: "+ an.getID()  
						+"\n Age of Animal: " +an.getAge() +"\nGender(True is Male, False is Female): " +an.getGender() 
						+"\n Type of Animal: " + an.getaType() +"\n Breed Of Animal: " +an.getBreed() 
						+"\n Description of Animal: "+ an.getDescription() + "\n Category:" + an.getCategory().getType()+an.getCategory().toString();
						
				if(an.getCategory() instanceof Adoption){
				}
				else
				{
					output += " \n Owner:" + an.getCategory().getPerson().getName()+"\n";
				}
			}
		}
		
		if(output.equals("")){
			output = "No animals in system";
		}
		return output;
	}
	
	
	public ArrayList<String> getAnimalsForComboBox(){
		ArrayList<String> names = new ArrayList<String>();
		
		for(Animal a: animals){
			names.add(a.getName());
		}
		return names;
	}
	//////////// report /////////////
	public String getAdoptedAndReadyAnimals(){
		String output = "Animals ready and going to be adopted\n";
		//loop through all animals
		for(Animal a:animals){
			//check is category an instance of adoption
			if(a.getCategory() instanceof Adoption){
				//cast category to adoption so we can get the status
				Adoption adopt = (Adoption) a.getCategory();
				//if status = Training then add animal to report
				if(adopt.getStatus().equals("Training")){
					output += a.getName();
				}
			}
		}
		if(output.equals("")){
			output = "No Animals up for adoption are currently in training";
		}
		return output;
		
	}
}
