import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReversiApp extends Application
{

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage)
	{

		// Buttons
		Button btRegister = new Button("Register/Sign In");
		Button btStats = new Button("View Statistics");
		Button btConfig = new Button("Configure Game");
		Button btGame = new Button("Start Game");

		// Setting actions for buttons
		btRegister.setOnAction(e -> (new RegistrationUI()).start(primaryStage));
		btStats.setOnAction(e -> (new StatisticsUI()).start(primaryStage));
		btConfig.setOnAction(e -> (new ConfigureUI()).start(primaryStage));
		btGame.setOnAction(e -> (new GameUI()).start(primaryStage));

		VBox paneInitial = new VBox(25); // the amount of vertical space between each child
		paneInitial.setAlignment(Pos.CENTER);
		paneInitial.getChildren().addAll(btRegister, btStats, btConfig, btGame);

		Scene sceneInitial = new Scene(paneInitial, 500, 500);

		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneInitial); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

}
