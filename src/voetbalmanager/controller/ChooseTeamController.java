package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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
import voetbalmanager.Main;
import voetbalmanager.Spel;
import voetbalmanager.model.Team;

public class ChooseTeamController implements Initializable, ControlledScreen, Observer {
    ScreensController myController = new ScreensController();
    
    @FXML
    private ListView<Team> List;
    private ObservableList<Team> listData = FXCollections.observableArrayList();
    @FXML
    private TextArea myTextField;
    @FXML
    private BorderPane borderChooseTeam;
    
    public ChooseTeamController(){
    	Main.huidigSpel.addObserver(this);
    }
    
	public void setScreenParent(ScreensController controller){
		myController = controller;
	}
	@Override
	public void initialize(URL location, ResourceBundle bundel){
		//Schermgrootte
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		borderChooseTeam.setPrefSize(screen.getWidth(), screen.getHeight());;
		//disable editing op de textarea
		myTextField.setDisable(true);
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
			Main.huidigSpel.getCompetitie().setSpelerTeam(newValue);
			
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

	@Override
	public void update(Observable ob, Object data) {
		if(ob instanceof Spel) {
			Spel spel = (Spel) ob;
			listData.clear();
			listData.addAll(spel.getCompetitie().getTeams());
		}
		
	}
	  
}
