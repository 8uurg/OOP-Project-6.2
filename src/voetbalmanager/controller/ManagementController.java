package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import voetbalmanager.Main;
//import voetbalmanager.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagementController implements Initializable, ControlledScreen {

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
	
	ScreensController myController;
	
	public void setScreenParent(ScreensController screens){
		myController = screens;
	}
	
	@FXML
	public void handleStartMatch() throws IOException{
		myController.setScreen(Main.MainMenu);
		//todo linken naar start wedstrijd simulatie
	}

	@FXML
	public void handleOpstelling() throws IOException{
		myController.setScreen(Main.Opstelling);
	}

	@FXML
	public void handleSpelersmarkt() throws IOException {
		myController.setScreen(Main.Market);
	}

	@FXML
	public void handleKlassement() throws IOException{
		myController.setScreen(Main.Klassement);
	}

	@FXML
	public void handleStatistieken() throws IOException{
		myController.setScreen(Main.Statistieken);
	}

	@FXML
	public void handleOpslaan()throws IOException {
		myController.setScreen(Main.MainMenu);
		//TODO game opslaan en schrijven naar xml
		//TODO popup geven met opslaan is gelukt (of niet)
	}

	@FXML
	public void handleExit() throws IOException {
		myController.setScreen(Main.MainMenu);
		//TODO popup met 'heeft u het spel opgeslagen?'
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Code hier
	}
}