package koko;

import java.util.Date;
import java.util.List;

import koko.dao.StudentDAO;

public class Student {
	private long id;
	private String firstName;
	private String lastName;
	private String fatherName;
	private Date dateOfBirth;
	private Group group;
	
	public Student(Long id, String firstName, String lastName, String fatherName, Date dateOfBirth, Group group) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherName = fatherName;
		this.dateOfBirth = dateOfBirth;
		this.group = group;
	}
	
	public long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getDateOfBirth() {
		return dateOfBirth.toString();
	}

	public Group getGroup() {
		return group;
	}
}
