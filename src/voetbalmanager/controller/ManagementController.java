package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voetbalmanager.Main;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Speelschema;
//import voetbalmanager.Main;
import voetbalmanager.model.Team;

public class ManagementController implements Initializable, ControlledScreen, Observer {

	@FXML
	private Button StartMatch;
	@FXML
	private Button Opstelling;
	@FXML
	private Button Spelersmarkt;
	@FXML
	private Button Klassement;
	@FXML
	private Button Statistieken;
	@FXML
	private Button Opslaan;
	@FXML
	private Button Exit;
	@FXML
	private Button closeButton;
	@FXML 
	private BorderPane border;
	@FXML private TextArea Budget;
	@FXML private TableView<Speelschema> wedstrijdSchema;
	private ObservableList<Speelschema> schemaData = FXCollections.observableArrayList();
	

	ScreensController myController;
	Stage stage = new Stage();

	

	public ManagementController(){
		Main.huidigSpel.addObserver(this);
		
	}
	
	
	public void setScreenParent(ScreensController screens) {
		myController = screens;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		border.setPrefSize(screen.getWidth(), screen.getHeight());
		
		//disable editing op de textarea
		Budget.setDisable(true);
		
		//TODO speelschema inladen
	}

	@FXML
	public void handleStartMatch() throws IOException {
		myController.setScreen(Main.MainMenu);
		// todo linken naar start wedstrijd simulatie
	}

	@FXML
	public void handleOpstelling() throws IOException {
		myController.setScreen(Main.Opstelling);
	}

	@FXML
	public void handleSpelersmarkt() throws IOException {
		myController.setScreen(Main.Market);
	}

	@FXML
	public void handleKlassement() throws IOException {
		myController.setScreen(Main.Klassement);
	}

	@FXML
	public void handleStatistieken() throws IOException {
		myController.setScreen(Main.Statistieken);
	}

	@FXML
	public void handleOpslaan() throws IOException {
		XMLWriter.saveCompetitie(Main.huidigSpel.getCompetitie());
		Parent root = FXMLLoader.load(Main.class
				.getResource("view/LoadPopup.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Save succesfull");
		stage.show();
	}

	@FXML
	public void handleExit() throws IOException {
		myController.setScreen(Main.MainMenu);
		// TODO popup met 'heeft u het spel opgeslagen?'
	}

	@FXML
	public void handleLoadPopup() {
		Stage st = (Stage) closeButton.getScene().getWindow();
		st.close();
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
}