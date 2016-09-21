package application;


import java.util.Calendar;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	static AnimalList shelterAnimals;
	static PersonList people;
	
	private Controller controll;
	Stage window;
	Button button;
	private static  Button      lostAnimal,foundAnimal,adoptedAnimal,removeAnimal,listAnimals;
	MenuItem quit,save,lostByLocation,lostByDate,foundByLocation,foundByDate,foundBetweenDateAndGender,adoptedAndReadyByName,adoptedAndReadyByAge,inTraining;
	Menu fileMenu,reportsMenu,foundReport,lostReport,adoptReport;
	private static  TextArea    information;
	LoadingIndicator loading;
	
	public void start(Stage mainStage) {
		LoadingIndicator loading = new LoadingIndicator("Loading info from file");
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		delay.setOnFinished( event -> loading.close() );
		delay.play();
		
		window = mainStage;
	    controll = new Controller();
	    window.setTitle("Animal Shelter");
	    controll.setPersonlist(people);
	    controll.setAnimallist(shelterAnimals);
	    //controll.writeAnimals(shelterAnimals);
	   // controll.writePeople(people);
	    controll.readAnimals();
	    controll.readPeople();
	    lostAnimal = new Button ("Lost Animal");
	    lostAnimal.setOnAction(e -> controll.newLostAnimal("Lost Animal Form",0));
	    foundAnimal = new Button ("Found Animal");
	    foundAnimal.setOnAction(e -> controll.newLostAnimal("Found Animal Form",1));
	    adoptedAnimal = new Button ("Animal Adoption");
	    adoptedAnimal.setOnAction(e -> controll.newAdoptedAnimal());
	    removeAnimal = new Button ("Remove Animal");
	    removeAnimal.setOnAction(e -> controll.removedAnimal());
	    listAnimals = new Button ("Display all the animals");
	    listAnimals.setOnAction(e -> controll.displayAnimals());
	    
	    fileMenu = new Menu("File");
	    save = new MenuItem("Save");
	    quit = new MenuItem("Exit");
	    quit.setOnAction(actionEvent -> System.exit(0));    
	    save.setOnAction(actionEvent -> controll.save());    
	    
	    
	    reportsMenu = new Menu("Reports");
	    lostReport = new Menu("Lost");
	    foundReport = new Menu("Found");
	    adoptReport = new Menu("Adopt");
	    
	    lostByLocation = new MenuItem("By Location");
	    lostByDate = new MenuItem("By Date");
	    lostReport.getItems().addAll(lostByLocation,lostByDate);
	    
	    foundByLocation = new MenuItem("By Location");
	    foundByDate = new MenuItem("By Date");
	    foundBetweenDateAndGender = new MenuItem("By Date&Gender");
	    foundReport.getItems().addAll(foundByLocation,foundByDate,foundBetweenDateAndGender);
	    
	    adoptedAndReadyByName = new MenuItem("By Name");
	    adoptedAndReadyByAge = new MenuItem("By Age");
	    inTraining = new MenuItem("In Training");
	    adoptReport.getItems().addAll(adoptedAndReadyByName,adoptedAndReadyByAge,inTraining);
	    
	    lostByLocation.setOnAction(actionEvent -> controll.doReport("Lost Ordered By Location", 1));    
	    lostByDate.setOnAction(actionEvent -> controll.doReport("Lost Ordered By Date", 2));    
	    
	    foundByLocation.setOnAction(actionEvent -> controll.doReport("Found Ordered By Location", 3));    
	    foundByDate.setOnAction(actionEvent -> controll.doReport("Found Ordered By Date", 4));    
	    foundBetweenDateAndGender.setOnAction(actionEvent -> controll.doReport("Found Between Dates Ordered by Gender", 5));    
	    
	    adoptedAndReadyByName.setOnAction(actionEvent -> controll.doReport("Ready For Adoption Ordered By Name", 6));    
	    adoptedAndReadyByAge.setOnAction(actionEvent -> controll.doReport("Ready For Adoption Ordered By Age", 7));    
	    
	    inTraining.setOnAction(actionEvent -> controll.doReport("Adoption Animals in Training", 8));    
	    
	    MenuBar menuBar = new MenuBar();
	    menuBar.prefWidthProperty().bind(window.widthProperty());
	    
	    reportsMenu.getItems().addAll(lostReport,foundReport,adoptReport);
	    
	    fileMenu.getItems().addAll(save,quit);
	    VBox v1 = new VBox(5);
	    v1.getChildren().addAll(lostAnimal,foundAnimal,adoptedAnimal);
	   
	    HBox vb1 = new HBox(1);
	    vb1.getChildren().add(v1);
	    
	    VBox v2 = new VBox(5);
	    v2.getChildren().addAll(removeAnimal,listAnimals);

	    HBox vb2 = new HBox(1);
	    vb2.getChildren().add(v2);
	    
	 
	    HBox layout = new HBox(30);
	    layout.setPadding(new Insets(10, 10 , 10, 10));
	    layout.getChildren().addAll(vb1,vb2);
	    lostAnimal.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;-fx-text-fill: rgb(49, 89, 23);-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	    foundAnimal.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;-fx-text-fill: rgb(49, 89, 23);-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	    adoptedAnimal.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;-fx-text-fill: rgb(49, 89, 23);-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	    removeAnimal.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;-fx-text-fill: rgb(49, 89, 23);-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	    listAnimals.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;-fx-text-fill: rgb(49, 89, 23);-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
		menuBar.getMenus().addAll(fileMenu,reportsMenu );
	    menuBar.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;-fx-text-fill: rgb(49, 89, 23);-fx-border-color: rgb(49, 89, 23); -fx-border-radius: 5;");
	  
	    BorderPane root = new BorderPane();
	    root.setTop(menuBar);
	    root.setCenter(layout);
	    Scene scene = new Scene(root, 500, 250);
	    
	    window.setScene(scene);
	    window.show();
	    window.toBack();

		PauseTransition delay2 = new PauseTransition(Duration.seconds(3));
		delay2.setOnFinished( event -> window.toFront());
		delay2.play();
	}
	public static void main(String[] args) {
		
		launch(args);
	}
}
