/*
 * Class created by Brent Hebert
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//import sun.net.idn.StringPrep;

public class PlayerInfo
{	
	
	//finds Username in file
	public static String userCheck(String p) throws FileNotFoundException
	{
		Scanner fs = new Scanner(new File("reginfo.txt"));
		String pn = p;
		String pp = "";
		String check = "";
		String [] check1 = new String[2];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				return pn;
			}
			
		}
		return pn +" not found";
	}
	
	
	//Finds Password associated with username
	public static String passCheck(String p) throws FileNotFoundException
	{
		Scanner fs = new Scanner(new File("reginfo.txt"));
		String pn = p;
		String pp = "";
		String check = "";
		String [] check1 = new String[2];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				pp=check1[1];
				return pp;
			}
			
		}
		return pn+" not found";
	}
	
	//Checks to see if a user exits already. Returns true if they do, false if they don't
	public static boolean find(String p) throws IOException
	{
		Scanner fs = new Scanner(new File("reginfo.txt"));
		String pn = p;
		String check = "";
		String [] check1 = new String[2];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				return true;
			}
			
		}
		
		return false;
	}
	
	//Checks to see if password is valid
	public static boolean passVal(String p)
	{
		String pp = p;
		int pi=0;
		if(pp.length()==5)
		{
			try 
			{
				pi=Integer.parseInt(pp);
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		
		else
			return false;
		
	}
	
	
}
