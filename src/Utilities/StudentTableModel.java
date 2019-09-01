package Utilities;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	ArrayList<Student> studentList;
    ArrayList<String> columnNames;

    public StudentTableModel(ArrayList<Student> studentList,
            ArrayList<String> columnNames) {
        this.studentList = studentList;
        this.columnNames = columnNames;
    }

    public void updateStudentModel(ArrayList<Student> studentList,
            ArrayList<String> columnNames) {
        this.studentList = studentList;
        this.columnNames = columnNames;
        this.fireTableDataChanged();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    
    @Override
    public String getColumnName(int column) {
        StringBuilder sb = new StringBuilder(columnNames.get(column)); 
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));  
        return sb.toString();
    }

    @Override
    public int getRowCount() {
        return studentList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return new Integer
                        (studentList.get(rowIndex).getStudentNumber());
            case 1:
                return studentList.get(rowIndex).getFullName();
            case 2:
                return studentList.get(rowIndex).getHomeAddress();
        }
        return null;
    }
    
    public void removeRow(int rowIndex) {
        studentList.remove(rowIndex);
        this.fireTableDataChanged();
    }
    
    public Student getRow(int rowIndex) {
        return studentList.get(rowIndex);
    }
    
    public ArrayList<Student> getTableContent() {
        return studentList;
    }
    
}
