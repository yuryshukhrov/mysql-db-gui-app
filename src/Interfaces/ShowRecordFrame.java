package Interfaces;

import Utilities.JaxbFileHandler;
import Utilities.Student;
import Utilities.StudentTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.JAXBException;

public class ShowRecordFrame extends JFrame {

	// Components declaration block starts here
	private JButton btnFilter;
    private JButton btnPrint;
    private JButton btnXML;
    private JPanel filterPanel;
    private JLabel lbFilter1;
    private JLabel lbFilter2;
    private JLabel lbTitle;
    private JScrollPane scrTable;
    private JTable table;
    private JTextField txfFilter;
    // Components declaration block ends here
 
    // Field variables block starts here
	private static final long serialVersionUID = 1L;
	private StudentTableModel tableModel;
    private final static String query = "SELECT * FROM Students";
    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private ResultSetMetaData resultSetMetaData = null;
    private TableRowSorter<StudentTableModel> sorter;
    private ArrayList<String> columnNames = new ArrayList<>();
    private ArrayList<Student> data = new ArrayList<>();
    // Field variables block ends here

    public ShowRecordFrame(Connection connection) {

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
        filterPanel = new JPanel();
        lbFilter1 = new JLabel();
        txfFilter = new JTextField();
        btnFilter = new JButton();
        btnPrint = new JButton();
        btnXML = new JButton();
        lbFilter2 = new JLabel();
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Display Records from Students Table");
        // Frame block ends here

        // Table block starts here
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.blue);
        tableModel = new StudentTableModel(data, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setFont(new Font("Arial", 0, 12));
        table.setModel(tableModel);
        scrTable.setViewportView(table);
        // Table block ends here

        // Title block starts here
        lbTitle.setFont(new Font("Arial", 1, 24));
        lbTitle.setText("Students Table Records");
        // Title block ends here

        // Filter panel components block starts here
        filterPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFilter1.setText("Enter keyword to filter");
        lbFilter2.setText("To reset filter leave this field empty and press Filter");
        txfFilter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfFilterActionPerformed(evt);
            }
        });
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        btnPrint.setText("Print Table");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        btnXML.setText("Save As XML");
        btnXML.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXMLActionPerformed(evt);
            }
        });
        // Filter panel components block ends here

        
        // Filter panel layout design start here
        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXML, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFilter2)
                    .addComponent(lbFilter1)
                    .addGroup(filterPanelLayout.createSequentialGroup()
                        .addComponent(txfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbFilter1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFilter2)
                .addGap(20, 20, 20)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnXML))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        // Filter panel layout design ends here

        // Frame layout design starts here
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrTable))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(lbTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(scrTable, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );   
        // Frame layout design ends here

        pack();
    }                   

    private void btnXMLActionPerformed(java.awt.event.ActionEvent evt) {                                       

        JFrame parentFrame = new JFrame();
        parentFrame.setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/save_as.png")));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter
                ("XML Files", "xml"));
        fileChooser.setDialogTitle("Save Table as");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath  = file.getPath();
            
            if (!filePath.toLowerCase().endsWith(".xml")) {
                file = new File(filePath + ".xml");
            }

            try {
                JaxbFileHandler.importXML(tableModel.getTableContent(),
                        file);
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.toString(),
                        "Error in serializing table to XML format",
                        JOptionPane.ERROR_MESSAGE);
            } catch (JAXBException jAXBException) {
                JOptionPane.showMessageDialog(null, jAXBException.toString(),
                        "Error in serializing table to XML format",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                      

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {                                         

        try {
            boolean complete = table.print(
                    JTable.PrintMode.NORMAL, null, null);

            if (complete) {
                JOptionPane.showMessageDialog(null,
                        "Table was successfult printer!\n",
                        null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Table was not printed!\n",
                        null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException printerException) {
            JOptionPane.showMessageDialog(null, printerException.toString(),
                    "Some problem cause printer exception to be raised!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {                                          

        String text = txfFilter.getText();
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

    private void txfFilterActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
       String text = txfFilter.getText();
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

    private void prepareTable() {

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

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/find.png")));
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
}

