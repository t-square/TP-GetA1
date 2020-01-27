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

	private String sqlAddress = "jdbc:postgresql://localhost:5432/Top Trumps";
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

}