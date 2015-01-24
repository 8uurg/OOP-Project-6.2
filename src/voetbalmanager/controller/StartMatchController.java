package voetbalmanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import voetbalmanager.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class StartMatchController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	@FXML private BorderPane border;
	@FXML private Label Scoor;
	@FXML private Button terug;
	@FXML private Button start;
	@FXML private Button ManagementTerug;
	
	//String uitslag = Main.huidigSpel.getCompetitie().getSpelerWedstrijd().toString();
	
	public StartMatchController(){
	}
	
	public void setScreenParent(ScreensController screens){
		myController = screens;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources){
	  // border.setPrefSize(screen.getWidth(), screen.getHeight());
	  // Scoor.setText(uitslag);
  }

	
	@FXML
	public void handleTerug(ActionEvent event){
		myController.setScreen(Main.ManagementMain);
	}
	
	@FXML
	public void handleStart(ActionEvent event){
		//TODO if statement knop disable
		//Start een speelronde
		Main.huidigSpel.getCompetitie().startSpeelronde();
		myController.setScreen(Main.StartMatch);
	}

	@FXML
	public void handleManagementTerug(){
		myController.setScreen(Main.ManagementMain);
		
	}
}
