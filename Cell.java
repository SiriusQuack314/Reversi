import javafx.scene.layout.Pane;

public class Cell extends Pane
{
	/*
	 * Tokens: 
	 * ' ' is a blank square 
	 * 'B' is a black piece 
	 * 'W' is a white piece 
	 * '+' is a valid move
	 */
	private char token = ' ';
	private int x;
	private int y;
	

	public Cell(int x, int y)
	{
		
		this.x = x;
		this.y = y;
		
		setStyle("-fx-border-color: black");
		
		this.setPrefSize(50, 50);
		this.setOnMouseClicked(e -> handleMouseClick());
	}

	public char getToken()
	{
		return token;
	}

	public void setToken(char c)
	{
		token = c;	
		if(token == ' ')
		{
			setStyle("-fx-border-color: black");
		}
		else if(token == '+')
		{
			setStyle("-fx-border-color: black; -fx-background-color: lightgreen");
		}
		else
		{
			setStyle("-fx-border-color: black");
		}
	}

	private void handleMouseClick()
	{
		if (token == '+')
		{
			
			if(Game.isBlacksTurn)
			{
				this.setToken('B');
			}
			else
			{
				this.setToken('W');
			}
			Game.consecutivePasses = 0;
			Board.updateBoard(x, y);
			Game.takeTurn();
			
		}

	}
}