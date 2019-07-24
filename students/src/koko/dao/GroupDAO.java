package koko.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import koko.Group;

public class GroupDAO {
	private final static String SELECT_ALL_AND_N = "SELECT a.numer, a.faculty, COUNT(b.groupnumber)  FROM groups AS a LEFT JOIN students AS b   ON  a.id = b.groupnumber GROUP BY   a.id";
	
	
	public static List<Group> getAllGroups() {
		Connection connection = DBManager.getDBConnection();
		List<Group> result = new ArrayList<Group>();
		try {			
			ResultSet resultSet = connection.prepareStatement(SELECT_ALL_AND_N).executeQuery();
			while(resultSet.next()) {
				result.add(new Group(resultSet.getInt("numer"),resultSet.getString("faculty"),resultSet.getInt("COUNT(B.GROUPNUMBER)")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean save(int oldNumber, int newNumber, String faculty) {
		return executeCommand("UPDATE groups SET numer = " + newNumber + ", faculty = '" + faculty + "' WHERE numer = " + oldNumber);		
	}
	
	public static boolean insert(int number, String faculty) {
		return executeCommand("INSERT INTO groups(numer,faculty) VALUES (" + number + ",'" + faculty + "')");		
	}
	
	public static boolean delete(int number) {
		return executeCommand("DELETE FROM groups WHERE numer = " + number);		
	}
	
	public static boolean executeCommand(String sqlString) {
		Connection connection = DBManager.getDBConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlString);			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
