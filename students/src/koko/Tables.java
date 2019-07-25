package koko;

import java.util.Iterator;
import java.util.List;

import koko.dao.GroupDAO;
import koko.dao.StudentDAO;

public class Tables {
	private static Object[][] array;
    private static Object[] columnsHeader;
    private static boolean groups;  // 0 - groups, 1 - students
    
    public Tables() {
    	
    }
    
	public static Object[][] getArray() {
		return array;
	}
	
	public static void setArrayToGroups() {		
		List<Group> list = GroupDAO.getAllGroups();
		Object[][] result = new Object[list.size()][3];
		int i = 0;
		Iterator<Group> iterator  = list.iterator();
		while(iterator.hasNext()) {
			Group group = iterator.next();
			result[i][0] = group.getNumber();
			result[i][1] = group.getFaculty();
			result[i][2] = group.getStudents();
			i++;
		}
		Tables.array = result;
		Tables.columnsHeader = new String[] {"Группа №", "Факультет","студентов"};
		groups = true;
	}
	
	public static void setArrayToStudents() {		
		List<Student> list = StudentDAO.getAllStudents();
		Object[][] result = new Object[list.size()][6];
		int i = 0;
		Iterator<Student> iterator  = list.iterator();
		while(iterator.hasNext()) {
			Student student = iterator.next();
			result[i][0] = student.getLastName();
			result[i][1] = student.getFirstName();
			result[i][2] = student.getFatherName();
			result[i][3] = student.getDateOfBirth();
			result[i][4] = student.getGroup();
			result[i][5] = student.getId();
			i++;
		}
		Tables.array = result;
		Tables.columnsHeader = new String[] {"Фамилия", "Имя","Отчество","День рожедния","Группа","id"};
		groups = false;
	}
	
	public static boolean groups() {
		return groups;
	}

	public static Object[] getColumnsHeader() {
		return columnsHeader;
	}  
}
