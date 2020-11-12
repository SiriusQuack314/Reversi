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

public class SigninUI
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
		static File newFile = new File("reginfo.txt");
		//int count = 0;

	//Creates or detects file used to store registration info.
	public static void createData()
	   {
		   try 
		    {  
		      if (newFile.createNewFile()) 
		      {
		        System.out.println("File created: " + newFile.getName());
		        FileWriter fw = new FileWriter(new File("reginfo.txt"));
		        fw.write("admin 12345\n");
		        fw.close();
		        
		      } 
		      
		      else 
		      {
		        System.out.println("Please enter username and password");
		      }
		    } 
		    
		    
		    catch (IOException e) 
		    {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		   
	   }
	
	//Used to test if the file was being written correctly.
	//Can be removed on final iteration.
	public static void readFile() {
		try {
		      File myObj = new File("reginfo.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	//Reads the first line of the file and returns the username.
	public static String userRead() {
		int n = 0; // The line number
		String line = null;
	      try{
	        line = Files.readAllLines(Paths.get("reginfo.txt")).get(n);
	      } 
	      catch(IOException e){
	        System.out.println(e);
	      }
		return line;
	}
	
	//Reads the second line of the file and returns the password.
	public static String passRead() {
		int n = 1; // The line number
		String line = null;
	      try{
	        line = Files.readAllLines(Paths.get("reginfo.txt")).get(n);
	      } 
	      catch(IOException e){
	        System.out.println(e);
	      }
	      return line;
	      
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
		createData();
		
		// Labels & Text fields
		Label LbUsername = new Label("Username"); // Labels will be on left..
		TextField TxUsername = new TextField();
		TxUsername.setMaxWidth(250);

		Label LbPassword = new Label("Password");
		TextField TxPassword = new TextField();
		TxPassword.setMaxWidth(250);

		// Buttons
		Button btLogin = new Button("Login");
	//	Button btRegister = new Button("Register");
		Button btBack = new Button("Back");

		// Setting actions for buttons
		btBack.setOnAction(e -> (new ReversiApp()).start(primaryStage));
	//	btRegister.setOnAction(e -> (new RegisterUI()).start(primaryStage));
		btLogin.setOnAction(new EventHandler<ActionEvent>() {
			//Handles the login button press based on login info input.
			@Override
		    public void handle(ActionEvent event) {
				try {
				
					
					if(TxUsername.getText().isEmpty()) {
		            showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
		            "Form Error", "Please enter a Username.");
		            readFile();
		            return;
		        }
		        if(TxPassword.getText().isEmpty()) {
		            showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
		            "Form Error", "Please enter a Password.");
		            return;
		        }        	
		        if(!TxUsername.getText().equals(PlayerInfo.userCheck(TxUsername.getText())) || !TxPassword.getText().equals(PlayerInfo.passCheck(TxUsername.getText()))){
		        	showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
				            "Form Error", "Username or Password is incorrect."
				            		);
		        	return;
		        }
		        
				if(Player.count!=2)	
		        if(TxUsername.getText().equals(PlayerInfo.userCheck(TxUsername.getText())) && TxPassword.getText().equals(PlayerInfo.passCheck(TxUsername.getText())))
					{
						
		        	if(TxUsername.getText().equals("admin") && TxPassword.getText().equals(PlayerInfo.passCheck(TxUsername.getText())))
					{
						showAlert(Alert.AlertType.CONFIRMATION, primaryStage.getScene().getWindow(), 
						        "Login Successful!", "Welcome Mighty Admin! The rules of time are yours to play with!");
						
						if(Player.count==0)
						{
							Player.setUsername(TxUsername.getText());
							Player.setLogin();
							Player.setPriority();
							Player.isAdmin=true;
							Player.count++;
							return;
						}
						
						else if(Player.count==1)
						{
							Player2.setUsername(TxUsername.getText());
							Player2.setLogin();
							Player2.isAdmin=true;
							Player.count++;
							return;
						}
						
						else if(Player.count==2)
						{
							showAlert(Alert.AlertType.ERROR,primaryStage.getScene().getWindow(),"Error","There are already 2 players signed in");
							return;
						}
						
					}
		        	
		        	
		        	
		        	showAlert(Alert.AlertType.CONFIRMATION, primaryStage.getScene().getWindow(), 
						        "Login Successful!", "Welcome " + TxUsername.getText());
						
						//This lil bit sets the player's username to his login, marks that he is logged in, and randomly assigns him white or black
						
						if(Player.count==0)
						{
							Player.setUsername(TxUsername.getText());
							Player.setLogin();
							Player.setPriority();
							Player.count++;
							return;
						}
						
						else if(Player.count==1)
						{
							Player2.setUsername(TxUsername.getText());
							Player2.setLogin();
							Player.count++;
							return;
						}
					}
				
		        else if(Player.count==2)
				{
					showAlert(Alert.AlertType.ERROR,primaryStage.getScene().getWindow(),"Error","There are already 2 players signed in");
					return;
				}
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }
		});

		
		
		VBox paneRegister = new VBox(25); // the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(LbUsername, TxUsername, LbPassword, TxPassword, btLogin, btBack);

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
