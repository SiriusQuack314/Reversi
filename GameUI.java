import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.util.Duration;

public class GameUI
{
	Game game;
	public static Stage primaryStage;
	static Label LbPlayer1Time;
	static Label LbPlayer2Time;

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws IOException
	{
		/*
		 * Starting a new game
		 */
		Game.resetTimer();
		Timer.start();
		GameUI.primaryStage = primaryStage;

		game = new Game((new Player()), new Player()); // Change this once sign in is complete

		// GameUI.primaryStage = primaryStage;

		refresh(primaryStage);

	}

	public static FlowPane showPlayers()
	{
		FlowPane panePlayers = new FlowPane();
		panePlayers.setAlignment(Pos.CENTER);
		panePlayers.setPadding(new Insets(20, 20, 20, 20));
		panePlayers.setHgap(20);
		Label LbPlayer1 = new Label("");
		Label LbPlayer2 = new Label("");

		if (Player.getLogin() == true && Player2.getLogin() == true)
		{
			if (Player.getPriority() == true)
			{
				LbPlayer1 = new Label(Player.getUsername());
				LbPlayer2 = new Label(Player2.getUsername());
			}
			else if (Player.getPriority() == false)
			{
				LbPlayer1 = new Label(Player2.getUsername());
				LbPlayer2 = new Label(Player.getUsername());
			}
		}

		else if (Player.getLogin() == true)
		{
			if (Player.getPriority() == true)
			{
				LbPlayer1 = new Label(Player.getUsername());
				LbPlayer2 = new Label("Player2");
			}
			else if (Player.getPriority() == false)
			{
				LbPlayer1 = new Label("Player1");
				LbPlayer2 = new Label(Player.getUsername());
			}
		}

		else if (Player2.getLogin() == true)
		{
			if (Player2.getPriority() == true)
			{
				LbPlayer1 = new Label(Player2.getUsername());
				LbPlayer2 = new Label("Player2");
			}
			else if (Player.getPriority() == false)
			{
				LbPlayer1 = new Label("Player1");
				LbPlayer2 = new Label(Player2.getUsername());
			}
		}

		else
		{

			LbPlayer1 = new Label("Player1"); // temp name
			LbPlayer2 = new Label("Player2"); // temp name

		}

		Label LbPlayer1Score = new Label("" + Game.blackScore); // temp var
		Label LbPlayer2Score = new Label("" + Game.whiteScore); // temp var

		if (Game.isBlacksTurn)
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

	private static FlowPane showTimers()
	{
		FlowPane paneTimer = new FlowPane();
		paneTimer.setAlignment(Pos.CENTER);
		paneTimer.setPadding(new Insets(20, 20, 20, 20));
		paneTimer.setHgap(20);

		Label LbPlayer1 = new Label("Player1 Time"); // temp name
		Label LbPlayer2 = new Label("Player2 Time"); // temp name

		LbPlayer1Time = new Label("" + (Game.blackTime / (1000 * 60)) % 60 + "::" + (Game.blackTime / 1000) % 60); // temp
																													// var
		LbPlayer2Time = new Label("" + (Game.whiteTime / (1000 * 60)) % 60 + "::" + (Game.whiteTime / 1000) % 60); // temp
																													// var

		paneTimer.getChildren().addAll(LbPlayer1, LbPlayer1Time, LbPlayer2, LbPlayer2Time);

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
				}
				else if (Board.board[i][j].getToken() == 'W')
				{
					Circle tokenWhite = new Circle();
					tokenWhite.setRadius(25);
					tokenWhite.setStroke(Color.BLACK);
					tokenWhite.setFill(Color.WHITE);
					paneBoard.add(tokenWhite, j, i);
				}
				else if (Board.board[i][j].getToken() == ' ')
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
		btQuit.setOnAction(e -> {
			try
			{
				Game.quitGameByForfeit();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btPass.setOnAction(e -> {
			try
			{
				Game.passTurn();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

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

	public static void showResults() throws FileNotFoundException, IOException
	{
		Button btBackToMain = new Button("Back To Main Screen");
		btBackToMain.setOnAction(e -> (new ReversiApp()).start(primaryStage));

		if (Game.blackScore > Game.whiteScore)
		{
			// Sees if the player is logged in and if he is black. If so, he gets a win
			// added to his stats
			if (Player.isLoggedIn == true)
			{
				if (Player.isBlack == true)
				{
					StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()) + 1,
							StatisticsInfo.getLosses(Player.getUsername()));
				}
			}

			// Checks to see if Player 2 is logged in and black. If so,
			// he gets a win
			if (Player2.isLoggedIn == true)
			{
				if (Player2.isBlack == true)
				{
					StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()) + 1,
							StatisticsInfo.getLosses(Player2.getUsername()));
				}
			}

			// Checks to see if Player 1 is white and logged in. If he is, he has lost the
			// game
			if (Player.isLoggedIn == true)
			{
				if (Player.isBlack == false)
				{
					StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()),
							StatisticsInfo.getLosses(Player.getUsername()) + 1);
				}
			}

			// Checks to see if Player 2 is white and logged in. If he is, he has lost the
			// game
			if (Player2.isLoggedIn == true)
			{
				if (Player2.isBlack == false)
				{
					StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()),
							StatisticsInfo.getLosses(Player2.getUsername()) + 1);
				}
			}

			Alert a1 = new Alert(AlertType.NONE, "BLACK HAS WON !", ButtonType.OK);
			Optional<ButtonType> result = a1.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				beginningStage(primaryStage);
			}
		}
		else if (Game.whiteScore > Game.blackScore)
		{

			// Sees if the player is logged in and if he is black. If so, he gets a win
			// added to his stats
			if (Player.isLoggedIn == true)
			{
				if (Player.isBlack == false)
				{
					StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()) + 1,
							StatisticsInfo.getLosses(Player.getUsername()));
				}
			}

			// Checks to see if Player 2 is logged in and white. If so,
			// he gets a win
			if (Player2.isLoggedIn == true)
			{
				if (Player2.isBlack == false)
				{
					StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()) + 1,
							StatisticsInfo.getLosses(Player2.getUsername()));
				}
			}

			// Checks to see if Player 1 is black and logged in. If he is, he has lost the
			// game
			if (Player.isLoggedIn == true)
			{
				if (Player.isBlack == true)
				{
					StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()),
							StatisticsInfo.getLosses(Player.getUsername()) + 1);
				}
			}

			// Checks to see if Player 2 is black and logged in. If he is, he has lost the
			// game
			if (Player2.isLoggedIn == true)
			{
				if (Player2.isBlack == true)
				{
					StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()),
							StatisticsInfo.getLosses(Player2.getUsername()) + 1);
				}
			}

			Alert a1 = new Alert(AlertType.NONE, "WHITE HAS WON !", ButtonType.OK);
			Optional<ButtonType> result = a1.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				beginningStage(primaryStage);
			}
		}
		else if (Game.blackScore == Game.whiteScore)
		{
			Alert a1 = new Alert(AlertType.NONE, "IT'S A TIE !", ButtonType.OK);
			Optional<ButtonType> result = a1.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				beginningStage(primaryStage);
			}

		}
	}

	public static void beginningStage(Stage primaryStage)
	{
		// Buttons
		Button btRegister = new Button("Register");
		Button btSignIn = new Button("Sign In");
		Button btStats = new Button("View Statistics");
		Button btConfig = new Button("Configure Game");
		Button btGame = new Button("Start Game");

		// Setting actions for buttons
		btRegister.setOnAction(e -> (new RegisterUI()).start(primaryStage));
		btSignIn.setOnAction(e -> (new SigninUI()).start(primaryStage));
		btStats.setOnAction(e -> {
			try
			{
				(new StatisticsUI()).start(primaryStage);
			}
			catch (FileNotFoundException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btConfig.setOnAction(e -> (new ConfigureUI()).start(primaryStage));
		btGame.setOnAction(e -> {
			try
			{
				(new GameUI()).start(primaryStage);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox paneInitial = new VBox(25); // the amount of vertical space between each child
		paneInitial.setAlignment(Pos.CENTER);
		paneInitial.getChildren().addAll(btRegister, btSignIn, btStats, btConfig, btGame);

		Scene sceneInitial = new Scene(paneInitial, 500, 500);

		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneInitial); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void timeOut() throws FileNotFoundException, IOException
	{

		if (Game.blackTime <= 0)
		{
			// STATISTICS
			
			if (Player.isLoggedIn == true)
			{
				if (Player.isBlack == true)
				{
					StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()) + 1,
							StatisticsInfo.getLosses(Player.getUsername()));
				}
			}

			// Checks to see if Player 2 is logged in and black. If so,
			// he gets a win
			if (Player2.isLoggedIn == true)
			{
				if (Player2.isBlack == true)
				{
					StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()) + 1,
							StatisticsInfo.getLosses(Player2.getUsername()));
				}
			}

			// Checks to see if Player 1 is white and logged in. If he is, he has lost the
			// game
			if (Player.isLoggedIn == true)
			{
				if (Player.isBlack == false)
				{
					StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()),
							StatisticsInfo.getLosses(Player.getUsername()) + 1);
				}
			}

			// Checks to see if Player 2 is white and logged in. If he is, he has lost the
			// game
			if (Player2.isLoggedIn == true)
			{
				if (Player2.isBlack == false)
				{
					StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()),
							StatisticsInfo.getLosses(Player2.getUsername()) + 1);
				}
			}
			
			

			Alert a3 = new Alert(AlertType.NONE, "WHITE HAS WON !", ButtonType.OK);
			a3.show();
			beginningStage(primaryStage);
		}

		else if (Game.whiteTime <= 0)
		{
			// STATISTICS
			
			// Sees if the player is logged in and if he is black. If so, he gets a win
						// added to his stats
						if (Player.isLoggedIn == true)
						{
							if (Player.isBlack == false)
							{
								StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()) + 1,
										StatisticsInfo.getLosses(Player.getUsername()));
							}
						}

						// Checks to see if Player 2 is logged in and white. If so,
						// he gets a win
						if (Player2.isLoggedIn == true)
						{
							if (Player2.isBlack == false)
							{
								StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()) + 1,
										StatisticsInfo.getLosses(Player2.getUsername()));
							}
						}

						// Checks to see if Player 1 is black and logged in. If he is, he has lost the
						// game
						if (Player.isLoggedIn == true)
						{
							if (Player.isBlack == true)
							{
								StatisticsInfo.overWrite(Player.getUsername(), StatisticsInfo.getWins(Player.getUsername()),
										StatisticsInfo.getLosses(Player.getUsername()) + 1);
							}
						}

						// Checks to see if Player 2 is black and logged in. If he is, he has lost the
						// game
						if (Player2.isLoggedIn == true)
						{
							if (Player2.isBlack == true)
							{
								StatisticsInfo.overWrite(Player2.getUsername(), StatisticsInfo.getWins(Player2.getUsername()),
										StatisticsInfo.getLosses(Player2.getUsername()) + 1);
							}
						}

			Alert a2 = new Alert(AlertType.NONE, "BLACK HAS WON !", ButtonType.OK);
			a2.show();
			beginningStage(primaryStage);
		}

	}
}
