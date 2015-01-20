package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import voetbalmanager.XMLLoader;
import voetbalmanager.Main;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Team;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class ChooseTeamController implements Initializable, ControlledScreen {
    ScreensController myController = new ScreensController();
    
    @FXML
    private ListView<Team> List;
    private ObservableList<Team> listData = FXCollections.observableArrayList();
    @FXML
    private TextArea myTextField;
    @FXML
    private BorderPane borderChooseTeam;
    
    public ChooseTeamController(){
    	//TODO Arthur
    	Competitie com = new Competitie("blah");
		com = XMLLoader.creeerCompetitie("this");
		for(int i=0; i<18; i++)
		listData.add(com.getTeams().get(i));
    }
    
	public void setScreenParent(ScreensController controller){
		myController = controller;
	}
	@Override
	public void initialize(URL location, ResourceBundle bundel){
		//Schergrootte
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		borderChooseTeam.setPrefSize(screen.getWidth(), screen.getHeight());;
		//init listview
		List.setItems(listData);
		List.setCellFactory((list) -> {return new ListCell<Team>(){
			@Override
			protected void updateItem(Team item,boolean empty){
				super.updateItem(item, empty);
				if(item==null || empty){
					setText(null);
				}else{
					setText(item.getNaam());
				}
			}
		};
	});
		// handle listview
		List.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			myTextField.clear();
			myTextField.appendText(newValue.getSpelerNamen());
			Main.huidigeCompetitie.setSpelerTeam(newValue);
			//System.out.println(newValue.isSpelerBestuurd());
			//TODO Arthur
			
		});
	}
	
	 @FXML
	   public void handleBack2(ActionEvent event) throws IOException {
		   myController.setScreen(Main.NewGame);
	    }
	   
	   @FXML
	   public void handleStartManagement(ActionEvent event) throws IOException {
		   myController.setScreen(Main.ManagementMain);
		   
		   
	    }
	  
}
