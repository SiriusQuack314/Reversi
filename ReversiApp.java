import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.util.Duration;

public class ReversiApp extends Application {

	Scene sceneInitial, sceneRegister, sceneSignIn, sceneStats, sceneConfig, sceneGame;
	private Cell board[][] = new Cell[8][8];
	private static final Integer Time = 20;
	private Timeline timeline;
	private Integer Whitetime = Time;
	private Integer Blacktime = Time;


	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) {
		/*
		 * Initial Screen
		 * 
		 * When the Reversi App is launched, this is the first scene it will go to.
		 * 
		 */

		// Buttons
		Button btRegister = new Button("Register New Player");
		Button btSignIn = new Button("Sign In");
		Button btStats = new Button("View Statistics");
		Button btConfig = new Button("Configure Game");
		Button btStart = new Button("Start Game");

		// Setting actions for buttons
		btRegister.setOnAction(e -> primaryStage.setScene(sceneRegister));
		btSignIn.setOnAction(e -> primaryStage.setScene(sceneSignIn));
		btStats.setOnAction(e -> primaryStage.setScene(sceneStats));
		btConfig.setOnAction(e -> primaryStage.setScene(sceneConfig));
		btStart.setOnAction(e -> primaryStage.setScene(sceneGame));

		VBox paneInitial = new VBox(25); // the amount of vertical space between each child
		paneInitial.setAlignment(Pos.CENTER);
		paneInitial.getChildren().addAll(btRegister, btSignIn, btStats, btConfig, btStart);

		sceneInitial = new Scene(paneInitial, 500, 500);

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
		btBackReg.setOnAction(e -> primaryStage.setScene(sceneInitial));

		VBox paneRegister = new VBox(25); // the amount of vertical space between each child
		paneRegister.setAlignment(Pos.CENTER);
		paneRegister.getChildren().addAll(LbUsername, TxUsername, LbPassword, TxPassword, btSubmitReg, btBackReg);

		sceneRegister = new Scene(paneRegister, 500, 500);

		/*
		 * Sign In Scene
		 * 
		 * Here a user can sign in to their profile. To sign in, one enters a screen
		 * name of five letters and digits; and a password of five digits.
		 * 
		 */

		// Labels & Text fields
		Label LbUsernameS = new Label("Username"); // Labels will be on left..
		TextField TxUsernameS = new TextField();
		TxUsernameS.setMaxWidth(250);

		Label LbPasswordS = new Label("Password");
		TextField TxPasswordS = new TextField();
		TxPasswordS.setMaxWidth(250);

		// Buttons
		Button btSubmitSign = new Button("Submit");
		Button btBackSign = new Button("Back");

		// Setting actions for buttons
		btBackSign.setOnAction(e -> primaryStage.setScene(sceneInitial));

		VBox paneSignIn = new VBox(25); // the amount of vertical space between each child
		paneSignIn.setAlignment(Pos.CENTER);
		paneSignIn.getChildren().addAll(LbUsernameS, TxUsernameS, LbPasswordS, TxPasswordS, btSubmitSign, btBackSign);

		sceneSignIn = new Scene(paneSignIn, 500, 500);

		/*
		 * Statistics Scene
		 * 
		 * The system will keep records of wins and losses of players. This is where
		 * guests and players can view records of wins/losses of individual games or
		 * players.
		 * 
		 */

		Label SelectPl = new Label("Select player to view statistics:");
		ObservableList<String> players = FXCollections.observableArrayList("Player 1", "Player 2", "Player 3"); // It's
																												// Going
																												// to
																												// Take
																												// From
																												// File

		ComboBox ComPlayers = new ComboBox(players);

		int numberOfWins = 0;
		int numberOfLosses = 0;

		Label TxWins = new Label("Wins : " + numberOfWins);
		Label TxLoses = new Label("Loses : " + numberOfLosses);

		// Buttons
		Button btBackStat = new Button("Back");

		// Setting actions for buttons
		btBackStat.setOnAction(e -> primaryStage.setScene(sceneInitial));

		VBox paneStats = new VBox(25); // the amount of vertical space between each child
		paneStats.setAlignment(Pos.CENTER);
		paneStats.getChildren().addAll(SelectPl, ComPlayers, TxWins, TxLoses, btBackStat);

		sceneStats = new Scene(paneStats, 500, 500);

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
		ObservableList<String> times = FXCollections.observableArrayList("1", "5", "10", "15", "30", "60"); // Fixed
																											// Times For
																											// Limiting
																											// The Time
		ComboBox ComTimeLimit = new ComboBox(times);

		// Buttons
		Button btSubmitTime = new Button("Submit");
		Button btBackConfig = new Button("Back");

		// Setting actions for buttons
		btBackConfig.setOnAction(e -> primaryStage.setScene(sceneInitial));

		VBox paneConfig = new VBox(25); // the amount of vertical space between each child
		paneConfig.setAlignment(Pos.CENTER);
		paneConfig.getChildren().addAll(LbSetTime, ComTimeLimit, btSubmitTime, btBackConfig);

		sceneConfig = new Scene(paneConfig, 500, 500);

		/*
		 * Start Game Scene
		 * 
		 * The game will start once the player(s) have signed up to play. Each player
		 * will have their own timer.
		 * 
		 */
		


		Label LbTimer1 = new Label(Time.toString()); // temp name
		Label LbTimer2 = new Label(Time.toString());
		// Buttons
		Button btPass = new Button("Pass Turn");
		Button btTimer = new Button("Start Timer");
		Button btQuit = new Button("Quit Game");

		btTimer.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				if (timeline != null) {
					timeline.stop();
				}
				// update timerLabel
				Whitetime = Time;
				LbTimer2.setText(Whitetime.toString());
				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
					@Override
					public void handle(Event arg0) {
						Whitetime--;
						// update timerLabel
						LbTimer2.setText(Whitetime.toString());
						if (Whitetime <= 0) {
							timeline.stop();
						}
					}
				}));
				timeline.playFromStart();
			}
		});

		// Setting actions for buttons
		btQuit.setOnAction(e -> primaryStage.setScene(sceneInitial));

		HBox paneButton = new HBox(100);
		paneButton.setPadding(new Insets(15, 15, 15, 15));
		paneButton.setAlignment(Pos.CENTER);
		paneButton.getChildren().addAll(btPass, btTimer, btQuit);

		GridPane paneBoard = new GridPane();
		paneBoard.setAlignment(Pos.CENTER);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				paneBoard.add(board[i][j] = new Cell(), j, i);
			}
		}

		FlowPane panePlayers = new FlowPane();
		panePlayers.setAlignment(Pos.CENTER);
		panePlayers.setPadding(new Insets(20, 20, 20, 20));
		panePlayers.setHgap(20);

		 // temp name

		Label LbPlayer1 = new Label("Player1"); // temp name
		Label LbPlayer2 = new Label("Player2"); // temp name

		Label LbPlayer1Score = new Label("0"); // temp var
		Label LbPlayer2Score = new Label("0"); // temp var

		Circle tokenBlack = new Circle();
		tokenBlack.setRadius(15);
		tokenBlack.setFill(Color.BLACK);

		Circle tokenWhite = new Circle();
		tokenWhite.setRadius(15);
		tokenWhite.setStroke(Color.BLACK);
		tokenWhite.setFill(Color.WHITE);

		// This token is for spacing purposes
		Circle tokenInvis = new Circle();
		tokenInvis.setRadius(15);
		tokenInvis.setStroke(Color.TRANSPARENT);
		tokenInvis.setFill(Color.TRANSPARENT);
		
		LbTimer1.setFont(new Font(20));
		LbTimer2.setFont(new Font(20));
		LbPlayer1.setFont(new Font(20));
		LbPlayer2.setFont(new Font(20));
		LbPlayer1Score.setFont(new Font(20));
		LbPlayer2Score.setFont(new Font(20));

		panePlayers.getChildren().addAll(LbTimer1, LbPlayer1, LbPlayer1Score, tokenBlack, tokenInvis, tokenWhite,
				LbPlayer2Score, LbPlayer2, LbTimer2);

		BorderPane paneGame = new BorderPane();
		paneGame.setTop(paneButton);
		paneGame.setCenter(paneBoard);
		paneGame.setBottom(panePlayers);
		// timers at top

		sceneGame = new Scene(paneGame, 600, 600);

		/*
		 * Creates the Initial Scene and places it in the stage
		 * 
		 */

		primaryStage.setTitle("Reversi"); // Set the stage title
		primaryStage.setScene(sceneInitial); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public class Cell extends Pane {
		private char token = ' ';

		public Cell() {
			setStyle("-fx-border-color: black");
			this.setPrefSize(50, 50);
			this.setOnMouseClicked(e -> handleMouseClick());
		}

		public char getToken() {
			return token;
		}

		public void setToken(char c) {
			// TO DO
		}

		private void handleMouseClick() {
			System.out.println("Clicked!");
		}
	}
}
