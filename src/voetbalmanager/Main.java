package voetbalmanager;


import voetbalmanager.controller.ControlledScreen;
import voetbalmanager.controller.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
<<<<<<< HEAD
	public static final String screen1ID = "MainMenu";
    public static final String screen1File = "MainMenu.fxml";
    
    public static String screen2ID = "NewGame";
    public static final String screen2File = "NewGame.fxml";
    
    public static String screen3ID = "screen3";
    public static final String screen3File = "NewGameNext.fxml";
 
=======
	public static final String screen1ID = "main";
    public static final String MainMenu = "MainMenu.fxml";
    
    public static final String screen2ID = "screen";
    public static final String NewGame = "NewGame.fxml";
    
    public static final String screen3ID = "screen3";
    public static final String NewGameNext = "NewGameNext.fxml";
    
    public static  String screen4ID = "laadScreen";
    public static  String LaadGame = "NewGameNext.fxml";
    
    public static final String screenHelp = "helpScreen";
    public static final String HelpMe= "Help.fxml";
    
    public static final String screenKlass= "klassScreen";
    public static final String Klassement = "Klassement.fxml";
    
    
    
    public static final String ManagmentMain = "ManagmentMain.fxml";
    public static final String Market = "Market.fxml";
    public static final String Opstelling = "Opstelling.fxml";
    public static final String StartMatch = "StartMatch.fxml";
    public static final String Statistieken = "Statistieken.fxml";
    ScreensController mainContainer;
>>>>>>> f0ccb0e6a574fc05ff1a0d5a56e65cc75e8ea997
    
    public ScreensController getController(){
    	return mainContainer;
    }
    @Override
    public void start(Stage primaryStage) {
        //Geef aan de verschillende schermen die er zijn
    	//TODO De rest van de schermen ook functionaliteit zetten net als deze.
    	//TODO Scherm nog kunnen laten resizen.
        
    
    	
    	mainContainer = new ScreensController();
   
    	//plaats de beschikbare schermen "ready to use"
        mainContainer.loadScreen(Main.screen1ID, Main.MainMenu);
        mainContainer.loadScreen(Main.screen2ID, Main.NewGame);
        mainContainer.loadScreen(Main.screen3ID, Main.NewGameNext);
        mainContainer.loadScreen(Main.screen4ID, Main.LaadGame);
        //mainContainer.loadScreen(screenHelp, HelpMe);
        
        mainContainer.setScreen(Main.screen1ID); //Zet de eerste scherm te voorschijn
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
<<<<<<< HEAD
        Scene scene = new Scene(root);
=======
        Scene scene = new Scene(root,800,600);
>>>>>>> f0ccb0e6a574fc05ff1a0d5a56e65cc75e8ea997
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}