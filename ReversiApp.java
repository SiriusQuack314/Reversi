import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReversiApp extends Application
{

	Scene sceneInitial, sceneRegister, sceneSignIn, sceneStats, sceneConfig, sceneStart;
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	public void start(Stage primaryStage)
	{
		/*
		 * Initial Screen
		 */
		
		Button btRegister = new Button("Register New Player");
		Button btSignIn = new Button("Sign In");
		Button btStats = new Button("View Statistics");
		Button btConfig = new Button("Configure Game");
		Button btStart = new Button("Start Game");
		
		
		btRegister.setOnAction(e -> primaryStage.setScene(sceneRegister));
		btSignIn.setOnAction(e -> primaryStage.setScene(sceneSignIn));
		btStats.setOnAction(e -> primaryStage.setScene(sceneStats));
		btConfig.setOnAction(e -> primaryStage.setScene(sceneConfig));
		btStart.setOnAction(e -> primaryStage.setScene(sceneStart));

		
		VBox paneInitial = new VBox(25); //the amount of vertical space between each child
		paneInitial.setAlignment(Pos.CENTER);
		paneInitial.getChildren().addAll(btRegister,btSignIn,btStats,btConfig,btStart);
		
		sceneInitial = new Scene(paneInitial, 500, 500);
		
		/*
		 * Register Scene
		 */
		
		Label LbUsername = new Label("Username");		//Labels will be on left..
		TextField TxUsername = new TextField ();
        TxUsername.setMaxWidth(250);
		Label LbPassword = new Label("Password");
		TextField TxPassword = new TextField ();
        TxPassword.setMaxWidth(250);
		Button btSubmitReg = new Button("Submit");
		Button btBack = new Button("Back");
		btBack.setOnAction(e -> primaryStage.setScene(sceneInitial));


		VBox paneRegister = new VBox(25); //the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(LbUsername,TxUsername,LbPassword,TxPassword,btSubmitReg,btBack);
		
		
		sceneRegister = new Scene(paneRegister, 500, 500);
		
		/*
		 * Sign In Scene
		 */
		
		Label LbUsernameS = new Label("Username");		//Labels will be on left..
		TextField TxUsernameS = new TextField ();
        TxUsernameS.setMaxWidth(250);
		Label LbPasswordS = new Label("Password");
		TextField TxPasswordS = new TextField ();
        TxPasswordS.setMaxWidth(250);
		Button btSubmitSign = new Button("Submit");
		Button btBackS = new Button("Back");
		btBackS.setOnAction(e -> primaryStage.setScene(sceneInitial));
		
		
		VBox paneSignIn = new VBox(25); //the amount of vertical space between each child
		paneSignIn.setAlignment(Pos.CENTER);
		paneSignIn.getChildren().addAll(LbUsernameS,TxUsernameS,LbPasswordS,TxPasswordS,btSubmitSign,btBackS);
		
		sceneSignIn = new Scene(paneSignIn, 500, 500);
		
		
		/*
		 * Statistics Scene
		 */
		Label SelectPl = new Label("Select A Player To View Statistic ");
		ObservableList<String> players = 				//It's Going to Take From File
			    FXCollections.observableArrayList(
			        "Player 1",
			        "Player 2",
			        "Player 3"
			    );
		ComboBox ComPlayers = new ComboBox(players);
		Label TxWins = new Label("Wins : ");
		Label WinNumber = new Label("0");
		Label TxLoses = new Label("Loses : ");
		Label LoseNumber = new Label("0");
		Button btBackSt = new Button("Back");
		btBackSt.setOnAction(e -> primaryStage.setScene(sceneInitial));

		VBox paneStats = new VBox(25); //the amount of vertical space between each child
		paneStats.setAlignment(Pos.CENTER);
		paneStats.getChildren().addAll(SelectPl,ComPlayers,TxWins,WinNumber,TxLoses,LoseNumber,btBackSt);
		
		sceneStats = new Scene(paneStats, 500, 500);
		
		
		/*
		 * Configuration Scene
		 */

		Label LbSetTime = new Label("Set The Time Limit");
		ObservableList<String> times = 				//Fixed Times For Limiting The Time
			    FXCollections.observableArrayList(
			        "15",
			        "30",
			        "60"
			    );
		ComboBox ComTimeLimit = new ComboBox(times);
		Button btSubmitTime = new Button("Submit");
		Button btBackco = new Button("Back");
		btBackco.setOnAction(e -> primaryStage.setScene(sceneInitial));
		
		VBox paneConfig = new VBox(25); //the amount of vertical space between each child
		paneConfig.setAlignment(Pos.CENTER);
		paneConfig.getChildren().addAll(LbSetTime,ComTimeLimit,btSubmitTime,btBackco);
		
		sceneConfig = new Scene(paneConfig, 500, 500);
		
		/*
		 * Start Game Scene
		 */
		
		Label temp5 = new Label("Start Game Scene");
		Button btBackstr = new Button("Back");
		btBackstr.setOnAction(e -> primaryStage.setScene(sceneInitial));
		
		VBox paneStart = new VBox(25); //the amount of vertical space between each child
		paneStart.setAlignment(Pos.CENTER);
		paneStart.getChildren().addAll(temp5,btBackstr);
		
		sceneStart = new Scene(paneStart, 500, 500);

		/*
		 * Creates the Initial Scene and places it in the stage
		 */
		
		primaryStage.setTitle("Reversi"); 				//Set the stage title
		primaryStage.setScene(sceneInitial); 					//Place the scene in the stage
		primaryStage.show(); 							//Display the stage
	}

}

