import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player
{
	private static String username;
	private String password;
	private boolean isAdmin;
	private int wins;
	private int losses;
	static boolean isBlack;
	static boolean isLoggedIn=false;
	
	public Player()
	{
		//place holder for now, will decide where to put this later
		//StatisticsInfo.write(username);
	}
	
	public Player(String pn)
	{
		this.username=pn;
	}
	
	public static void setUsername(String pn)
	{
		username=pn;
	}
	
	public static String getUsername()
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
	
	public static void setPriority()
	{
		int pr = (int)(Math.random()*2);
			if(pr==0)
			{
				isBlack=true;
				Player2.isBlack=false;
			}
			else
			{
				isBlack=false;
				Player2.isBlack=true;
			}
	}
	
	public static boolean getPriority()
	{
		return isBlack;
	}
	
	public static void setLogin()
	{
		isLoggedIn=true;
	}
	
	public static boolean getLogin()
	{
		return isLoggedIn;
	}
	
	public String toString()
	{
		return "Username: " + username + " | Password: " + password + " | Admin: " + isAdmin;
	}
}
