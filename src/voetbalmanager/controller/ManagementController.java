package voetbalmanager.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;





import javax.xml.transform.stream.StreamResult;

import voetbalmanager.Main;
import voetbalmanager.XMLWriter;
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
	@FXML
	private Button closeButton;
	
	ScreensController myController;
	Stage stage = new Stage();
	
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
	 Parent root = FXMLLoader.load(Main.class.getResource("view/LoadPopup.fxml"));
	 Scene scene = new Scene(root);
	 stage.setScene(scene);
	 stage.setTitle("Save succesfull");
	 stage.show();
		XMLWriter.saveCompetitie(Main.huidigeCompetitie);
		
	}

	@FXML
	public void handleExit() throws IOException {
		myController.setScreen(Main.MainMenu);
		//TODO popup met 'heeft u het spel opgeslagen?'
	}
	@FXML
	public void handleLoadPopup(){
		Stage st = (Stage)closeButton.getScene().getWindow();
		st.close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Code hier
	}
}