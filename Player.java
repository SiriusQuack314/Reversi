
public class Player
{
	private String username;
	private String password;
	private boolean isAdmin;
	
	public Player()
	{
		
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void signIn(String username, String password)
	{
		//TO DO
		//include way to recognize if they are an admin, and set isAdmin to true
	}
	
	public void register(String username, String password)
	{
		//TO DO
	}
	
	public String toString()
	{
		return "Username: " + username + " | Password: " + password + " | Admin: " + isAdmin;
	}
	
}
