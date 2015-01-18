package voetbalmanager;


import voetbalmanager.controller.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String MainMenu = "main";
    public static final String screen1ID = "MainMenu.fxml";
    
    public static final String NewGame = "screen";
    public static final String screen2ID = "NewGame.fxml";
    
    public static final String ChooseTeam = "screen3";
    public static final String screen3ID = "NewGameNext.fxml";
    
    public static final String LoadGame = "laadScreen";
    public static final String Screen4ID ="LoadGame.fxml";
    
    public static final String HelpMe = "helpScreen";
    public static final String HelpScreen= "Help.fxml";
    
    public static final String screenKlass= "klassScreen";
    public static final String Klassement = "Klassement.fxml";
    
    public static final String ManagmentMain = "ManagmentMain.fxml";
    public static final String Market = "Market.fxml";
    public static final String Opstelling = "Opstelling.fxml";
    public static final String StartMatch = "StartMatch.fxml";
    public static final String Statistieken = "Statistieken.fxml";
    ScreensController mainContainer;
    public static Stage stage = new Stage();
    public static Group root = new Group();
    
    public ScreensController getController(){
    	return mainContainer;
    }
    @Override
    public void start(Stage primaryStage) {
        //Geef aan de verschillende schermen die er zijn
    	//TODO De rest van de schermen ook functionaliteit zetten net als deze.
    	//TODO Scherm nog kunnen laten resizen.
    	Main.stage=primaryStage;
    	mainContainer = new ScreensController();
    	
    	//plaats de beschikbare schermen "ready to use"
        mainContainer.loadScreen(Main.MainMenu, Main.screen1ID);
        mainContainer.loadScreen(Main.NewGame, Main.screen2ID);
        mainContainer.loadScreen(Main.ChooseTeam, Main.screen3ID);
        mainContainer.loadScreen(Main.LoadGame, Main.Screen4ID);
        mainContainer.loadScreen(Main.HelpMe, Main.HelpScreen);
        
        mainContainer.setScreen(Main.MainMenu); //Zet de eerste scherm te voorschijn
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
public static Stage getStage(){
	return stage;
}
    public static void main(String[] args) {
        launch(args);
    }
}