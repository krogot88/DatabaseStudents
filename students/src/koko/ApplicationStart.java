package koko;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import koko.dao.DBManager;

public class ApplicationStart {
	static JTable table1;
	static JScrollPane jScrollPane = null;
	static JLabel label;
	static JFrame frame;
	static JTextField tf1;
	static JTextField tf2;
	
	public static void main(String[] args) {
		DBManager dbManager = new DBManager();	

        frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Groups");
        JMenuItem m22 = new JMenuItem("Students");
        m1.add(m11);
        m1.add(m22);
        
        m11.addActionListener((e)-> menuGroups());
        m22.addActionListener((e)-> menuStudents());
        
        JPanel panel = new JPanel(); 
        GroupLayout layout = new GroupLayout(panel); 
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true); 
        layout.setAutoCreateContainerGaps(true); 
        
        JLabel label1 = new JLabel("Фамилия");
        JLabel label2 = new JLabel("Группа");
        tf1 = new JTextField(15); 
        tf2 = new JTextField(15); 
        JButton send = new JButton("Добавить");
        JButton change = new JButton("Изменить");       
        JButton delete = new JButton("Удалить");
        label = new JLabel("-");
        
        send.addActionListener((e) -> {	      
        	if(Tables.groups()) groupsSendModal();
        	else studentsSendModal();
        });        
        change.addActionListener((e) -> {	      
        	if(Tables.groups()) groupsChangeModal();
        	else studentsChangeModal();
        });        
        delete.addActionListener((e) -> {	      
        	if(Tables.groups())groupsDeleteModal(); 
        	else studentsDeleteModal();
        });
        
        tf1.getDocument().addDocumentListener(new DocumentListener() {            
            public void insertUpdate(DocumentEvent e){update(e);}            
            public void removeUpdate(DocumentEvent e){update(e);}            
            public void changedUpdate(DocumentEvent e){update(e);}
            private void update(DocumentEvent e) {filterLastName();}
        });        
        tf2.getDocument().addDocumentListener(new DocumentListener() {            
            public void insertUpdate(DocumentEvent e){update(e);}            
            public void removeUpdate(DocumentEvent e){update(e);}            
            public void changedUpdate(DocumentEvent e){update(e);}
            private void update(DocumentEvent e) {filterLastName();}
        });
        
        layout.setHorizontalGroup(layout.createSequentialGroup()                 
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
                        .addComponent(label1) 
                        .addComponent(label2)) 
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
                		.addComponent(tf1) 
                		.addComponent(tf2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER) 
                		.addGroup(layout.createSequentialGroup()
                				.addComponent(send)
                				.addComponent(change)
                				.addComponent(delete))
                		.addComponent(label))
        );          
        layout.setVerticalGroup(layout.createSequentialGroup() 
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
                        .addComponent(label1) 
                        .addComponent(tf1) 
                        .addComponent(send)
                        .addComponent(change)
                        .addComponent(delete))                        
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
                		.addComponent(label2) 
                        .addComponent(tf2) 
                        .addComponent(label))
        );       

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        menuGroups();
        frame.setVisible(true);
	}
	
	public static void groupsChangeModal() {
		int selectRow = table1.getSelectedRow();
        JTextField number = new JTextField();
        JTextField faculty = new JTextField();
        int modelRow = table1.convertRowIndexToModel(selectRow); // for equals view row with model row
        String oldNumber = table1.getModel().getValueAt(modelRow, 0).toString();
        String oldFaculty = table1.getModel().getValueAt(modelRow, 1).toString();
        number.setText(oldNumber);
        faculty.setText(oldFaculty);
        final JComponent[] inputs = new JComponent[] {
                new JLabel("First"),
                number,
                new JLabel("Last"),
                faculty,	                    
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if(Group.save(oldNumber,number.getText(),faculty.getText())) {
            	table1.getModel().setValueAt(number.getText(), selectRow, 0);
            	table1.getModel().setValueAt(faculty.getText(), selectRow, 1);
            	TableRowSorter sorter = new TableRowSorter(table1.getModel());
            	table1.setRowSorter(sorter);
            	List<RowSorter.SortKey> sortKeys = new ArrayList<>(); 
            	sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));	                	 
            	sorter.setSortKeys(sortKeys);
            	sorter.sort();
            	
            } else {
            	label.setText("Not saved");
            	groupsChangeModal();
			}
        } 
	}
	
	public static void studentsChangeModal() {
		int selectRow = table1.getSelectedRow();
        JTextField lastName = new JTextField();
        JTextField firstName = new JTextField();
        JTextField fatherName = new JTextField();
        JTextField dateOfBirth = new JTextField();
        
        
        // Set list of exists groups in DataBase to popupwindow
        List<Group> list = Group.getAllGroups();
        List<String> items = new ArrayList<>();        
        list.forEach((group) -> items.add(String.valueOf(group.getNumber())));
        JComboBox groupNumer = new JComboBox(items.toArray());
        
        
        int modelRow = table1.convertRowIndexToModel(selectRow); // for equals view row with model row
        
        String oldLastName = table1.getModel().getValueAt(modelRow, 0).toString();
        String oldFirstNamet = table1.getModel().getValueAt(modelRow, 1).toString();
        String oldFatherName = table1.getModel().getValueAt(modelRow, 2).toString();
        String oldDateOfBirth = table1.getModel().getValueAt(modelRow, 3).toString(); 
        String oldgroupNumber = table1.getModel().getValueAt(modelRow, 4).toString()
        						.substring(0, table1.getModel().getValueAt(modelRow, 4).toString().indexOf(" ") ); 
        String id = table1.getModel().getValueAt(modelRow, 5).toString();
        
        
        lastName.setText(oldLastName);
        firstName.setText(oldFirstNamet);
        fatherName.setText(oldFatherName);
        dateOfBirth.setText(oldDateOfBirth); 
        groupNumer.setEditable(false);
        groupNumer.setSelectedItem(oldgroupNumber);;
        
        final JComponent[] inputs = new JComponent[] {
                new JLabel("фамилия"), lastName,
                new JLabel("имя"),  firstName,	                    
                new JLabel("отчество"),  fatherName,	                    
                new JLabel("дата рождения"),  dateOfBirth,	                    
                new JLabel("группа"),  groupNumer,	                    
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if(Student.save(id,lastName.getText(),firstName.getText(),fatherName.getText(),dateOfBirth.getText(),groupNumer.getSelectedItem().toString())) {
            	//table1.getModel().setValueAt(lastName.getText(), selectRow, 0);
            	//table1.getModel().setValueAt(firstName.getText(), selectRow, 1);
            	//table1.getModel().setValueAt(fatherName.getText(), selectRow, 2);
            	//table1.getModel().setValueAt(dateOfBirth.getText(), selectRow, 3);
            	//table1.getModel().setValueAt(groupNumer.getSelectedItem().toString(), selectRow, 4);
            	menuStudents();
            	
            } else {
            	label.setText("Not saved");
            	studentsChangeModal();
			}
        }
	}
	
	public static void groupsSendModal() {		
        JTextField number = new JTextField();
        JTextField faculty = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("First"),
                number,
                new JLabel("Last"),
                faculty,	                    
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if(Group.insert(number.getText(),faculty.getText())) {            	
            	menuGroups();         	
            } else {
            	label.setText("Not inserted");
			}
        } 
	}
	
	public static void studentsSendModal() {
		JTextField lastName = new JTextField();
		JTextField firstName = new JTextField();
		JTextField fatherName = new JTextField();
		JTextField dateOfBirth = new JTextField();
		
		List<Group> list = Group.getAllGroups();
        List<String> items = new ArrayList<>();        
        list.forEach((group) -> items.add(String.valueOf(group.getNumber())));
        JComboBox groupNumer = new JComboBox(items.toArray());
        
        final JComponent[] inputs = new JComponent[] {
                new JLabel("фамилия"), lastName,
                new JLabel("имя"),  firstName,	                    
                new JLabel("отчество"),  fatherName,	                    
                new JLabel("дата рождения"),  dateOfBirth,	                    
                new JLabel("группа"),  groupNumer,	                    
        };
        
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if(Student.insert(lastName.getText(),firstName.getText(),fatherName.getText(),dateOfBirth.getText(),groupNumer.getSelectedItem().toString())) {            	
            	menuStudents();         	
            } else {
            	label.setText("Not inserted");
			}
        } 
		
	}
	
	public static void groupsDeleteModal() {
		int selectRow = table1.getSelectedRow();
		int modelRow = table1.convertRowIndexToModel(selectRow);
		String number = table1.getModel().getValueAt(modelRow, 0).toString();
		int result = JOptionPane.showConfirmDialog(null, "U sure?", "delete student", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(Group.delete(number)) {            	
				menuGroups();
            } else {
            	label.setText("Not deleted");
			}
		}
	}
	
	public static void studentsDeleteModal() {
		int selectRow = table1.getSelectedRow();
		int modelRow = table1.convertRowIndexToModel(selectRow);
		String number = table1.getModel().getValueAt(modelRow, 5).toString();
		int result = JOptionPane.showConfirmDialog(null, "U sure?", "delete student", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(Student.delete(number)) {            	
				menuStudents();
            } else {
            	label.setText("Not deleted");
			}
		}
	}
	
	public static void menuGroups() {
		if(jScrollPane != null) 
			frame.getContentPane().remove(jScrollPane);
		Tables.setArrayToGroups();
		table1 = new JTable(Tables.getArray(),Tables.getColumnsHeader());
		jScrollPane = new JScrollPane(table1);
		frame.getContentPane().add(BorderLayout.CENTER,jScrollPane);
		tf1.setEnabled(false);
		tf2.setEnabled(false);		
		frame.revalidate();
		frame.repaint();
	}
	
	public static void menuStudents() {
		frame.getContentPane().remove(jScrollPane);
		Tables.setArrayToStudents();
		table1 = new JTable(Tables.getArray(),Tables.getColumnsHeader());
		table1.removeColumn(table1.getColumnModel().getColumn(5));
		TableRowSorter sorter = new TableRowSorter(table1.getModel());		
    	table1.setRowSorter(sorter);
    	filterLastName();
    	List<RowSorter.SortKey> sortKeys = new ArrayList<>(); 
    	sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));	                	 
    	sorter.setSortKeys(sortKeys);
    	sorter.sort();
		jScrollPane = new JScrollPane(table1);
		frame.getContentPane().add(BorderLayout.CENTER,jScrollPane);
		tf1.setEnabled(true);
		tf2.setEnabled(true);
		frame.revalidate();
		frame.repaint();
	}
	
	public static void filterLastName() {
		List<RowFilter<Object,Object>> rfs = new ArrayList<RowFilter<Object,Object>>(2);
		rfs.add(RowFilter.regexFilter("(?i)" + tf1.getText(), 0));
		rfs.add(RowFilter.regexFilter("(?i)" + tf2.getText(), 4));
		RowFilter<Object,Object> af = RowFilter.andFilter(rfs);
		
		TableRowSorter<? extends TableModel> rowSorter = (TableRowSorter<? extends TableModel>)table1.getRowSorter();		
        String text = tf1.getText();
        rowSorter.setRowFilter(af); 
	}
}


