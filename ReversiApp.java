import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		
		Label temp1 = new Label("Register Scene");
		
		VBox paneRegister = new VBox(25); //the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(temp1);
		
		sceneRegister = new Scene(paneRegister, 500, 500);
		
		/*
		 * Sign In Scene
		 */
		
		Label temp2 = new Label("Sign In Scene");
		
		VBox paneSignIn = new VBox(25); //the amount of vertical space between each child
		paneSignIn.setAlignment(Pos.CENTER);
		paneSignIn.getChildren().addAll(temp2);
		
		sceneSignIn = new Scene(paneSignIn, 500, 500);
		
		
		/*
		 * Statistics Scene
		 */
		
		Label temp3 = new Label("Statistcs Scene");
		
		VBox paneStats = new VBox(25); //the amount of vertical space between each child
		paneStats.setAlignment(Pos.CENTER);
		paneStats.getChildren().addAll(temp3);
		
		sceneStats = new Scene(paneStats, 500, 500);
		
		
		/*
		 * Configuration Scene
		 */

		Label temp4 = new Label("Configuration Scene");
		
		VBox paneConfig = new VBox(25); //the amount of vertical space between each child
		paneConfig.setAlignment(Pos.CENTER);
		paneConfig.getChildren().addAll(temp4);
		
		sceneConfig = new Scene(paneConfig, 500, 500);
		
		/*
		 * Start Game Scene
		 */
		
		Label temp5 = new Label("Start Game Scene");
		
		VBox paneStart = new VBox(25); //the amount of vertical space between each child
		paneStart.setAlignment(Pos.CENTER);
		paneStart.getChildren().addAll(temp5);
		
		sceneStart = new Scene(paneStart, 500, 500);

		/*
		 * Creates the Initial Scene and places it in the stage
		 */
		
		primaryStage.setTitle("Reversi"); 				//Set the stage title
		primaryStage.setScene(sceneInitial); 					//Place the scene in the stage
		primaryStage.show(); 							//Display the stage
	}

}
