package voetbalmanager.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.stream.StreamResult;

import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Team;

public class KlassementController implements Initializable, ControlledScreen {

	@FXML
	private Button Terug;
	@FXML private final TableView<Team> KlassementTabel;
	//@FXML private TableColumn<Integer> RangKolom;
	@FXML private TableColumn<Team, String> TeamKolom;
	@FXML private TableColumn<Team, Integer> PuntenKolom;
	private ObservableList<Team> TeamData = FXCollections.observableArrayList();
	
	ScreensController myController;
	
	public KlassementController(){
		//TODO Arthur
		//Arraylist van teams in observablelist TeamData zetten
    	Competitie com = new Competitie("blah");
		com = XMLLoader.creeerCompetitie("this");
		for(int i=0; i<18; i++)
		TeamData.add(com.getTeams().get(i));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO init tabel kolommen
		TeamKolom.setCellValueFactory(cellData -> cellData.getValue().getNaam());
	    PuntenKolom.setCellValueFactory(cellData -> cellData.getValue().getPuntenTotaal);
	     
	     KlassementTabel.setItems(TeamData);
	}

	public void setScreenParent(ScreensController screen) {
		myController = screen;
	}

	@FXML
	public void handleTerug(ActionEvent event) throws IOException {
		myController.setScreen(Main.ManagementMain);
	}

	

}
