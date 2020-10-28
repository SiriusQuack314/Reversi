

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
		isBlacksTurn = true;
		new Board(); //set up board
		updateScores(); //setting initial scores
		
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
			if(consecutivePasses == 2)
			{
				quitGame();
			}
			takeTurn();
		}		
	}
	
	public static void quitGame()
	{
		GameUI.refresh(GameUI.primaryStage);
		Game.updateScores();
		GameUI.showResults();		
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

	public static void quitGameByForfeit()
	{
		if(isBlacksTurn)
		{
			blackScore = Integer.MIN_VALUE;
		}
		else
		{
			whiteScore = Integer.MIN_VALUE;
		}
		GameUI.showResults();
	}
	
}
