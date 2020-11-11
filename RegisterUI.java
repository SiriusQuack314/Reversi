import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterUI
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	//Method that clears the file if you try and register a new username and password.
	public static void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter("reginfo.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
	
	//Creates text file that stores register info.
	public static void createData()
	   {
		   try 
		    {
		      File nf = new File("reginfo.txt");
		      if (nf.createNewFile()) 
		      {
		        System.out.println("File created: " + nf.getName());
		      } 
		      
		      else 
		      {
		        System.out.println("User Info Found");
		      }
		    } 
		    
		    
		    catch (IOException e) 
		    {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		   
	   }
	
	//Writes the username to the file and goes to the next line.
	public static void userWrite(String u) throws IOException
	{
		Scanner sc = new Scanner(new File("reginfo.txt"));
		FileWriter fw = new FileWriter("reginfo.txt", true);
		
		while(sc.hasNextLine())
		{
			sc.nextLine();	
		}
		fw.write(u+"\n");		
		fw.close();
	}
	
	//Writes the password to the file.
	public static void passWrite(String p) throws IOException
	{
		Scanner sc = new Scanner(new File("reginfo.txt"));
		FileWriter fw = new FileWriter("reginfo.txt", true);
		
		while(sc.hasNextLine())
		{
			sc.nextLine();	
		}
		fw.write(p+"\n");
		fw.close();
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
		createData();
		
		// Labels & Text fields
		Label LbUsername = new Label("Choose a Username"); // Labels will be on left..
		TextField TxUsername = new TextField();
		TxUsername.setMaxWidth(250);

		Label LbPassword = new Label("Choose a Password");
		TextField TxPassword = new TextField();
		TxPassword.setMaxWidth(250);

		// Buttons
		Button btSubmitReg = new Button("Register");
		Button btBackReg = new Button("Back");

		// Setting actions for buttons
		btBackReg.setOnAction(e -> (new SigninUI()).start(primaryStage));
		btSubmitReg.setOnAction(new EventHandler<ActionEvent>() {
			//Handles the registration inputs
			@Override
		    public void handle(ActionEvent event) {
		        if(TxUsername.getText().isEmpty()) {
		            showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
		            "Form Error", "Please enter a Username");
		            return;
		        }
		        if(TxPassword.getText().isEmpty()) {
		            showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
		            "Form Error", "Please enter a Password");
		            return;
		        }
		        if(TxUsername.getText().length() != 5) {
		        	showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
				            "Form Error", "Username must be 5 characters");
				            return;
		        }
		        if(TxPassword.getText().length() != 5) {
		        	showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
				            "Form Error", "Password must be 5 numbers");
				            return;
		        }
		        if(!TxUsername.getText().isEmpty() && !TxPassword.getText().isEmpty()){		        	
		        	try {
		        		clearTheFile();
						userWrite(TxUsername.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	try {
						passWrite(TxPassword.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	showAlert(Alert.AlertType.CONFIRMATION, primaryStage.getScene().getWindow(), 
		    		        "Registration Successful!", "Welcome " + TxUsername.getText());
		        	try {
						if(StatisticsInfo.find(TxUsername.getText())==false)
						{
						try 
						{
							StatisticsInfo.write(TxUsername.getText());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	return;
		        }
		        else
		        {
		        	showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), 
				    "Form Error", "Your username or password is incorrect");
				    return;	
		        }
		    }
		});
		
		VBox paneRegister = new VBox(25); // the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(LbUsername, TxUsername, LbPassword, TxPassword, btSubmitReg, btBackReg);

		Scene sceneRegister = new Scene(paneRegister, 500, 500);
		
		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneRegister); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	//Same alert method from SigninUI
	private void showAlert(AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		
	}
}
