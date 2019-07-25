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

	public int getNumber() {
		return number;
	}

	public String getFaculty() {
		return faculty;
	}

	public int getStudents() {
		return students;
	}

	@Override
	public String toString() {
		if(number == 0) {
			return "- " ;  // this gap " "  is very important, do not delete it!
		}
		return number + " " + faculty;
	}	
}
