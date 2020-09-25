import java.util.Scanner;
import java.nio.file.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class BasicSaveV1 
{

	static Scanner kb = new Scanner(System.in);
	//static Scanner fr = new Scanner(new File("saveTest.txt"));
	
	static String name;
	static String nameCheck;
	static String password;
	static String passCheck;
	static int stats=0;
	
	public static void Register() throws FileNotFoundException
	{
		Scanner fr = new Scanner(new File("saveTest.txt"));
		System.out.println("Hello new player! Enter your username: ");
		name = kb.next();
		
		//having trouble checking for duplicate usernames
		while(fr.hasNextLine())
		{
			if(fr.next().contains(name))
			{
				System.out.println("Sorry, but that username is taken");
				Register();
				return;
			}
		}
		
		System.out.println("Great, now what is gonna be your password?: ");
		password = kb.next();
		if(password.equals(name)) 
		{
			System.out.println("You can't have your password as your username! That's just poor security! \nTry Again Damnit!");
			Register();
			return;
		}
		System.out.println("Great now what's that social? Lol jk, this concludes part 1 of Brent's save test");
		
		Save();
	}
	
	
	
	
	static public void Save()
	{
		
		try 
		{
			FileWriter fw = new FileWriter("saveTest.txt");
			fw.write(name+" "+password);
			fw.close();
			
		}
		
		catch (IOException e)
		{
			System.out.println("You done goofed");
		}
	}
	
	public static void signIn() throws FileNotFoundException
	{
		Scanner fr = new Scanner(new File("saveTest.txt"));
		
		System.out.println("Username: ");
		nameCheck=kb.next();
		System.out.println("Password: ");
		passCheck=kb.next();
		
		String chex;
		
		while(fr.hasNext()) 
		{
			
			//Still having trouble looking trying to check for the damn user
			
			chex=nameCheck+" "+passCheck;
			if(chex.equals(fr.nextLine()))
			{
				System.out.println("Logged in");
				fr.close();
				return;
			}
			/*if(fr.next().contains(nameCheck+passCheck))
			{
				System.out.println("You're signed in chump! This concludes test 2 of Brent's save test. You didn't even have to study");
				fr.close();
				return;
			}*/
			//fr.nextLine();
			else
			{
				System.out.println("get fucked lol");
			}

		}
		
		/*System.out.println("I'm sorry Waldo, I couldn't find you");
		fr.close();*/
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		Register();
		signIn();
		
		

	}

}
