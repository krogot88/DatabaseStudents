package koko.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

public class DBManager {
	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    /**
     * 
     */
    public DBManager() { 
    	Connection conn = getDBConnection();
		try {
			RunScript.execute(conn, new FileReader("resources/init_tables.sql"));
		} catch (Exception e1) {	
			e1.printStackTrace();
		} 
		Runnable runServer = new Runnable() {				
			@Override
			public void run() {
				try {					
					Server.startWebServer(conn);
					System.out.println("H2 server stop work");
				} catch (SQLException e) {					
					e.printStackTrace();
				} 				
			}
		};
		Thread threadServer = new Thread(runServer);
		threadServer.start();
		System.out.println("H2 server started");
    }
    
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }    

}
