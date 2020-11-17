/*
 * Class created by Mert Saritac
 */

import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigureUI
{
	
	public static int timeLimit= 15;
	private ObservableList<String> times = FXCollections.observableArrayList("1", "5", "10", "15", "30", "60"); 
	private ComboBox<String> ComTimeLimit = new ComboBox<String>(times);

	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage)
	{
		/*
		 * Configuration Scene
		 * 
		 * Must be signed in as the System Administrator to configure certain
		 * parameters.
		 * 
		 * Abilities include: -Setting time limit
		 * 
		 */

		Label LbSetTime = new Label("Set The Time Limit");
		ComTimeLimit.setValue(Integer.toString(timeLimit));
		// Buttons
		Button btSubmitTime = new Button("Submit");
		Button btBackConfig = new Button("Back");

		// Setting actions for buttons
		btBackConfig.setOnAction(e -> (new ReversiApp()).start(primaryStage));
		btSubmitTime.setOnAction(e -> SubmitTime(primaryStage));

		VBox paneConfig = new VBox(25); // the amount of vertical space between each child
		paneConfig.setAlignment(Pos.CENTER);
		paneConfig.getChildren().addAll(LbSetTime, ComTimeLimit, btSubmitTime, btBackConfig);

		Scene sceneConfig = new Scene(paneConfig, 500, 500);
		
		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneConfig); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public void SubmitTime(Stage primaryStage)
	{
		timeLimit = Integer.parseInt(ComTimeLimit.getValue().trim());
		Alert a1 = new Alert(AlertType.NONE,"Time Has Set To "+timeLimit, ButtonType.OK);
		Optional<ButtonType> result = a1.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			(new ReversiApp()).start(primaryStage);
		}
	}
}
