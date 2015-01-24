package voetbalmanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import voetbalmanager.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class StartMatchController implements Initializable, ControlledScreen {
	ScreensController myController;
	Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	@FXML private BorderPane border;
	@FXML private Label Scoor;
	//String uitslag = Main.huidigSpel.getCompetitie().getSpelerWedstrijd().toString();
	
	public void StartMatchController(){
	}
	public void setScreenParent(ScreensController screens){
		myController = screens;
	}
	
	@FXML
	public void handleExit(ActionEvent event){
		myController.setScreen(Main.ManagementMain);
	}
	 @Override
	   public void initialize(URL location, ResourceBundle resources){
		   border.setPrefSize(screen.getWidth(), screen.getHeight());
		  // Scoor.setText(uitslag);
	   }


}
