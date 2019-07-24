package koko.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import koko.Group;
import koko.Student;

public class StudentDAO {
	private final static String SELECT_ALL = "SELECT s.id, s.lastname, s.firstname, s.fathername, s.dateofbirth, g.numer, g.faculty FROM STUDENTS AS s LEFT JOIN groups AS g ON s.groupnumber = g.id ";
	
	public static List<Student> getAllStudents() {
		Connection connection = DBManager.getDBConnection();
		List<Student> result = new ArrayList<>();
		try {			
			ResultSet resultSet = connection.prepareStatement(SELECT_ALL).executeQuery();
			while(resultSet.next()) {
				Group group = new Group(resultSet.getInt("numer"), resultSet.getString("faculty"));
				result.add(new Student(resultSet.getLong("id"),resultSet.getString("firstname"), resultSet.getString("lastName"), resultSet.getString("fatherName"), resultSet.getDate("dateOfBirth"), group));
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
	
	public static boolean save(long id,String lN,String fN,String faN,String date,int groupNumer) {
		return executeCommnad("UPDATE students SET lastName = '" + lN + "', firstName = '" + fN + "', fatherName = '" + faN + "', dateOfBirth = '" + date	+ "', groupNumber = XXX WHERE id = " + id, groupNumer);		
	}
	
	public static boolean insert(String lastName,String firstName,String fatherName,String dateOfBirth,int groupNumer) {
		return executeCommnad("INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('" + firstName + "','" + lastName + "','"+ fatherName + "','"+ dateOfBirth + "', XXX )",groupNumer);		
	}
	
	public static boolean delete(int number) {
		return executeCommnad("DELETE FROM students WHERE id = " + number, -1);		
	}
	
	public static boolean executeCommnad(String sqlString, int groupNumer) {
		Connection connection = DBManager.getDBConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			if(groupNumer != -1) {
				ResultSet resultSet = connection.prepareStatement("SELECT id FROM groups WHERE numer="+groupNumer).executeQuery(); // resolve numer VS id
				resultSet.next();
				groupNumer = resultSet.getInt("id");			
				statement.executeUpdate(sqlString.replaceFirst("XXX", Integer.toString(groupNumer)));			
				return true;
			}
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
