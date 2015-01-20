package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;
import voetbalmanager.model.Speler;

public class MarktController implements Initializable, ControlledScreen{

	@FXML
	private Button BackMarket;
	ScreensController myController;   
	
	@FXML private ListView<Speler> LijstKopenA;
	@FXML private ListView<Speler> SecLijstKopenA;
	private ObservableList<Speler> KopenAdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstKopenV;
	@FXML private ListView<Speler> SecLijstKopenV;
	private ObservableList<Speler> KopenVdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstKopenD;
	@FXML private ListView<Speler> SecLijstKopenD;
	private ObservableList<Speler> KopenDdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstVerkopenA;
	@FXML private ListView<Speler> SecLijstVerkopenA;
	private ObservableList<Speler> VerkopenAdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstVerkopenV;
	@FXML private ListView<Speler> SecLijstVerkopenV;
	private ObservableList<Speler> VerkopenVdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstVerkopenD;
	@FXML private ListView<Speler> SecLijstVerkopenD;
	private ObservableList<Speler> VerkopenDdata = FXCollections.observableArrayList();
	@FXML private BorderPane border;
	
	
	public MarktController(){
		
	}
	

	   @Override
	   public void initialize(URL location, ResourceBundle resources){
		   Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		   border.setPrefSize(screen.getWidth(), screen.getHeight());
		   
	   }
	   
	   
	   public static void addList(ObservableList<Speler> data){
	    	//Competitie com = new Competitie("blah");
			//com = XMLLoader.creeerCompetitie("this");
			//for(int i=0; i < data.size(); i++)
			//TODO methode oproepen die een arraylist van een bepaald type terug geeft
			//data.add(Main.huidigeCompetitie);
		   
		   data.addAll(Main.huidigSpel.getCompetitie().getSpelerTeam().getSelectie());
	   }
	   
	   public static void LijstNaarScherm(ObservableList<Speler> data){
		   
		   
	   }
	
	public void setScreenParent(ScreensController screenParent){
	       myController = screenParent;
	   }
	   
	
	@FXML
	public void handleBackMarket() throws IOException{
	myController.setScreen(Main.ManagementMain);
	Main.getStage().setTitle("Main Managment");
	 } 
	 

	@FXML
	public void handleKopenA() throws IOException{

	 } 
	
	@FXML
	public void handleKopenV() throws IOException{

	 } 
	
	@FXML
	public void handleKopenD() throws IOException{

	 } 
	
	@FXML
	public void handleVerkopenA() throws IOException{

	 } 
	
	@FXML
	public void handleVerkopenV() throws IOException{

	 } 
	
	@FXML
	public void handleVerkopenD() throws IOException{

	 } 



}
