package koko;

import java.util.Date;
import java.util.List;

import koko.dao.GroupDAO;
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
	
	

	public static List<Student> getAllStudents() {		
		List<Student> result = StudentDAO.getAllStudents();
		return result;		
	}
	
	public static boolean delete(int number) {
		return StudentDAO.delete(number);
	}
	
	public static boolean delete(String number) {
		return delete(Integer.parseInt(number));		
	}
	
	public static boolean save(String id,String lastName,String firstName,String fatherName,String dateOfBirth,String groupNumer) {		
		return save(Long.parseLong(id),lastName,firstName,fatherName,dateOfBirth,Integer.parseInt(groupNumer));
	}
	
	public static boolean save(long id,String lastName,String firstName,String fatherName,String dateOfBirth,int groupNumer) {		
		return StudentDAO.save(id,lastName,firstName,fatherName,dateOfBirth,groupNumer);
	}
	
	public static boolean insert(String lastName,String firstName,String fatherName,String dateOfBirth,String groupNumer) {
		return insert(lastName,firstName,fatherName,dateOfBirth,Integer.parseInt(groupNumer));
	}
	
	public static boolean insert(String lastName,String firstName,String fatherName,String dateOfBirth,int groupNumer) {
		return StudentDAO.insert(lastName,firstName,fatherName,dateOfBirth,groupNumer);			
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	


}
