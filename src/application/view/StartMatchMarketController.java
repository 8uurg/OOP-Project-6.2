package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartMatchMarketController implements Initializable{

	@FXML
	private Button BackMarket;
	 
	
	@FXML
	public void handleBackMarket() throws IOException{
			Parent root = FXMLLoader.load(MainController.class.getResource("ManagementMain.fxml"));
			Scene scene = new Scene(root,800,600);
			Stage ps = new Stage();
			
			ps.setScene(scene);
			ps.show();
	 } 
	 




   @Override
   public void initialize(URL location, ResourceBundle resources){
	   
   }
}
