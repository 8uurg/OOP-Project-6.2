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
import voetbalmanager.model.BeschikbareSpeler;
import voetbalmanager.model.Speler;

public class MarktController implements Initializable, ControlledScreen, Observer{

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
		Main.huidigSpel.addObserver(this);
		
	}
	

	   @Override
	   public void initialize(URL location, ResourceBundle resources){
		   Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		   border.setPrefSize(screen.getWidth(), screen.getHeight());
		   
		  verkopenSpelerId.setDisable(true);
		  kopenSpelerId.setDisable(true);
		   
		   
		   //init verkopenList
		  //TODO Arthur help
		   Verkopendata.addAll(Main.huidigSpel.getCompetitie().getSpelerTeam().getSelectie());
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
				verkopenSpelerId.appendText(newValue.toString());
			});
		   
		   
		   //init kopenList
			//TODO Arthur help
			Kopendata.addAll(Main.huidigSpel.getCompetitie().getTransferMarkt().getVerhandelbareSpelers());
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
				kopenSpelerId.appendText(newValue.getSpeler().toString());
			});
		   
	   }
	   
	
	public void setScreenParent(ScreensController screenParent){
	       myController = screenParent;
	   }
	   
	
	@FXML
	public void handleKopen() throws IOException{
		
		kopenSpeler.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			Main.huidigSpel.getCompetitie().getTransferMarkt().koopSpeler(Main.huidigSpel.getCompetitie().getSpelerTeam(), newValue);
		});
		
	}
	
	@FXML
	public void handleVerkopen() throws IOException{
		
		verkopenSpeler.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			Main.huidigSpel.getCompetitie().getTransferMarkt().maakVerhandelbaar(newValue);
		});
	}
	
	@FXML
	public void handleBackMarket() throws IOException{
	myController.setScreen(Main.ManagementMain);
	Main.getStage().setTitle("Main Managment");
	 }


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	} 
	 



}
