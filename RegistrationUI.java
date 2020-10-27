import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationUI
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage)
	{
		/*
		 * Register Scene
		 * 
		 * Here a user can register as a new player. To register, one enters a screen
		 * name of five letters and digits; and a password of five digits.
		 * 
		 */

		// Labels & Text fields
		Label LbUsername = new Label("Username"); // Labels will be on left..
		TextField TxUsername = new TextField();
		TxUsername.setMaxWidth(250);

		Label LbPassword = new Label("Password");
		TextField TxPassword = new TextField();
		TxPassword.setMaxWidth(250);

		// Buttons
		Button btSubmitReg = new Button("Submit");
		Button btBackReg = new Button("Back");

		// Setting actions for buttons
		btBackReg.setOnAction(e -> (new ReversiApp()).start(primaryStage));

		VBox paneRegister = new VBox(25); // the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(LbUsername, TxUsername, LbPassword, TxPassword, btSubmitReg, btBackReg);

		Scene sceneRegister = new Scene(paneRegister, 500, 500);
		
		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneRegister); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
}
