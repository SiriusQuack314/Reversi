import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameUI
{
	Game game;
	public static Stage primaryStage;

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage)
	{
		/*
		 * Starting a new game
		 */
		
		GameUI.primaryStage = primaryStage;
		
		game = new Game((new Player()), new Player()); // Change this once sign in is complete

		//GameUI.primaryStage = primaryStage;
		
		refresh(primaryStage);

	}

	public static FlowPane showPlayers()
	{
		FlowPane panePlayers = new FlowPane();
		panePlayers.setAlignment(Pos.CENTER);
		panePlayers.setPadding(new Insets(20, 20, 20, 20));
		panePlayers.setHgap(20);

		Label LbPlayer1 = new Label("Player1"); // temp name
		Label LbPlayer2 = new Label("Player2"); // temp name

		Label LbPlayer1Score = new Label("" + Game.blackScore); // temp var
		Label LbPlayer2Score = new Label("" + Game.whiteScore); // temp var

		if(Game.isBlacksTurn)
		{
			LbPlayer1.setStyle("-fx-border-color: red; -fx-border-width: 4");
		}
		else
		{
			LbPlayer2.setStyle("-fx-border-color: red; -fx-border-width: 4");
		}
		
		Circle tokenBlack = new Circle();
		tokenBlack.setRadius(15);
		tokenBlack.setFill(Color.BLACK);

		Circle tokenWhite = new Circle();
		tokenWhite.setRadius(15);
		tokenWhite.setStroke(Color.BLACK);
		tokenWhite.setFill(Color.WHITE);

		// This token is for spacing purposes
		Circle tokenInvis1 = new Circle();
		tokenInvis1.setRadius(15);
		tokenInvis1.setStroke(Color.TRANSPARENT);
		tokenInvis1.setFill(Color.TRANSPARENT);

		// This token is for spacing purposes
		Circle tokenInvis2 = new Circle();
		tokenInvis2.setRadius(15);
		tokenInvis2.setStroke(Color.TRANSPARENT);
		tokenInvis2.setFill(Color.TRANSPARENT);

		// This token is for spacing purposes
		Circle tokenInvis3 = new Circle();
		tokenInvis3.setRadius(15);
		tokenInvis3.setStroke(Color.TRANSPARENT);
		tokenInvis3.setFill(Color.TRANSPARENT);

		LbPlayer1.setFont(new Font(20));
		LbPlayer2.setFont(new Font(20));
		LbPlayer1Score.setFont(new Font(20));
		LbPlayer2Score.setFont(new Font(20));

		panePlayers.getChildren().addAll(LbPlayer1, tokenInvis1, LbPlayer1Score, tokenBlack, tokenInvis2, tokenWhite,
				LbPlayer2Score, tokenInvis3, LbPlayer2);

		return panePlayers;
	}
	
	private static FlowPane showTimers() {
		FlowPane paneTimer = new FlowPane();
		paneTimer.setAlignment(Pos.CENTER);
		paneTimer.setPadding(new Insets(20, 20, 20, 20));
		paneTimer.setHgap(20);

		Label LbPlayer1 = new Label("Player1 Time"); // temp name
		Label LbPlayer2 = new Label("Player2 Time"); // temp name

		Label LbPlayer1Score = new Label("" + Game.blackScore); // temp var
		Label LbPlayer2Score = new Label("" + Game.whiteScore); // temp var
		
		paneTimer.getChildren().addAll(LbPlayer1, LbPlayer1Score, LbPlayer2, LbPlayer2Score);
		
		
		return paneTimer;
	}

	public static GridPane showBoard()
	{
		GridPane paneBoard = new GridPane();
		paneBoard.setAlignment(Pos.CENTER);
		paneBoard.setGridLinesVisible(true);

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				paneBoard.add(Board.board[i][j], j, i);

				if (Board.board[i][j].getToken() == 'B')
				{
					Circle tokenBlack = new Circle();
					tokenBlack.setRadius(25);
					tokenBlack.setStroke(Color.BLACK);
					tokenBlack.setFill(Color.BLACK);
					paneBoard.add(tokenBlack, j, i);
				} else if (Board.board[i][j].getToken() == 'W')
				{
					Circle tokenWhite = new Circle();
					tokenWhite.setRadius(25);
					tokenWhite.setStroke(Color.BLACK);
					tokenWhite.setFill(Color.WHITE);
					paneBoard.add(tokenWhite, j, i);
				} else if (Board.board[i][j].getToken() == ' ')
				{
					Rectangle tokenBlank = new Rectangle();
					tokenBlank.setWidth(50);
					tokenBlank.setHeight(50);
					tokenBlank.setStroke(Color.BLACK);
					tokenBlank.setFill(Color.TRANSPARENT);
					paneBoard.add(tokenBlank, j, i);
				}
				
			}
		}

		return paneBoard;
	}

	/*
	 * This method refreshes the display of the board
	 */
	public static void refresh(Stage primaryStage)
	{
		/*
		 * Creating the buttons pane
		 */
		
		// Buttons
		Button btPass = new Button("Pass Turn");
		Button btQuit = new Button("Quit Game");

		// Setting actions for buttons
		btQuit.setOnAction(e -> Game.quitGameByForfeit());
		btPass.setOnAction(e -> Game.passTurn());

		HBox paneButton = new HBox(100);
		paneButton.setPadding(new Insets(15, 15, 15, 15));
		paneButton.setAlignment(Pos.CENTER);
		paneButton.getChildren().addAll(btPass, btQuit);

		GridPane paneBoard = showBoard();

		FlowPane panePlayers = showPlayers();
		
		FlowPane paneTimer = showTimers();
		
		VBox paneTop = new VBox(5); // the amount of vertical space between each child
		paneTop.setAlignment(Pos.CENTER);
		paneTop.getChildren().addAll(paneButton, paneTimer);


		/*
		 * Combining all panes to a single BorderPane
		 */

		BorderPane paneGame = new BorderPane();
		paneGame.setTop(paneTop);
		paneGame.setCenter(paneBoard);
		paneGame.setBottom(panePlayers);
		// timers at top

		Scene sceneGame = new Scene(paneGame, 600, 600);

		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneGame); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		

	}

	
	public static void showResults()
	{
		BorderPane paneResults = new BorderPane();
		Button btBackToMain = new Button("Back To Main Screen");
		btBackToMain.setOnAction(e -> (new ReversiApp()).start(primaryStage));
		
		if(Game.blackScore>Game.whiteScore)
		{
			Alert a1 = new Alert(AlertType.NONE,"BLACK HAS WON !", ButtonType.OK);
			Optional<ButtonType> result = a1.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				beginningStage(primaryStage);
			}
		}
		else if(Game.whiteScore>Game.blackScore)
		{
			Alert a1 = new Alert(AlertType.NONE,"WHITE HAS WON !", ButtonType.OK); 
			Optional<ButtonType> result = a1.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				beginningStage(primaryStage);
			}
		}
		else if(Game.blackScore==Game.whiteScore)
		{
			Alert a1 = new Alert(AlertType.NONE,"IT'S A TIE !", ButtonType.OK); 
			Optional<ButtonType> result = a1.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				beginningStage(primaryStage);
			}
		}
	}
	
	
	public static void beginningStage(Stage primaryStage)
	{
		Button btRegister = new Button("Sign In");
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
