package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 *
 * @author TvA
 */

public class DBTest {

    /**
    * Main Method - gets the database username and password and then runs a connection test
    * @param args
    */
	
    public static void main(String args[]) {
        // input from standard in
    	DBAgent dbA = new DBAgent(); // use a single DBAgent instance to connect to a database
    	int nResult = -1;
  
    	// Test command interface
  /*
    	Scanner input = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();
        System.out.println();
        input.close();
                
        // Test set user and password
        dbA.setSQLUsername(username);
        dbA.setSQLPassword(password);
   */     
        // Test open connection
        if (dbA.openConnection(5)) {
        	 System.out.println("Database is online and available,");
        } else {
        	System.out.println("Database is NOT online. Exiting");
        	return;
        }
/*
        try {  	      
           dbA.sendUpdate("INSERT INTO  public.\"GameStatus\""
              		+ "( \"NumberOfDraws\", \"PName\", \"WinTime\", \"Winner\", \"RoundsPlayed\", \"RoundsWon\") "
              		+ "VALUES ('10', '{PlayerOne}', '5', '{AI}', '15', '3');");
  		}
        
 		catch (Exception e) {
 			// Print exception information
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }	
*/       
        try {
    	      
           java.sql.ResultSet sResult =  dbA.sendQuery("SELECT * FROM public.\"GameStatus\"");
	       while (sResult.next()) {
				System.out.println("GameID: " + sResult.getString("GameID"));
	       }
		}
		catch (Exception e) {
			// Print exception information
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
       }	
   
        dbA.updateGame("PlayerTwo", 5, "AI", 45, 23, 10);
        
        nResult = dbA.getTotalGamesPlayed();
		System.out.println("TOTAL_GAME_NUMBER: " + nResult);
		
        nResult = dbA.getAIWins();
		System.out.println("TOTAL_AI_WINS: " + nResult);
		
        nResult = dbA.getHumanWins();
		System.out.println("TOTAL_HUMAN_WINS: " + nResult);
		
        nResult = dbA.getAvgDraws();
		System.out.println("AVERAGE_DRAWS: " + nResult);
				
        nResult = dbA.getLargestRoundsPlayed();
		System.out.println("MOST_ROUNDS_PLAYED: " + nResult);
		
      // Test close connection
        dbA.closeConnection();
        System.out.println("Database is closed.");
    }

}

