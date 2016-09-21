package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	private Stage mainStage;
	private AnimalList aList;
	private PersonList pList;

	public Controller() {
		
		aList = new AnimalList();
		mainStage = new Stage();
	}

	public void addAnimal(String name, int age, boolean gender, String description, String aType, String breed,String colour) {
		
		Animal a = new Animal(name, age, gender, description, aType, breed, colour);
		aList.addAnimal(a);
		System.out.println("Thank You, " + name + " has been entered to the list of Animals");
	}
	
	public void setPersonlist(PersonList pl){
		this.pList = pl;
	}

	public void setAnimallist(AnimalList pl){
		this.aList = pl;
	}
	
	public AnimalList getAnimalList(){
		return aList;
	}
	
	public void removedAnimal(String name) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the name of the animal: ");
		name = sc.nextLine();
		aList.removeAnimalByName(name);
		System.out.println("Thank You " + name + " has been deleted from the list of Animals");
		sc.close();
	}

	// 0 is lost screen and object, 1 is found
	public void newLostAnimal(String title, int i) {
	
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(5, 5, 5, 5)); 
		Scene scene = new Scene(gPane, 950, 400); 
		mainStage.setScene(scene);

		Text sceneTitle = new Text(title);
		sceneTitle.setFont(Font.font("Segoe UI Semibold", FontWeight.NORMAL, 25));
		gPane.add(sceneTitle, 0, 0, 1, 1);
		
		Label name = new Label("Name of Animal:");
		gPane.add(name, 0, 1);
		final TextField nameField = new TextField();
		gPane.add(nameField, 1, 1);
		
		
		Label age = new Label("Age of Animal:");
		final TextField ageField = new TextField();
		gPane.add(age,0, 3 );
		gPane.add(ageField,1, 3 );

		CheckBox gender = new CheckBox();
		Label gender1 = new Label("Gender of Animal: ");
		gPane.add(gender1, 0, 2);
		gender.setText("Male tick box, female dont tick box ");
		gender.setSelected(true);
		gPane.add(gender, 1, 2);

		Label type = new Label("Type of Animal: ");
		gPane.add(type,0, 4);
		final TextField typeField = new TextField();
		gPane.add(typeField, 1, 4);
		
		Label breed = new Label("Breed of Animal: ");
		gPane.add(breed, 3, 1);
		final TextField breedField = new TextField();
		gPane.add(breedField, 4, 1);
		
		Label colour = new Label("Colour of Animal: ");
		gPane.add(colour, 3, 3);
		final TextField colourField = new TextField();
		gPane.add(colourField, 4, 3);

		Label description = new Label("Description of Animal: ");
		gPane.add(description,  3, 2);
		final TextField descriptionField = new TextField();
		gPane.add(descriptionField, 4, 2); 

		Label location = new Label("Location of Animal: ");
		gPane.add(location, 3, 4);
		final TextField locationField = new TextField();
		gPane.add(locationField, 4, 4);

		Label error = new Label("");
		gPane.add(error, 2, 5);

		Button addAnimalButton = new Button("Add Animal to System");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BASELINE_CENTER); 
		
		hbox.getChildren().add(addAnimalButton);
		gPane.add(hbox, 2, 7);
		mainStage.setTitle("Lost Animal");
		scene.getStylesheets().add(getClass().getResource("animal.css").toExternalForm());
		mainStage.show();
		
		
		
		addAnimalButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				boolean add = true;
				String errorMessage = "";
				String name = nameField.getText();
				String age = ageField.getText();
				String animalColour = colourField.getText();
				String animalBreed = breedField.getText();
				String desc = descriptionField.getText();
				String animalType = typeField.getText();
				String loc = locationField.getText();

				if (name.equals("")) {
					add = false;
					errorMessage = "Please enter the name of the animal";
				}

				if (animalColour.equals("")) {
					add = false;
					errorMessage = "Please enter the colour of the animal";
				}

				if (animalBreed.equals("")) {
					add = false;
					errorMessage = "Please enter the breed of the animal";
				}

				if (desc.equals("")) {
					add = false;
					errorMessage = "Please enter the description of the animal";
				}

				//////only cat or dog are used
				animalType = animalType.toUpperCase();
				if (animalType.equals("") || !(animalType.equals("DOG") || animalType.equals("CAT"))) {
					add = false;
					errorMessage = "Please enter the type of Animal\n(eg. cat or dog)";
				}

		
				boolean gen = gender.isSelected();

				///////// get age, textbox is used, parse the value to a number, if letters are used it shows error//////
				int animalAge = 0;
				try {
					animalAge = Integer.parseInt(age);
				} catch (NumberFormatException e) {
					add = false;
					errorMessage = "Please enter the age of the Animal\n (Please enter the number) ";
				}

				///////if add is true then all fields entered correctly
				if (add) {
					Category c;
					Calendar d = Calendar.getInstance();
					Date date = d.getTime();
					
					/////////////////checks if category should be found or lost based on the number(int)
					
					d.setTime(date);
					if (i == 0) {
						c = new Lost(d, loc);
					} else {
						c = new Found(d, loc);
					}
					
					Person p = new Person("blah", "blah", "blah", 4884623);
					
					c.setPerson(p);
				
					Animal newAnimal = new Animal(name, animalAge, gen, desc, animalType, animalBreed, animalColour);
					newAnimal.setCategory(c);

					aList.addAnimal(newAnimal);
					error.setText("Animal added with name: " + name);
					
					Alert a = new Alert(AlertType.INFORMATION);
			        a.setTitle("Thank you, Animal has been added");
			  
			        a.setResizable(false);
			        a.setContentText("Thank you the animal is on the list");
			        a.showAndWait();
	
					mainStage.close();
				} 
				else
				{
					error.setText(errorMessage);
				}
			}
		});

	}

	@SuppressWarnings("unchecked")
	public void newAdoptedAnimal() {
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(20, 25, 25, 20)); 
		Scene scene = new Scene(gPane, 750, 400); 
		mainStage.setScene(scene);

		Text sceneTitle = new Text("Animal for Adoption");
		sceneTitle.setFont(Font.font("Segoe UI Semibold", FontWeight.NORMAL, 20));
		gPane.add(sceneTitle, 0, 0, 1, 1);
		Label name = new Label("Please select an Animal:");
		gPane.add(name, 0, 1);

		final ComboBox<String> cBoxAnimal = new ComboBox();
		cBoxAnimal.getItems().addAll(aList.getAnimalsForComboBox());
		gPane.add(cBoxAnimal, 1, 1);
		Label name1 = new Label("Please tick:");
		gPane.add(name1, 0, 2);

		CheckBox neutered = new CheckBox();
		neutered.setText("Neutered");
		neutered.setSelected(false);
		gPane.add(neutered, 1, 2);
	
		CheckBox chipped = new CheckBox();
		chipped.setText("Chipped");
		chipped.setSelected(false);
		gPane.add(chipped, 2, 2);
	
		CheckBox vaccinated = new CheckBox();
		vaccinated.setText("Vaccinated");
		vaccinated.setSelected(false);
		gPane.add(vaccinated, 3, 2);
		
		CheckBox reserved = new CheckBox();
		reserved.setText("Reserved");
		reserved.setSelected(false);
		gPane.add(reserved, 4, 2);
		
		Label status = new Label("Status of Animal:");
		gPane.add(status, 0, 5);
						
		final ComboBox<String> cBoxStatus = new ComboBox();
		cBoxStatus.getItems().addAll("Training","Ready","Not Ready");
		gPane.add(cBoxStatus, 1, 5);
		
		final MenuButton interested = new MenuButton("People interested in adoption:");  
        final ArrayList<CheckMenuItem> selectedPeople = new ArrayList<CheckMenuItem>();
        for(Person p:pList.people){
        	CheckMenuItem i = new CheckMenuItem(p.getName()+" - "+p.getPhoneNumber());
        	selectedPeople.add(i);
        }
        interested.getItems().addAll(selectedPeople);  

        final ListView<String> selectedItems = new ListView<>();  
        for (final CheckMenuItem item : selectedPeople) {  
            item.selectedProperty().addListener(new ChangeListener<Boolean>() {  
                @Override  
                public void changed(ObservableValue<? extends Boolean> obs,  
                        Boolean wasPicked, Boolean nowPicked) {  
                    if (nowPicked) {  
                        selectedItems.getItems().add(item.getText());  
                    } else {  
                        selectedItems.getItems().remove(item.getText());  
                    }  
                }  
            });  
        }  

        Label interestedParties = new Label("People interested in adoption:\n Can select more than one");
		gPane.add(interestedParties, 0, 7);	
		
        gPane.add(interested, 1, 7);
        
        Label dateL = new Label("Date you would like to adopt: ");
		gPane.add(dateL, 0, 9);
		
		Label selectedDate = new Label("");
		gPane.add(selectedDate, 1, 10);
		
        final DatePicker dateP = new DatePicker();
        dateP.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = dateP.getValue();
                selectedDate.setText("" + date);
            }
        });
        gPane.add(dateP,1,9);
      
        Label errorLabel = new Label("");
        gPane.add(errorLabel, 0, 10);
		
        Button addAdoptionButton = new Button("Add To Adopted List");
		
		///////////////// event handler found/lost
        addAdoptionButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent arg0) {
        		errorLabel.setText("");
        		Animal adoptedAnimal = null;
    			boolean add = true;
    			
    			//////////check if animal is selected, if not null p exeption when getting value when returned
				try{
					String animalName = (String) cBoxAnimal.getSelectionModel().getSelectedItem().toString(); 
					int i = 0;
					for(String s:cBoxAnimal.getItems()){
						if(s.equals(animalName)){
							adoptedAnimal = aList.getAnimal(i);
						}
						i++;
					}
					System.out.println(adoptedAnimal.getName());
					
				}				
				catch(NullPointerException e){
					errorLabel.setText("Please select Animal for Adoption");
					add = false;
				}
				
				boolean isNeutered = neutered.isSelected();
				boolean isVaccinated = vaccinated.isSelected();
				boolean isChipped = chipped.isSelected();
				boolean isReserved = reserved.isSelected();
				
				//////////check if animal is slected, if not null p exeption when getting value when returned
				String animalStatus = "";
				try{
					animalStatus = (String) cBoxAnimal.getSelectionModel().getSelectedItem().toString(); 
				}				
				catch(NullPointerException e){
					errorLabel.setText("Please select the condition of Animal");
					add = false;
				}
				
				////////get date,parse into calendar object get the string val from label
				Calendar adoptionDate = Calendar.getInstance();
				if(selectedDate.getText().equals("")){
					add = false;
					errorLabel.setText("Please select the date you would like to adopt");
				}else{

					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
					Date d;
					try {
						d = format1.parse(selectedDate.getText());
						adoptionDate.setTime(d); 
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				}
				
				PersonList interestedPeople = new PersonList();
				//////////interested people, need loop through slected items til theres a match in the person list
				for(String people: selectedItems.getItems()){
					for(Person p:pList.people){
						String valMatch = p.getName()+p.getPhoneNumber();
						if(valMatch.equals(people)){
							interestedPeople.addPerson(p);
						}
					}
				}

				if(add){
					Adoption adoption = new Adoption(adoptionDate);
					adoption.setChipped(isChipped);
					adoption.setNeutered(isNeutered);
					adoption.setInterested(interestedPeople);
					adoption.setReserved(isReserved);
					adoption.setStatus(animalStatus);
					adoption.setVaccinated(isVaccinated);
					
					////////set animal in list to be in adopted cateogry now
					aList.getAnimal(aList.getAnimalbyPosition(adoptedAnimal.getID())).setCategory(adoption);
					
					Alert a = new Alert(AlertType.INFORMATION);
			        a.setTitle("Animal has been updated");
			        a.setResizable(false);
			        a.setContentText("Congrads" + adoptedAnimal.getName()+" is ready to be adopted");
			        a.showAndWait();
					mainStage.close();
				}
			}
		});
        
        gPane.add(addAdoptionButton, 0, 11);
        
        mainStage.setTitle("Adoption an animal");
		scene.getStylesheets().add(getClass().getResource("animal.css").toExternalForm());
		mainStage.show();

	}
	
	public void doReport(String title, int counter){

		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(20, 25, 25, 20)); 
		Scene scene = new Scene(gPane, 600, 400); 
		
		mainStage.setScene(scene);
		mainStage.setTitle(title);
		
		Text sceneTitle = new Text(title);
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		gPane.add(sceneTitle, 0, 0, 1, 1);
		TextArea    information;
		information = new TextArea(".......info will be placed here");
		String reportText = "";
		
		switch(counter){
			case 1: TextInputDialog dialog = new TextInputDialog("Location");
					
					dialog.setContentText("Please enter the location:");
					String location = "";
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
					    location = result.get();
					}
					reportText = lostAnimalsByLocation(location);
					break;
					
			case 2: reportText = "Not Yet Implemented";
					break;
					
			case 3: TextInputDialog dialog2 = new TextInputDialog("Location");
					
					dialog2.setContentText("Please enter the location:");
					String foundlocation = "";

					Optional<String> result2 = dialog2.showAndWait();
					if (result2.isPresent()){
						foundlocation = result2.get();
					}

					reportText = foundAnimalsByLocation(foundlocation);
					break;
					
			case 4: reportText = "Not Yet Implemented";//write method to generate lost report
					break;
					
			case 5: reportText = "Not Yet Implemented";//write method to generate lost report
					break;
					
			case 6: reportText = "Not Yet Implemented";//write method to generate lost report
					break;
					
			case 7: reportText = "Not Yet Implemented";//write method to generate lost report
					break;
					
			case 8: reportText = aList.getAdoptedAndReadyAnimals();
					break;
		}
		
		information.setText(reportText);
		gPane.add(information, 0, 1);
		scene.getStylesheets().add(getClass().getResource("animal.css").toExternalForm());
		mainStage.show();

	}
	
	//////save the running lists in the system
	public void save() {
		writeAnimals(aList);
		writePeople(pList);
	}

	public void removedAnimal() {

		GridPane grPane = new GridPane();
		grPane.setAlignment(Pos.CENTER);
		grPane.setHgap(10);
		grPane.setVgap(10);
		grPane.setPadding(new Insets(20, 25, 25, 25));
		Scene scene = new Scene(grPane, 450, 270);
		mainStage.setScene(scene);
		
		Text sceneTitle = new Text("Remove Animal");
		sceneTitle.setFont(Font.font("Segoe UI Semibold", FontWeight.NORMAL, 22));
		grPane.add(sceneTitle, 0, 0, 1, 1);
		
		Label error = new Label("");
		grPane.add(error, 0, 6);
	
		final ComboBox<String> animalComboBox = new ComboBox();
		animalComboBox.getItems().addAll(aList.getAnimalsForComboBox());
		grPane.add(animalComboBox, 1, 3);
		
		Label selectanimal = new Label("Please select an Animal:");
		grPane.add(selectanimal, 0, 3);
		
		Button removeButton = new Button("Erase Animal");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				Animal removedAnimal = null;
				
				try{
					String animalName = (String) animalComboBox.getSelectionModel().getSelectedItem().toString(); 
				
					int i = 0;
					for(String s:animalComboBox.getItems()){
						if(s.equals(animalName)){
							removedAnimal = aList.getAnimal(i);
						}
						i++;
					}
					
					boolean success = aList.removeAnimalByName(removedAnimal.getName());
					if (success) {
						Alert c = new Alert(AlertType.INFORMATION);
				        c.setHeaderText("thank you" + removedAnimal.getName()+" has been erased");
				        c.setResizable(false);
				        c.showAndWait();
	
						mainStage.close();
					} else {
						error.setText("Uh oh" +removedAnimal.getName() + " has not been erased");
					}
					
				}				
				catch(NullPointerException e){
					error.setText("Please select an Animal to be erased");
				}
			}
		});
		VBox ox2 = new VBox(10);
	
		ox2.getChildren().add(removeButton);
		grPane.add(ox2, 2, 3);
		mainStage.setTitle("Erase animal");
		scene.getStylesheets().add(getClass().getResource("animal.css").toExternalForm());
		mainStage.show();
	}
	

	public void displayAnimals(){
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(20, 25, 25, 20));
		Scene scene = new Scene(pane, 600, 400); 
		
		mainStage.setScene(scene);
		
		Text sceneTitle = new Text("Animals On The System");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 1, 1);
		TextArea    information;
		information = new TextArea("......... info will be placed here");
		information.setText(aList.toString());
		pane.add(information, 0, 1);
		scene.getStylesheets().add(getClass().getResource("animal.css").toExternalForm());
		mainStage.show();

	}
	public void readAnimals(){
		try
	      {
	         FileInputStream fileOut =
	         new FileInputStream("shelter.ser");
	         ObjectInputStream out = new ObjectInputStream(fileOut);
	         aList = (AnimalList) out.readObject();
	         out.close();
	         fileOut.close();
	      }
		catch(IOException | ClassNotFoundException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public void writeAnimals(AnimalList al){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("shelter.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(al);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public void readPeople(){
		try
	      {
	         FileInputStream fileOut =
	         new FileInputStream("people.ser");
	         ObjectInputStream out = new ObjectInputStream(fileOut);
	         pList = (PersonList) out.readObject();
	         out.close();
	         fileOut.close();
	      }catch(IOException | ClassNotFoundException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public void writePeople(PersonList al){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("people.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(al);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}

	public String lostAnimalsByLocation(String loc){
		String output = "Animals Lost at Location\n";
		//search all animals by lost category
		for(Animal a:aList.getList()){
			//check is category lost
			if(a.getCategory() instanceof Lost){
				//cast category to lost
				Lost lost = (Lost) a.getCategory();
				//check if location matches search location
				if(lost.getLocation().toUpperCase().equals(loc.toUpperCase())){
					output += a.getName()+"\n";
				}
			}
		}
		
		if(output.equals("")){
			output = "No Lost animals at location: "+loc;
		}
		return output;
	}
	
	public String foundAnimalsByLocation(String loc){
		String output = "Animals Found at Location\n";
		//search all animals by lost category
		for(Animal b:aList.getList()){
			//check is category lost
			if(b.getCategory() instanceof Found){
				//cast category to found
				Found found = (Found) b.getCategory();
				//check if location matches search location
				if(found.getLocation().toUpperCase().equals(loc.toUpperCase())){
					output += b.getName()+"\n";
				}
			}
		}
		
		if(output.equals("")){
			output = "No Found animals at location: "+loc;
		}
		return output;
	}
}
