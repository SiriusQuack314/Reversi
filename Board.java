
public class Board
{
	static Cell[][] board;

	public Board()
	{
		board = new Cell[8][8];

		// initialize board
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{

				board[i][j] = new Cell(i,j);

				if ((i == 3 && j == 3) || (i == 4 && j == 4))
				{
					board[i][j].setToken('W');
				}
				if ((i == 3 && j == 4) || (i == 4 && j == 3))
				{
					board[i][j].setToken('B');
				}

			}
		}

	}

	/*
	 * Calculates the scores of each player.
	 * 
	 * Precondition: Board has been initialized. 
	 * Postcondition: Returns an int[] of
	 * the format [blackScore, whiteScore].
	 */
	public static int[] calculateScores()
	{
		int[] score = new int[2];

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (board[i][j].getToken() == 'B')
				{
					score[0]++;
				} else if (board[i][j].getToken() == 'W')
				{
					score[1]++;
				}

			}
		}

		return score;
	}

	/*
	 * Checks if the board is full of pieces.
	 * 
	 * Precondition: Board has been initialized. 
	 * Postcondition: Returns false if the board contains empty squares 
	 * (or valid moves); and true if the board only
	 * contains pieces.
	 * 
	 */
	public static boolean isFull()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				char c = board[i][j].getToken();

				if (c == '+' || c == ' ')
				{
					return false;
				}

			}
		}
		return true;
	}

	/*
	 * Checks if there are valid moves available on the board.
	 * 
	 * Precondition: Board has been initialized.
	 * Postcondition: Returns true if there are valid moves available;
	 * and false if there are no valid moves.
	 */
	public static boolean hasValidMoves()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{

				if (board[i][j].getToken() == '+')
				{
					return true;
				}

			}
		}
		return false;
	}

	/*
	 * Updates the valid moves available to the current player.
	 * 
	 * Precondition: Board has been initialized.
	 * Postcondition: The board is configured to determine the valid moves for the current player.
	 */
	public static void updateValidMoves()
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				
			}
		}
		
	}
	
	/*
	 * Updates the board to show the captures the player made.
	 * 
	 * Precondition: Board has been initiated. Player has made a move.
	 * Postcondition: All "captured" pieces are changed to the current player's color.
	 */
	public static void updateBoard(int x, int y)
	{
		
		
	}
	
}
