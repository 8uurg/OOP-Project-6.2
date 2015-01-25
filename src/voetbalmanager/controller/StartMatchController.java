package voetbalmanager.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import voetbalmanager.Main;
import voetbalmanager.Spel;
import voetbalmanager.model.Competitie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StartMatchController implements Initializable, ControlledScreen, Observer {
	
	
	@FXML private BorderPane border;
	@FXML private Label Scoor;
	@FXML private Button terug;
	@FXML private Button start;
	@FXML private Button ManagementTerug;
	@FXML private TextArea Ronde;
	

	ScreensController myController;
	Stage stage = new Stage();
	Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	
	public StartMatchController(){
		Main.huidigSpel.addObserver(this);
	}
	
	public void setScreenParent(ScreensController screens){
		myController = screens;
		border.setPrefSize(screen.getWidth(), screen.getHeight());
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources){ 
		
	}

	
	@FXML
	public void handleTerug(ActionEvent event){
		myController.setScreen(Main.ManagementMain);
	}
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Spel) {
			if(Main.huidigSpel.getCompetitie()!=null)
				update(Main.huidigSpel.getCompetitie(), arg1);


			Main.huidigSpel.getCompetitie().addObserver(this);
		}
		if(arg0 instanceof Competitie) {
			int week = Main.huidigSpel.getCompetitie().getWeek();
			
			Ronde.appendText(Main.huidigSpel.getCompetitie().getSchema().getSchema().get(week-1).toString() + Main.huidigSpel.getCompetitie().huidigeResultaten().toString());
		}
	}
}
