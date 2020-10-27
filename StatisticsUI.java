import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatisticsUI
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage)
	{

		/*
		 * Statistics Scene
		 * 
		 * The system will keep records of wins and losses of players. This is where
		 * guests and players can view records of wins/losses of individual games or
		 * players.
		 * 
		 */

		Label SelectPl = new Label("Select player to view statistics:");
		ObservableList<String> players = FXCollections.observableArrayList("Player 1", "Player 2", "Player 3"); 

		ComboBox ComPlayers = new ComboBox(players);

		int numberOfWins = 0;
		int numberOfLosses = 0;

		Label TxWins = new Label("Wins : " + numberOfWins);
		Label TxLoses = new Label("Loses : " + numberOfLosses);

		// Buttons
		Button btBackStat = new Button("Back");

		// Setting actions for buttons
		btBackStat.setOnAction(e -> (new ReversiApp()).start(primaryStage));

		VBox paneStats = new VBox(25); // the amount of vertical space between each child
		paneStats.setAlignment(Pos.CENTER);
		paneStats.getChildren().addAll(SelectPl, ComPlayers, TxWins, TxLoses, btBackStat);

		Scene sceneStats = new Scene(paneStats, 500, 500);
		
		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneStats); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
}
