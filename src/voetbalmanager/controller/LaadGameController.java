package voetbalmanager.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

import org.xml.sax.InputSource;

import voetbalmanager.Main;
import voetbalmanager.XMLLoader;

public class LaadGameController implements Initializable, ControlledScreen{

   @FXML private Button Terug;
   @FXML private Button Doorgaan;
   @FXML private ListView<File> laadgame;
   private ObservableList<File> listData = FXCollections.observableArrayList();
   @FXML private BorderPane border;
   private File filetoload;
   
   ScreensController myController;

   public void setScreenParent(ScreensController screenParent){
       myController = screenParent;
   }
   
   public void initialize(URL location, ResourceBundle resources){
	   Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	   border.setPrefSize(screen.getWidth(), screen.getHeight());
	   
	   //listview
	   listData.addAll((new File("saves/")).listFiles());
	   laadgame.setItems(listData);
	   Doorgaan.setDisable(true);
	   laadgame.setCellFactory((list) -> {return new ListCell<File>(){
			@Override
			protected void updateItem(File item,boolean empty){
				super.updateItem(item, empty);
				if(item==null || empty){
					setText(null);
				}else{
					String name = item.getName();
					if(name.endsWith(".xml"))
						name = name.substring(0, name.length()-4);
					setText(name);
				}
			}
		};});
	   laadgame.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			filetoload = newValue;
			Doorgaan.setDisable(newValue==null);
		});
	   
   }
   
   @FXML
   public void handleLaadTerug(ActionEvent event)throws IOException{
	   myController.setScreen(Main.MainMenu);
   }
   @FXML
   public void handleDoorgaan(ActionEvent event)throws IOException{
	   Main.huidigSpel.setCompetitie(XMLLoader.laadCompetitie(new InputSource(new InputStreamReader(new FileInputStream(filetoload)))));
	   
	   myController.setScreen(Main.ManagementMain);   
   }
}