package Interfaces;

import Utilities.Student;
import Utilities.StudentTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class DeleteRecordFrame extends JFrame {

    // Components declaration block starts here
    private JButton btnDelete;
    private JButton btnFilter;
    private JLabel lbDelete1;
    private JLabel lbDelete2;
    private JLabel lbDelete3;
    private JLabel lbDelete4;
    private JLabel lbFilter1;
    private JLabel lbFilter2;
    private JLabel lbTitle;
    private JPanel panelDelete;
    private JPanel panelFilter;
    private JScrollPane scrTable;
    private JTable table;
    private JTextField txtfFilter;
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
    private ArrayList<Student> data = new ArrayList<>();
    ;
    private StudentTableModel tableModel;
    private TableRowSorter<StudentTableModel> sorter;
    private int rowsChanged = -1;
    // Field variables block ends here

    public DeleteRecordFrame(Connection connection) {

        this.connection = connection;
        prepareTable();
        initComponents();
        setIcon();
    }

    private void initComponents() {

        // Instantiation of components block starts here
        scrTable = new JScrollPane();
        table = new JTable();
        lbTitle = new JLabel();
        panelFilter = new JPanel();
        lbFilter1 = new JLabel();
        txtfFilter = new JTextField();
        btnFilter = new JButton();
        lbFilter2 = new JLabel();
        panelDelete = new JPanel();
        lbDelete1 = new JLabel();
        lbDelete2 = new JLabel();
        lbDelete3 = new JLabel();
        lbDelete4 = new JLabel();
        btnDelete = new JButton();
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete Record from Students");
        // Frame block ends here

        // Table model block starts here
        tableModel = new Utilities.StudentTableModel(data, columnNames);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.blue);
        table.setAutoCreateRowSorter(true);
        table.setFont(new Font("Arial", 0, 12));
        table.setModel(tableModel);
        scrTable.setViewportView(table);
        // Table model block ends here

        // Title block starts here
        lbTitle.setFont(new Font("Arial", 1, 24));
        lbTitle.setText("Students Table");
        // Title block ends here

        // Filter panel components and event listeners start here
        panelFilter.setBorder(BorderFactory.
                createLineBorder(new Color(0, 0, 0)));
        lbFilter1.setText("Enter keyword to filter");
        lbFilter2.setText(""
                + "To reset filter leave this field empty and press Filter");
        txtfFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtfFilterActionPerformed(evt);
            }
        });
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        // Filter panel components and event listeners end here

        // Filter panel layout design starts here
        GroupLayout panelFilterLayout = new GroupLayout(panelFilter);
        panelFilter.setLayout(panelFilterLayout);
        panelFilterLayout.setHorizontalGroup(
                panelFilterLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelFilterLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addComponent(lbFilter1)
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addGroup(panelFilterLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbFilter2)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addComponent(txtfFilter, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));

        panelFilterLayout.setVerticalGroup(
                panelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbFilter1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFilterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtfFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnFilter))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFilter2)
                .addContainerGap(21, Short.MAX_VALUE)));
        // Filter panel layout design ends here

        // Delete panel components and event listeners start here
        panelDelete.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        lbDelete1.setText("Select records you wish to delete and press Delete");
        lbDelete2.setText("You can delete multiple records by holding CTRL and");
        lbDelete3.setText("selecting the records. If you want to delete all records");
        lbDelete4.setText("press CTRL + A or hold SHIFT and select last row");
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/images/data_delete.png")));
        btnDelete.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        // Delete panel components and event listeners end here

        // Delete panel layout design starts here
        GroupLayout panelDeleteLayout = new GroupLayout(panelDelete);
        panelDelete.setLayout(panelDeleteLayout);
        panelDeleteLayout.setHorizontalGroup(
                panelDeleteLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDeleteLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbDelete1)
                .addComponent(lbDelete3)
                .addComponent(lbDelete4)
                .addComponent(lbDelete2))
                .addGap(31, 31, 31)
                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        panelDeleteLayout.setVerticalGroup(
                panelDeleteLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelDeleteLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelDeleteLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(btnDelete)
                .addGroup(panelDeleteLayout.createSequentialGroup()
                .addComponent(lbDelete1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDelete2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDelete3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDelete4)))
                .addContainerGap(23, Short.MAX_VALUE)));
        // Filter panel layout design ends here

        // Frame layout design starts here
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(lbTitle)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(scrTable, GroupLayout.Alignment.TRAILING)
                .addComponent(panelFilter, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDelete, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(scrTable, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(panelFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(panelDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE)));
        // Frame layout design ends here

        pack();
    }

    private void setIcon() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/images/delete.png")));
    }

    private void btnDeleteActionPerformed(ActionEvent evt) {

        int[] row = table.getSelectedRows();
        for (int r : row) {
            if (r != -1) {
                r = table.convertRowIndexToModel(r);
                deleteQuery(tableModel.getRow(r));
                tableModel.removeRow(r);
            }
        }
    }

    private void btnFilterActionPerformed(ActionEvent evt) {

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
                        "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void txtfFilterActionPerformed(ActionEvent evt) {

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
                        "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void prepareTable() {

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.toString(),
                    "Error in executing query!", JOptionPane.ERROR_MESSAGE);
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

    public void removeSelectedRows(JTable table) {
        int[] rows = table.getSelectedRows();
        for (int r : rows) {
            deleteQuery(tableModel.getRow(r));
            tableModel.removeRow(r);
        }
    }

    private void deleteQuery(Student row) {
        String deleteQuery = "DELETE FROM Students WHERE "
                + "sn = ? AND name = ? AND address = ?";
        try {
            preparedStatement = connection.prepareStatement(deleteQuery);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                    "Error in preparedStatement = "
                    + "connection.prepareStatement(deleteQuery)",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            preparedStatement.setInt(1, row.getStudentNumber());
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                    "Error in preparedStatement.setInt(1, "
                    + "row.getStudentNumber())",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            preparedStatement.setString(2, row.getFullName());
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                    "Error in preparedStatement.setString(2, "
                    + "row.getFullName())", JOptionPane.ERROR_MESSAGE);
        }
        try {
            preparedStatement.setString(3, row.getHomeAddress());
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, sQLException.toString(),
                    "Error in preparedStatement.setString(3, "
                    + "row.getHomeAddress())",
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
                    "Record was successfuly deleted!", null,
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (rowsChanged != 1) {
            JOptionPane.showMessageDialog(null, "Record was not deleted!",
                    null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
