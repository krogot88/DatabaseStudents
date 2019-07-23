package koko;

import java.util.Iterator;
import java.util.List;

public class Tables {
	private static Object[][] array = new String[][] {{ "�����" , "��","1" },{ "����"  , "��","1" },{ "������", "�","1"  }};
    private static Object[] columnsHeader = new String[] {"������ �", "���������","���������"};
    private static boolean groups;  // 0 - groups, 1 - students
    
    public Tables() {
    	
    }
    
	public static Object[][] getArray() {
		return array;
	}
	
	public static void setArrayToGroups() {		
		List<Group> list = Group.getAllGroups();
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
		Tables.columnsHeader = new String[] {"������ �", "���������","���������"};
		groups = true;
	}
	
	public static void setArrayToStudents() {		
		List<Student> list = Student.getAllStudents();
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
		Tables.columnsHeader = new String[] {"�������", "���","��������","���� ��������","������","id"};
		groups = false;
	}
	
	public static boolean groups() {
		return groups;
	}

	public static Object[] getColumnsHeader() {
		return columnsHeader;
	}  
}