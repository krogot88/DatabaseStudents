package koko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import koko.Group;

public class GroupDAO {
	private final static String SELECT_ALL = "select * from groups";
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
		Connection connection = DBManager.getDBConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("UPDATE groups SET numer = " + newNumber + ", faculty = '" + faculty + "' WHERE numer = " + oldNumber);			
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
	
	public static boolean insert(int number, String faculty) {
		Connection connection = DBManager.getDBConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO groups(numer,faculty) VALUES (" + number + ",'" + faculty + "')");			
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
	
	public static boolean delete(int number) {
		Connection connection = DBManager.getDBConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM groups WHERE numer = " + number);			
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
