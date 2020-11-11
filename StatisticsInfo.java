import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class StatisticsInfo
{
  
	//Creates the stats.txt file to save player's data
	public static void createData()
    {
	   try 
	    {
	      File newFile = new File("stats.txt");
	      if (newFile.createNewFile()) 
	      {
	        System.out.println("File created: " + newFile.getName());
	      } 
	      
	      else 
	      {
	        System.out.println("User Stats Found");
	      }
	    } 
	    
	    
	    catch (IOException e) 
	    {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	   
    }
	
	//Writes a new player to the file
	public static void write(String p) throws IOException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		FileWriter fw = new FileWriter("stats.txt", true);
		
		while(fs.hasNextLine())
		{
			fs.nextLine();
			
		}
		fw.write(p+" 0 0\n");
		fw.close();
	}
	
	
	//This finds a player's name, wins, and losses, and returns them. I may delete this.
	//EDIT: This now finds a player and returns true if found. False if not.
	public static boolean find(String p) throws IOException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		String pn = p;
		int pw = 0;
		int pl = 0;
		String check = "";
		String [] check1 = new String[3];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				pw=Integer.parseInt(check1[1]);
				pl=Integer.parseInt(check1[2]);
				return true;
			}
			
		}
		
		//return pn+" has "+pw +" wins and "+pl+" losses";
		return false;
	}
	
	
	//Overwrites a players previous data
	public static void overWrite(String p, int w, int l) throws IOException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		
		String pn=p;
		String old="";
		String check = "";
		String [] check1 = new String[3];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				//This essentially deletes the player's old data, so it can be rewritten later
			}
			else
			{
				old+=check+"\n";			
			}
		}
		
		FileWriter fw = new FileWriter("stats.txt");
		fw.write(old);
		fw.append(p+" "+w+" "+l+"\n");
		fw.close();
	}
	
	//Deletes a Player
	public static void delete(String p) throws IOException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		
			
		String pn=p;
		//String old = fs.nextLine();
		String old="";
		String check = "";
		String [] check1 = new String[3];
		while(fs.hasNextLine())
		{
				
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				
			}
			else
			{
				old+=check+"\n";		
			}
			
				
		}
			
		FileWriter fw = new FileWriter("stats.txt");
		fw.write(old);
		fw.close();
	}
	
	//Displays all players in the file. This may move or change
	//EDIT: This has changed from void to String
	public static String displayAll() throws FileNotFoundException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		String check = "";
		
		while(fs.hasNextLine())
		{
			check+=fs.nextLine()+"\n";
		}
		
		return check;
	}
	
	//Gets player wins
	public static int getWins(String p) throws FileNotFoundException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		String pn = p;
		int pw = 0;
		String check = "";
		String [] check1 = new String[3];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				pw=Integer.parseInt(check1[1]);
				break;
			}
			
		}
		
		return pw;
	}
	
	//Gets Player losses
	public static int getLosses(String p) throws FileNotFoundException
	{
		Scanner fs = new Scanner(new File("stats.txt"));
		String pn = p;
		int pl = 0;
		String check = "";
		String [] check1 = new String[3];
		
		while(fs.hasNextLine())
		{
			
			check=fs.nextLine();
			check1 = check.split(" ");
			if(check1[0].equals(pn))
			{
				pl=Integer.parseInt(check1[2]);
				break;
			}
			
		}
		
		return pl;
	}

	
   
}
