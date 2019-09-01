package Interfaces;


import Utilities.Student;
import Utilities.StudentTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class UpdateRecordFrame extends JFrame {

	
	// Components declaration block starts here
	private JButton btnFilter;
	private JButton btnUpdate;
	private JLabel lbAddress;
	private JLabel lbFilter1;
	private JLabel lbFilter2;
	private JLabel lbName;
	private JLabel lbNumber;
	private JLabel lbTitle;
	private JPanel panelFilter;
	private JPanel panelUpdate;
	private JScrollPane scrTable;
	private JTable table;
	private JTextField txtfAddress;
	private JTextField txtfFilter;
	private JTextField txtfName;
	private JTextField txtfNumber;
	// Components declaration block ends here

	// Field variables block starts here
	private static final long serialVersionUID = 1L;
	private final static String query = "SELECT * FROM Students";
	private Connection connection = null;
	private ResultSet resultSet = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSetMetaData resultSetMetaData = null;
	private ArrayList<String> columnNames = new ArrayList<>();
	private ArrayList<Student> data = new ArrayList<>();;
	private StudentTableModel tableModel;
	private TableRowSorter<StudentTableModel> sorter;
	private int rowsChanged = -1;
	// Field variables block ends here

    public UpdateRecordFrame(Connection connection) {
        this.connection = connection;
        prepareTable();
        initComponents();
        setIcon();
    }
    
    private void prepareTable() {
        
        data.clear();
        columnNames.clear();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.toString(),
                        "Error in executing query!", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in resultSetMetaData = resultSet.getMetaData()", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                columnNames.add(resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in columnNames.add"
                    + "(resultSetMetaData.getColumnName())", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            while (resultSet.next()) {
                data.add(newRow(resultSet));
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in data.add(newRow(resultSet))", 
                         JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Student newRow(ResultSet r) {

        Student row = new Student();
        try {
            row.setStudentNumber(r.getInt(1));
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in row.setStudentNumber(r.getInt(1))", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            row.setFullName(r.getString(2));
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in row.setFullName(r.getString(2))", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            row.setHomeAddress(r.getString(3));
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in row.setHomeAddress(r.getString(3))", 
                         JOptionPane.ERROR_MESSAGE);
        }

        return row;
    }

                         
    private void initComponents() {

    	// Instantiation of components block starts here
        scrTable = new JScrollPane();
        table = new JTable();
        lbTitle = new JLabel();
        panelUpdate = new JPanel();
        txtfNumber = new JTextField();
        txtfName = new JTextField();
        txtfAddress = new JTextField();
        lbNumber = new JLabel();
        lbName = new JLabel();
        lbAddress = new JLabel();
        btnUpdate = new JButton();
        panelFilter = new JPanel();
        lbFilter1 = new JLabel();
        txtfFilter = new JTextField();
        lbFilter2 = new JLabel();
        btnFilter = new JButton();
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Records From Table Students");
        // Frame block ends here

        // Table block starts here
        table.setSelectionMode
        (ListSelectionModel.SINGLE_SELECTION);
        tableModel = new Utilities.StudentTableModel(data, columnNames);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.blue);
        table.setAutoCreateRowSorter(true);
        table.setFont(new Font("Arial", 0, 12));
        table.setModel(tableModel);
        scrTable.setViewportView(table);
        // Table block ends here

        // Title block starts here
        lbTitle.setFont(new java.awt.Font("Arial", 1, 24));
        lbTitle.setText("Update Student Record");
        // Title block ends here

        // Update panel block starts here
        panelUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbNumber.setFont(new java.awt.Font("Arial", 1, 12)); 
        lbNumber.setText("Student Number");
        lbName.setFont(new java.awt.Font("Arial", 1, 12)); 
        lbName.setText("Student Name");
        lbAddress.setFont(new java.awt.Font("Arial", 1, 12));
        lbAddress.setText("Student Address");
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_btn.png")));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        // Update panel block ends here
        
        // Filter panel block starts here
        panelFilter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFilter1.setText("Enter keyword to filter");
        lbFilter2.setText("Toreset filter leave this field empty and press Filter");
        txtfFilter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfFilterActionPerformed(evt);
            }
        });
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        // Filter panel block ends here

        // Update panel layout design starts here
        javax.swing.GroupLayout panelUpdateLayout = new javax.swing.GroupLayout(panelUpdate);
        panelUpdate.setLayout(panelUpdateLayout);
        panelUpdateLayout.setHorizontalGroup(
            panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUpdateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName)
                    .addComponent(lbNumber)
                    .addComponent(lbAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        panelUpdateLayout.setVerticalGroup(
            panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUpdateLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelUpdateLayout.createSequentialGroup()
                        .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAddress))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        // Update panel layout design ends here

        // Filter panel layout design starts here
        javax.swing.GroupLayout panelFilterLayout = new javax.swing.GroupLayout(panelFilter);
        panelFilter.setLayout(panelFilterLayout);
        panelFilterLayout.setHorizontalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFilter2)
                    .addComponent(lbFilter1)
                    .addGroup(panelFilterLayout.createSequentialGroup()
                        .addComponent(txtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        panelFilterLayout.setVerticalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilterLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbFilter1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFilter2)
                .addContainerGap(20, Short.MAX_VALUE))
        );       
        // Filter panel layout design ends here

        // Frame layout design starts here
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(lbTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrTable)
                            .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(scrTable, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(panelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(panelUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        // Frame layout design ends here

        pack();
    }                 

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          

        if (txtfName.getText().isEmpty() || txtfAddress.getText().isEmpty()
                || txtfNumber.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "Please fill in all fields",
                    "Information Message",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            int row = table.convertRowIndexToModel(table.getSelectedRow());
            updateQuery(tableModel.getRow(row));
            tableModel.fireTableDataChanged();

            txtfNumber.setText("");
            txtfName.setText("");
            txtfAddress.setText("");
            txtfFilter.setText("");

            prepareTable();
            tableModel.updateStudentModel(data, columnNames);

            sorter = new TableRowSorter<>(tableModel);
            table.setRowSorter(sorter);
            sorter.setRowFilter(null);
        }
    }                                         

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        String text = txtfFilter.getText();
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                JOptionPane.showMessageDialog(null, pse.toString(),
                        "Bad regex pattern",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                         

    private void txtfFilterActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        String text = txtfFilter.getText();
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                JOptionPane.showMessageDialog(null, pse.toString(),
                        "Bad regex pattern",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                          

    private void updateQuery(Student row) {
        String updateRecordQuery = " UPDATE Students SET sn = ? , name = ? , "
                + " address = ? WHERE sn = ? ";
        try {
            preparedStatement = connection.prepareStatement(updateRecordQuery);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement = "
                    + "connection.prepareStatement(updateRecordQuery)", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            preparedStatement.setInt(1, Integer.parseInt(txtfNumber.getText()));

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                    "Error in preparedStatement.setInt(1, "
                    + "row.getStudentNumber())",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Student Number should be integer!",
                    JOptionPane.ERROR_MESSAGE);
        } 
        try {
            preparedStatement.setString(2, txtfName.getText());
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement.setString(2, "
                    + "row.getFullName())", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            preparedStatement.setString(3, txtfAddress.getText());
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement.setString(3, "
                    + "row.getHomeAddress())", 
                         JOptionPane.ERROR_MESSAGE);
        }
        try {
            preparedStatement.setInt(4, row.getStudentNumber());
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement.setInt(1, "
                    + "row.getStudentNumber())", 
                         JOptionPane.ERROR_MESSAGE);
        } 

        try {
            rowsChanged = preparedStatement.executeUpdate();
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in rowsChanged = "
                    + "preparedStatement.executeUpdate()", 
                         JOptionPane.ERROR_MESSAGE);
        }
        
        if (rowsChanged == 1) {
            JOptionPane.showMessageDialog(null, 
                    "Record was successfuly updated!",
                    null, JOptionPane.INFORMATION_MESSAGE);
        } else if (rowsChanged != 1) {
            JOptionPane.showMessageDialog(null, 
                    "Record was not updated!",
                    null, JOptionPane.INFORMATION_MESSAGE);
        }    
    }
    
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/update.png")));
    }               
   
}

