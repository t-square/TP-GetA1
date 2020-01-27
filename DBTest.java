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

        try {
  	      
           dbA.sendUpdate("INSERT INTO  public.\"GameStatus\""
           		+ "(\"GameID\", \"GameDate\", \"GameTime\", \"NumberOfDraws\", \"PlayerID\", \"Winner\", \"WinnerType\", \"RoundsPlayed\", \"RoundsWon\") "
           		+ "VALUES ('5', '{01/27/2020}', '{10:00:00}', '5', '2', '{PlayerOne}', '1', '6', '3 );");
  		}
        
 		catch (Exception e) {
 			// Print exception information
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }	
        
        try {
    	      
           java.sql.ResultSet sResult =  dbA.sendQuery("SELECT * FROM public.\"GameStatus\"");
	       while (sResult.next()) {
				System.out.println("GameDate: " + sResult.getString("GameDate"));
	       }
		}
		catch (Exception e) {
			// Print exception information
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
       }	
     
        // Test close connection
        dbA.closeConnection();
        System.out.println("Database is closed.");
    }

}

