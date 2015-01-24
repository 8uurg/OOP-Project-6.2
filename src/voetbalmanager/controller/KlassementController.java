package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;
import voetbalmanager.Spel;
import voetbalmanager.model.Team;

public class KlassementController implements Initializable, ControlledScreen, Observer {

	@FXML
	private Button Terug;
	@FXML private TableView<Team> klassementTable;
	//TODO nummers 1 t/m 18 in de rangkolom
	//@FXML private TableColumn<Integer> RangKolom;
	@FXML private TableColumn<Team, String> TeamKolom;
	@FXML private TableColumn<Team, Integer> PuntenKolom;
	@FXML private TableColumn<Team, Integer> GewonnenKolom;
	@FXML private TableColumn<Team, Integer> GelijkKolom;
	@FXML private TableColumn<Team, Integer> VerlorenKolom;
	@FXML private TableColumn<Team, Integer> DoelsaldoKolom;
	@FXML private TableColumn<Team, Integer> DoelTegenKolom;
	@FXML private BorderPane border;
	
	private ObservableList<Team> teamData = FXCollections.observableArrayList();
	ScreensController myController;
	Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	
	
	public KlassementController(){
		Main.huidigSpel.addObserver(this);	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO puntentotaal en tegendoelpunten worden niet ingeladen
		//waarden aan de tabelkolommen toewijzen, via klasse KlassementTabel
		border.setPrefSize(screen.getWidth(), screen.getHeight());
		TeamKolom.setCellValueFactory(
						new PropertyValueFactory<>("naam")
				);
		PuntenKolom.setCellValueFactory(
				new PropertyValueFactory<>("puntentotaal")
		);
		GewonnenKolom.setCellValueFactory(
				new PropertyValueFactory<>("gewonnen")
		);
		GelijkKolom.setCellValueFactory(
				new PropertyValueFactory<>("gelijk")
		);
		VerlorenKolom.setCellValueFactory(
				new PropertyValueFactory<>("verloren")
		);
		DoelsaldoKolom.setCellValueFactory(
				new PropertyValueFactory<>("doelsaldo")
		);
		DoelTegenKolom.setCellValueFactory(
				new PropertyValueFactory<>("tegendoel")
		);
		//klassementTable.setItems(teamData);
		
	}

	public void setScreenParent(ScreensController screen) {
		myController = screen;
	}

	@FXML
	public void handleTerug(ActionEvent event) throws IOException {
		myController.setScreen(Main.ManagementMain);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Spel) {
			Spel spel = (Spel) o;
			Main.huidigSpel.getCompetitie().Sorteren("Punten");
			teamData.addAll(spel.getCompetitie().getTeams());
			klassementTable.setItems(teamData);
		}
	}

	

}
