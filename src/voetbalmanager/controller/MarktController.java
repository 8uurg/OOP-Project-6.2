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
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;
import voetbalmanager.model.BeschikbareSpeler;
import voetbalmanager.model.Speler;

public class MarktController implements Initializable, ControlledScreen{

	@FXML private Button BackMarket;
	ScreensController myController;   
	
	@FXML private ListView<BeschikbareSpeler> kopenSpeler;
	@FXML private TextArea kopenSpelerId;
	@FXML private Button Kopen;
	private ObservableList<BeschikbareSpeler> Kopendata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> verkopenSpeler;
	@FXML private TextArea verkopenSpelerId;
	@FXML private Button Verkopen;
	private ObservableList<Speler> Verkopendata = FXCollections.observableArrayList();
	
	@FXML private BorderPane border;
	
	
	public MarktController(){
		 Verkopendata.addAll(Main.huidigSpel.getCompetitie().getSpelerTeam().getSelectie());
		 Kopendata.addAll(Main.huidigSpel.getCompetitie().getTransferMarkt().getVerhandelbareSpelers());
	}
	

	   @Override
	   public void initialize(URL location, ResourceBundle resources){
		   Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		   border.setPrefSize(screen.getWidth(), screen.getHeight());
		   
		   verkopenSpelerId.setDisable(true);
		   kopenSpelerId.setDisable(true);
		   
		   //init kopenList
		   
	   }
	   
	
	public void setScreenParent(ScreensController screenParent){
	       myController = screenParent;
	   }
	   
	
	@FXML
	public void handleBackMarket() throws IOException{
	myController.setScreen(Main.ManagementMain);
	Main.getStage().setTitle("Main Managment");
	 } 
	 



}
