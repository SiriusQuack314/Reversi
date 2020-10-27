import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
		/*
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
		*/
		
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
	}

	private void handleMouseClick()
	{
		System.out.print("Clicked ");
		if (token == '+')
		{
			System.out.print("Valid");
			if(Game.isBlacksTurn)
			{
				this.setToken('B');
			}
			else
			{
				this.setToken('W');
			}
			Board.updateBoard(x, y);
			Game.takeTurn();
			
		}
		System.out.println();

	}
}