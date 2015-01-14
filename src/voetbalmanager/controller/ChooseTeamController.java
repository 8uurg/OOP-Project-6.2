package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import voetbalmanager.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ChooseTeamController implements Initializable, ControlledScreen {
    ScreensController myController = new ScreensController();
    
	public void setScreenParent(ScreensController controller){
		myController = controller;
	}
	@Override
	public void initialize(URL location, ResourceBundle bundel){
		//TODO Code hier	
	}
	
	 @FXML
	   public void handleBack2(ActionEvent event) throws IOException {
		   myController.setScreen(Main.NewGame);
		   /*Parent root = FXMLLoader.load(Main.class.getResource("view/NewGame.fxml"));
			Scene scene = new Scene(root);
			Stage ps = new Stage();
			
			ps.setScene(scene);
			ps.show();
		*/
	    }
	   
	   @FXML
	   public void handleStartManagement(ActionEvent event) throws IOException {
		   myController.setScreen(Main.MainMenu);
		  /* 	Parent root = FXMLLoader.load(Main.class.getResource("view/ManagementMain.fxml"));
			Scene scene = new Scene(root);
			Stage ps = new Stage();
			
			ps.setScene(scene);
			ps.show();
		*/
	    }
}
