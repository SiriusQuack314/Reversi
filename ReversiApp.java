import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
		
		Button btRegister = new Button("Register New Player");
		Button btSignIn = new Button("Sign In");
		Button btStats = new Button("View Statistics");
		Button btConfig = new Button("Configure Game");
		Button btStart = new Button("Start Game");
		
		VBox pane = new VBox(25); //the amount of vertical space between each child
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(btRegister,btSignIn,btStats,btConfig,btStart);
		
		Scene scene = new Scene(pane, 500, 500);
		
		primaryStage.setTitle("Reversi");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
