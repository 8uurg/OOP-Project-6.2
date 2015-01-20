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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import voetbalmanager.Main;
import voetbalmanager.Spel;
import voetbalmanager.model.Team;

public class KlassementController implements Initializable, ControlledScreen, Observer {

	@FXML
	private Button Terug;
	@FXML private final TableView<Team> klassementTabel;
	//@FXML private TableColumn<Integer> RangKolom;
	@FXML private TableColumn<Team, String> teamKolom;
	@FXML private TableColumn<Team, Integer> puntenKolom;
	private ObservableList<Team> teamData = FXCollections.observableArrayList();
	
	ScreensController myController;
	
	public KlassementController(){
		Main.huidigSpel.addObserver(this);
		
		//TODO Los de tabelproblemen op aub. :)
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO init tabel kolommen
		teamKolom.setCellValueFactory(cellData -> cellData.getValue().getNaam());
	    puntenKolom.setCellValueFactory(cellData -> cellData.getValue().getPuntenTotaal());
	     
	     klassementTabel.setItems(teamData);
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
			teamData.addAll(spel.getCompetitie().getTeams());
		}
	}

	

}
