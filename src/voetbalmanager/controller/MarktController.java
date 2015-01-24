package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;
import voetbalmanager.Spel;
import voetbalmanager.model.BeschikbareSpeler;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;
import voetbalmanager.model.TransferMarkt;

public class MarktController implements Initializable, ControlledScreen, Observer{

	@FXML private Button BackMarket;
	@FXML private ListView<BeschikbareSpeler> kopenSpeler;
	@FXML private TextArea kopenSpelerId;
	@FXML private Button Kopen;
	@FXML private ListView<Speler> verkopenSpeler;
	@FXML private TextArea verkopenSpelerId;
	@FXML private Button Verkopen;
	@FXML private BorderPane border;
	
	ScreensController myController; 
	private Speler verkoopFocus;
	private BeschikbareSpeler koopFocus;
	private ObservableList<Speler> Verkopendata = FXCollections.observableArrayList();
	private ObservableList<BeschikbareSpeler> Kopendata = FXCollections.observableArrayList();
	
	public MarktController(){
		Main.huidigSpel.addObserver(this);
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources){
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		border.setPrefSize(screen.getWidth(), screen.getHeight());
		 
		
		 
		//init verkopenList
		//TODO Arthur help
		verkopenSpeler.setItems(Verkopendata);
		verkopenSpeler.setCellFactory((list) -> {return new ListCell<Speler>(){
			@Override
			protected void updateItem(Speler item,boolean empty){
				super.updateItem(item, empty);
				if(item==null || empty){
					setText(null);
				}else{
					setText(item.getNaam());
				}
			}
		};
		});
		// Details in de textarea
		verkopenSpeler.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			verkopenSpelerId.clear();
			if(newValue!= null)verkopenSpelerId.appendText(newValue.toString());
			else verkopenSpelerId.appendText("Geen speler geselecteerd.");
			
			verkoopFocus = newValue;
		});
		   
		   
		//init kopenList
		//TODO Arthur help

		kopenSpeler.setItems(Kopendata);
		kopenSpeler.setCellFactory((list) -> {return new ListCell<BeschikbareSpeler>(){
			@Override
			protected void updateItem(BeschikbareSpeler item,boolean empty){
				super.updateItem(item, empty);
					if(item==null || empty){
						setText(null);
					}else{
						setText(item.getSpeler().getNaam());
					}
			}
		};
		});
		// Details in de textarea
		kopenSpeler.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			kopenSpelerId.clear();
			if(newValue!=null) kopenSpelerId.appendText(newValue.getSpeler().toString());
			else kopenSpelerId.appendText("Geen speler geselecteerd.");
			
			koopFocus = newValue;
		});
		
		verkopenSpelerId.setDisable(true);
		kopenSpelerId.setDisable(true);
	   
	}
	   
	
	public void setScreenParent(ScreensController screenParent){
	       myController = screenParent;
	   }
	   
	
	@FXML
	public void handleKopen() throws IOException{
		Main.huidigSpel.getCompetitie().getTransferMarkt().koopSpeler(Main.huidigSpel.getCompetitie().getSpelerTeam(), koopFocus);
	}
	
	@FXML
	public void handleVerkopen() throws IOException{
		Main.huidigSpel.getCompetitie().getTransferMarkt().maakVerhandelbaar(verkoopFocus);;
	}
	
	@FXML
	public void handleBackMarket() throws IOException{
	myController.setScreen(Main.ManagementMain);
	Main.getStage().setTitle("Main Managment");
	 }


	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Spel) {
			// Huidige spel is veranderd. Update!
			Main.huidigSpel.getCompetitie().addObserver(this);
			Team steam;
			if((steam = Main.huidigSpel.getCompetitie().getSpelerTeam())!=null){
				this.update(steam, arg1);
			}
			TransferMarkt smarkt;
			if((smarkt = Main.huidigSpel.getCompetitie().getTransferMarkt())!=null){
				this.update(smarkt, arg1);
			}
			
		}
		if(arg0 instanceof Competitie) {
			Main.huidigSpel.getCompetitie().getSpelerTeam().addObserver(this);
			Main.huidigSpel.getCompetitie().getTransferMarkt().addObserver(this);
			Kopendata.clear();
			Kopendata.addAll(Main.huidigSpel.getCompetitie().getTransferMarkt().getVerhandelbareSpelers());
			
			Verkopendata.clear();
			Verkopendata.addAll(Main.huidigSpel.getCompetitie().getSpelerTeam().getSelectie());
		}
		if(arg0 instanceof Team) {
			// Team is veranderd. Update!
			Verkopendata.clear();
			Verkopendata.addAll(Main.huidigSpel.getCompetitie().getSpelerTeam().getSelectie());
		}
		if(arg0 instanceof TransferMarkt) {
			// Transfermarkt is veranderd. Update!
			Kopendata.clear();
			Kopendata.addAll(Main.huidigSpel.getCompetitie().getTransferMarkt().getVerhandelbareSpelers());
		}
	} 
	 



}
