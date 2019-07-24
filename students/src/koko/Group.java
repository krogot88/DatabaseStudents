package koko;


import java.util.List;

import koko.dao.GroupDAO;

public class Group {
	private int number;
	private String faculty;
	private int students;
	
	public Group(int number, String faculty) {
		this.number = number;
		this.faculty = faculty;
		students = 0;
	}
	
	public Group(int number, String faculty, int students) {
		this.number = number;
		this.faculty = faculty;
		this.students = students;
	}
	
	public static List<Group> getAllGroups() {		
		List<Group> result = GroupDAO.getAllGroups();
		return result;		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}

	public static boolean save(int oldNumber, int newNumber, String faculty) {
		return GroupDAO.save(oldNumber, newNumber, faculty);
	}
	
	public static boolean save(String oldNumber, String newNumber, String faculty) {
		return Group.save(Integer.parseInt(oldNumber),Integer.parseInt(newNumber), faculty);		
	}
	
	public static boolean insert(int newNumber, String faculty) {
		return GroupDAO.insert(newNumber, faculty);
	}
	
	public static boolean insert(String newNumber, String faculty) {
		return Group.insert(Integer.parseInt(newNumber), faculty);		
	}
	
	public static boolean delete(int number) {
		return GroupDAO.delete(number);
	}
	
	public static boolean delete(String number) {
		return Group.delete(Integer.parseInt(number));		
	}

	@Override
	public String toString() {
		if(number == 0) {
			return "- " ;  // this gap " "  is very important, do not delete it!
		}
		return number + " " + faculty;
	}	
}
