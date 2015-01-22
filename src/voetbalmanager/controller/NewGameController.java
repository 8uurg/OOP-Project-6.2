package voetbalmanager.controller;

//import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
public class NewGameController implements Initializable, ControlledScreen {
	@FXML
	TextField TekstVeld;
	@FXML
	Button closeButton;
	@FXML
	private BorderPane newGameNavigator;
	
	ScreensController myController;
	Stage ps;
	

	Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	private double width = screen.getWidth();
	private double height = screen.getHeight();

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		this.newGameNavigator.setPrefSize(width, height);

	}
	
	@FXML
	public void handleBack(ActionEvent event) throws IOException{
		myController.setScreen(Main.MainMenu);
		Main.getStage().setTitle("Main Menu");
	}

	@FXML
	public void handleNext(ActionEvent event) throws IOException{
		
		String naam = TekstVeld.getText();
		Main.huidigSpel.setCompetitie(XMLLoader.creeerCompetitie(naam));
		Main.huidigSpel.getCompetitie().setNaam(naam);
		
		if(naam==null || naam.isEmpty()){
			Parent root = FXMLLoader.load(Main.class.getResource("view/Popup.fxml"));
			Scene my = new Scene(root);
			ps = new Stage();
			ps.setScene(my);
			ps.setTitle("No name entered");
			ps.show();	
		} else {
			myController.setScreen(Main.ChooseTeam);
			Main.getStage().setTitle("Choose a team to start the competition");
		}
	}
	
	@FXML
	public void handlePopup(ActionEvent event){
		Stage stage = (Stage)closeButton.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
