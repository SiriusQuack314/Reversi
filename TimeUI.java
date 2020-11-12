  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TimeUI
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
		
	
	public void start(Stage primaryStage)
	{
		/*
		 * Sign-in Scene
		 * 
		 * Here a user can sign-in as an existing player. To sign-in, one enters their registered
		 * name of five letters and digits; and their password of five digits.
		 * 
		 */
		
		// Labels & Text fields
		Label LbAdmin = new Label("Admin Login");
		
		Label LbUsername = new Label("Username"); // Labels will be on left..
		TextField TxUsername = new TextField();
		TxUsername.setMaxWidth(250);

		Label LbPassword = new Label("Password");
		TextField TxPassword = new TextField();
		TxPassword.setMaxWidth(250);

		// Buttons
		Button btLogin = new Button("Submit");
	//	Button btRegister = new Button("Register");
		Button btBack = new Button("Back");

		// Setting actions for buttons
		btBack.setOnAction(e -> (new ReversiApp()).start(primaryStage));
	//	btRegister.setOnAction(e -> (new RegisterUI()).start(primaryStage));
		btLogin.setOnAction(new EventHandler<ActionEvent>() {
			//Handles the login button press based on login info input.
			@Override
		    public void handle(ActionEvent event) {
				
				if(TxUsername.getText().isEmpty()) {
		            showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
		            "Form Error", "Please enter a Username.");
		            return;
		        }
		        if(TxPassword.getText().isEmpty()) {
		            showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
		            "Form Error", "Please enter a Password.");
		            return;
		        }        	
		        if(!TxUsername.getText().equals("admin") || !TxPassword.getText().equals("12345")){
		        	showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
				            "Form Error", "Username or Password is incorrect."
				            		);
		        	return;
		        }
		        else new ConfigureUI().start(primaryStage);
				
				}
		});


		
		VBox paneRegister = new VBox(25); // the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(LbAdmin, LbUsername, TxUsername, LbPassword, TxPassword, btLogin, btBack);

		Scene sceneRegister = new Scene(paneRegister, 500, 500);
		
		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneRegister); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	//Alert method that allows for alert messages to appear
	private void showAlert(AlertType alertType, Window owner, String title, String message) {
			Alert alert = new Alert(alertType);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.initOwner(owner);
			alert.show();
			
		}
}