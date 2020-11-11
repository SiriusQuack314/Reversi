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
		
		updateValidMoves();

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
		
		for(int x=0;x<8;x++)
		{
			for(int y=0;y<8;y++)
			{
				if(board[x][y].getToken() == 'B' || board[x][y].getToken() == 'W')
				{
					continue;
				}
				
				if(isValidMove(x, y))
				{
					board[x][y].setToken('+');
				}
				else
				{
					board[x][y].setToken(' ');
				}
				
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
		char player;
		char opp;
		if(Game.isBlacksTurn)
		{
			player = 'B';
			opp = 'W';
		}
		else
		{
			player = 'W';
			opp = 'B';
		}
		int xInit = x;
		int yInit = y;
		boolean possibleCapture = false;
		boolean hasCaptured = false;
		
		while(--x>=0 && --y>= 0)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(--x>=0 && --y>= 0)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}
		
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(--x>=0)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}	
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(--x>=0)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}	
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(--x>=0 && ++y<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}	
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(--x>=0 && ++y<8)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}	
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(++x<8 && --y>=0)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(++x<8 && --y>=0)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}		
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(++x<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(++x<8)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}		
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(++x<8 && ++y<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(++x<8 && ++y<8)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}		
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(--y>=0)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(--y>=0)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}		
		x = xInit;
		y = yInit;
		possibleCapture = false;
		hasCaptured = false;
		while(++y<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(possibleCapture)
				{
					hasCaptured = true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				possibleCapture = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		if(hasCaptured)
		{
			x = xInit;
			y = yInit;
			while(++y<8)
			{
				if(board[x][y].getToken() == player)
				{
					break;
				}
				else if(board[x][y].getToken() == opp)
				{
					flipCoin(x, y);
				}
			}
		}

	}
	
	
	/*
	 * Changes the color of the coin 
	 */
	public static void flipCoin(int x, int y)
	{
		if(board[x][y].getToken()=='W')
			board[x][y].setToken('B');
		else if(board[x][y].getToken()=='B')
			board[x][y].setToken('W');

	}
	
	public static boolean isValidMove(int x, int y)
	{
		char player;
		char opp;
		if(Game.isBlacksTurn)
		{
			player = 'B';
			opp = 'W';
		}
		else
		{
			player = 'W';
			opp = 'B';
		}
		int xInit = x;
		int yInit = y;
		boolean hasCaptured = false;
		
		while(--x>=0 && --y>= 0)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(--x>=0)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}		
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(--x>=0 && ++y<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}		
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(++x<8 && --y>=0)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}		
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(++x<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}		
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(++x<8 && ++y<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}		
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(--y>=0)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}		
		x = xInit;
		y = yInit;
		hasCaptured = false;
		while(++y<8)
		{
			if(board[x][y].getToken() == player)
			{
				if(hasCaptured)
				{
					return true;
				}
				else
				{
					break;
				}
			}
			else if(board[x][y].getToken() == opp)
			{
				hasCaptured = true;
			}
			else if(board[x][y].getToken() == ' ' || board[x][y].getToken() == '+')
			{
				break;
			}
		}
		
		return false;
	}
	
}
