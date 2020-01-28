package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

/**
 *
 * @author TvA
 */

public class DBAgent {

    /**
    * This class accesses the database
    * - Open connection
    * - Execute SQl queries
    * - Get SQL ResultSet
    * - Close Statements
    * - Close connection 
    */

	private String sqlAddress = "jdbc:postgresql://localhost:5432/TopTrumps";
	private String sqlUsername = "Manager";
	private String sqlPassword = "123456";
	
	Connection cSQL = null; // SQL connection object
	
	// Empty initializer
	public void DBAgent() {		
		
	}
	
	// Open a database connection, return true if success
	public boolean openConnection(int nTimeout)
	{
		try {
			// Use postgresql driver
			Class.forName("org.postgresql.Driver");
			cSQL = DriverManager.getConnection(sqlAddress, sqlUsername, sqlPassword);
        
	        // Limit wait for database to maximum of 10s, minimum of 1s, default 5s
	        if (nTimeout <= 0 && nTimeout > 10) {
	        	nTimeout = 5;
	        }
	        
	        // Verify that the connection is OK
	        if (cSQL != null)
	        {	        
	        	return true; // valid connection return true, else fall through and return false
	        }
		
		} 
		catch (Exception e) {
			// Print exception information
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }	
        
		return false; // fall through, return false, connection not established
	}

	// Close a database connection
	public void closeConnection()
	{
		// CLose an active SQL connection. OK no-op if already closed
		try {			
				cSQL.close();
		} 
		catch (Exception e) {
			// Print exception information
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }	
	}

	// set the address for the SQL access, ignore if null
	// Format "jdbc:postgresql://SERVER:PORT/DATABASE"
	public void setSQLAddress(String sAddress) {
		System.out.println("sAddress: " + sAddress);		
		if (!sAddress.isEmpty()) {
			sqlAddress = sAddress;
		}
	}

	// set the user name for SQL access, ignore if null
	public void setSQLUsername(String sUsername) {
		System.out.println("sUsername: " + sUsername);
		if (!sUsername.isEmpty()) {
			sqlUsername = sUsername;
		}
	}

	// Set the user name for SQL access, ignore if null
	public void setSQLPassword(String sPassword) {
		System.out.println("sPassword: " + sPassword);
		if (!sPassword.isEmpty()) {
			sqlPassword = sPassword;
		}
	}
	
	// Send a SQL query, return a ResultSet object
	public java.sql.ResultSet sendQuery(String sQuery) {
		java.sql.Statement qState = null; // create a SQL statement object
		java.sql.ResultSet qResult = null;

		try {
			
			qState = cSQL.createStatement(); // create a SQL statement object		
			qResult = qState.executeQuery(sQuery);						
		}
		catch (Exception e) {
			// Print exception information
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }	

		return qResult;
		
	}
	
	// Send a SQL update
	public void sendUpdate(String sUpdate) {
		java.sql.Statement qState = null; // create a SQL statement object
		
		try {
			
			qState = cSQL.createStatement(); 
			qState.executeUpdate(sUpdate);	// send update					
		}
		catch (Exception e) {
			// Print exception information
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }			
	}

	/*
	 * updateGame
	 * Adds game information to the database
	 * parameters: PlayerName, WinTime, Winner, RoundsPlayed, RoundsWon, NumberOfDraws
	 * 
	 */
	public void updateGame(String sPlayer, 
			int winTime,
			String sWinner,
			int roundsPlayed,
			int roundsWon,
			int numberOfDraws) {
		
		String sUpdate;
		
		sUpdate = "INSERT INTO  public.\"GameStatus\""
				+ "( \"NumberOfDraws\", \"PName\", \"WinTime\", \"Winner\", \"RoundsPlayed\", \"RoundsWon\") "
				+ "VALUES ('" 
				+ numberOfDraws + "', '{" 
				+ sPlayer + "}', '" 
				+ winTime + "', '{" 
				+ sWinner + "}', '" 
				+ roundsPlayed + "', '" 
				+ roundsWon + "');"; 
		
		// System.out.println("updateGame: " + sUpdate);		
        sendUpdate(sUpdate);
		
	}

	/* Query 1
	 * 1. Get total game played overall:
	 * SELECT COUNT(*) AS TOTAL_GAME_NUMBER
	 * FROM GAMESTATUS
	 */	
	public int getTotalGamesPlayed() {
		
		int nTotal = -1;
		java.sql.ResultSet rResultSet;
		String sQuery = "SELECT COUNT(*) AS TOTAL_GAME_NUMBER FROM public.\"GameStatus\"";
		
		try {  	      
				rResultSet = sendQuery(sQuery);
				while (rResultSet.next()) {
					nTotal = Integer.parseInt(rResultSet.getString("TOTAL_GAME_NUMBER"));
				}
		}
		catch (Exception e) {
				// Print exception information
	           e.printStackTrace();
	           System.err.println(e.getClass().getName()+": "+e.getMessage());
	           System.exit(0);
	    }		
		return nTotal;
	
	}

	/* Query 2
	 * 2. Get number of AI wins:
	 * SELECT COUNT(*) AS NUMBER_OF_AI_WINS
	 * FROM GAMESTATUS
	 * WHERE WINNER = AI
	 */
	public int getAIWins() {
		
		int nTotal = -1;
		java.sql.ResultSet rResultSet;
		String sQuery = "SELECT COUNT(*) AS NUMBER_OF_AI_WINS "
				+ "FROM public.\"GameStatus\" "
				+ "WHERE \"Winner\" = '{AI}'";
		
		try {  	      
				rResultSet = sendQuery(sQuery);
				while (rResultSet.next()) {
					nTotal = Integer.parseInt(rResultSet.getString("NUMBER_OF_AI_WINS"));
				}
		}
		catch (Exception e) {
				// Print exception information
	           e.printStackTrace();
	           System.err.println(e.getClass().getName()+": "+e.getMessage());
	           System.exit(0);
	    }		
		return nTotal;
		
	}
	
	/* Query 3
	 * 3.  Get number of human wins:
	 * SELECT COUNT(*) AS NUMBER_OF_HUMAN_WINS
	 * FROM GAMESTATUS
	 * WHERE WINNER = USER
	 */
	public int getHumanWins() {
		
		int nTotal = -1;
		java.sql.ResultSet rResultSet;
		String sQuery = "SELECT COUNT(*) AS NUMBER_OF_HUMAN_WINS "
				+ "FROM public.\"GameStatus\" "
				+ "WHERE \"Winner\" != '{AI}'";
		
		try {  	      
				rResultSet = sendQuery(sQuery);
				while (rResultSet.next()) {
					nTotal = Integer.parseInt(rResultSet.getString("NUMBER_OF_HUMAN_WINS"));
				}
		}
		catch (Exception e) {
				// Print exception information
	           e.printStackTrace();
	           System.err.println(e.getClass().getName()+": "+e.getMessage());
	           System.exit(0);
	    }		
		return nTotal;
		
	}
	
	/* Query 4
	 * 4. Get average draws
	 * SELECT Avg(draws) AS AVERAGE_DRAWS
	 * FROM GAMESTATUS 
	 */
	public int getAvgDraws() {
		
		double rTotal = -1.0;
		java.sql.ResultSet rResultSet;
		String sQuery = "SELECT AVG(\"NumberOfDraws\") AS AVERAGE_DRAWS "
				+ "FROM public.\"GameStatus\" ";
		
		try {  	      
				rResultSet = sendQuery(sQuery);
				while (rResultSet.next()) {
					rTotal = Double.parseDouble(rResultSet.getString("AVERAGE_DRAWS"));
				}
		}
		catch (Exception e) {
				// Print exception information
	           e.printStackTrace();
	           System.err.println(e.getClass().getName()+": "+e.getMessage());
	           System.exit(0);
	    }		
		return (int)rTotal;
		
	}
	
	/* Query 5
	 * 5. Get the largest number of rounds played in a single game
	 * SELECT Max(RoundsPlayed) AS MOST_ROUNDS_PLAYED
	 * FROM GAMESTATUS 
	 */
	public int getLargestRoundsPlayed() {
		
		int nTotal = -1;
		java.sql.ResultSet rResultSet;
		String sQuery = "SELECT MAX(\"RoundsPlayed\") AS MOST_ROUNDS_PLAYED "
				+ "FROM public.\"GameStatus\" ";
		
		try {  	      
				rResultSet = sendQuery(sQuery);
				while (rResultSet.next()) {
					nTotal = Integer.parseInt(rResultSet.getString("MOST_ROUNDS_PLAYED"));
				}
		}
		catch (Exception e) {
				// Print exception information
	           e.printStackTrace();
	           System.err.println(e.getClass().getName()+": "+e.getMessage());
	           System.exit(0);
	    }		
		return nTotal;
		
	}

}