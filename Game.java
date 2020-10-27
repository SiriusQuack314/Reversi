import javafx.stage.Stage;

public class Game
{	
	Player black;
	Player white;
	static int blackScore;
	static int whiteScore;
	static boolean isBlacksTurn = true;
	static boolean gameOver = false;
	static int consecutivePasses = 0;
	
	public Game(Player black, Player white)
	{
		this.black = black;
		this.white = white;
		
		new Board(); //set up board
		updateScores(); //setting initial scores
		
	//	takeTurn();
		
	}
	
	public static void updateScores()
	{
		int[] scores = Board.calculateScores();
		blackScore = scores[0];
		whiteScore = scores[1];
	}
	
	public static void takeTurn()
	{		
		Game.updateScores();
		isBlacksTurn = !isBlacksTurn;
		Board.updateValidMoves();
		consecutivePasses = 0;
		
		if(Game.checkGameOver())
		{
			Game.quitGame();
			return;
		}
		
		GameUI.refresh(GameUI.primaryStage);
	}
	
	public static void passTurn()
	{
		if(!Board.hasValidMoves())
		{
			consecutivePasses++;
			isBlacksTurn = !isBlacksTurn;
			GameUI.refresh(GameUI.primaryStage);
		}		
	}
	
	public static void quitGame()
	{
		
	}
	
	/*
	 * Checks if the game is over.
	 * The game is over when either the board is full of pieces or
	 * when both players have no valid moves remaining (indicated by 2 consecutive passes).
	 * 
	 */
	public static boolean checkGameOver()
	{
		return (consecutivePasses>1) || Board.isFull();
	}
	
	public String toString()
	{
		return ""; //TBD
	}
	
}