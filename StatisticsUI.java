import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;

public class StatisticsUI
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws FileNotFoundException
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
		ObservableList<String> players = FXCollections.observableArrayList();
		
		//Scans the stats file, adds players to the combo box
		try {
		Scanner fs = new Scanner(new File("stats.txt"));
		String check = "";
		String [] check1 = new String[3];
		while(fs.hasNextLine())
		{
			check=fs.nextLine();
			check1=check.split(" ");
			players.add(check1[0]);
		}
		

		ComboBox ComPlayers = new ComboBox(players);

		int numberOfWins [] = new int[] {0};
		int numberOfLosses [] = new int[] {0};

		Label TxWins = new Label("Wins : " + numberOfWins[0]);
		Label TxLoses = new Label("Loses : " + numberOfLosses[0]);
		
		
		//Displays a players wins and losses when clicked on
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() 
		{ 
          public void handle(ActionEvent e) 
          { 
              
        	  try 
        	  {
				numberOfWins[0] = StatisticsInfo.getWins((String)ComPlayers.getValue());
				numberOfLosses[0] = StatisticsInfo.getLosses((String)ComPlayers.getValue());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	  TxWins.setText("Wins: "+numberOfWins[0]);
        	  TxLoses.setText("Losses: "+numberOfLosses[0]);
        	  
          } 
      }; 
      
      ComPlayers.setOnAction(event);
      

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
		catch(IOException E)
		{
			showAlert(Alert.AlertType.ERROR,primaryStage.getScene().getWindow(),"Error","There are no players registered yet");
		}
}

	private void showAlert(AlertType alertType, Window owner, String title, String message) 
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		
	}
}
