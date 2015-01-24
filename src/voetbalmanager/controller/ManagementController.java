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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voetbalmanager.Main;
import voetbalmanager.Spel;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speelronde;
import voetbalmanager.model.Speelschema;
//import voetbalmanager.Main;
import voetbalmanager.model.Team;
import voetbalmanager.model.Wedstrijd;

public class ManagementController implements Initializable, ControlledScreen,
		Observer {

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
	@FXML
	private Button oplaan;
	@FXML
	private Button terug;
	@FXML
	private TextArea Budget;
	@FXML
	private TableView<Speelschema> wedstrijdSchema;
	@FXML private TableColumn<Speelschema, String> Club;
	@FXML private TableColumn<Speelschema, String> tegenClub;

	private ObservableList<Speelschema> schemaData = FXCollections
			.observableArrayList();
	
	ScreensController myController;
	Stage stage = new Stage();
	Rectangle2D screen = Screen.getPrimary().getVisualBounds();

	public ManagementController() {
		Main.huidigSpel.addObserver(this);
		//TODO schema in schemaData laden
		//schemaData.addAll(Main.huidigSpel.getCompetitie().)

	}

	public void setScreenParent(ScreensController screens) {
		myController = screens;
		border.setPrefSize(screen.getWidth(), screen.getHeight());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// disable editing op de textarea
		Budget.setDisable(true);

		// TODO speelschema inladen
		Club.setCellValueFactory(
			    new PropertyValueFactory<>("team")
			);
		tegenClub.setCellValueFactory(
			    new PropertyValueFactory<>("tegenteam")
			);
		
		
	
	}

	@FXML
	public void handleStartMatch() throws IOException {
		//TODO if statement knop disable
		//Main.huidigSpel.getCompetitie().maakSpeelSchema();
		Main.huidigSpel.getCompetitie().startSpeelronde();
		myController.setScreen(Main.StartMatch);
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
		/*Parent root = FXMLLoader.load(Main.class
				.getResource("view/LoadPopup.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Save succesfull");
		stage.show();*/
	}

	@FXML
	public void handleExit() throws IOException {
		myController.setScreen(Main.MainMenu);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Spel) {
			if(Main.huidigSpel.getCompetitie()!=null)
				update(Main.huidigSpel.getCompetitie(), arg1);
			
			wedstrijdSchema.setItems(schemaData);
			Main.huidigSpel.getCompetitie().addObserver(this);
		}
		if(arg0 instanceof Competitie) {
			if(Main.huidigSpel.getCompetitie().getSpelerTeam()!=null) {
				Main.huidigSpel.getCompetitie().getSpelerTeam().addObserver(this);
				update(Main.huidigSpel.getCompetitie().getSpelerTeam(), arg1);
			}
		}
		if(arg0 instanceof Team) {
			Budget.setText("Budget: " + Integer.toString(Main.huidigSpel.getCompetitie().getSpelerTeam().getBudget()));
		}
	}

}