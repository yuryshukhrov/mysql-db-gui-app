package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class NewRecordFrame extends JFrame {
	
	// Components declaration block starts here
	private JButton btnAdd;
    private JLabel lbAddress;
    private JLabel lbName;
    private JLabel lbNumber;
    private JLabel lbTitle;
    private JPanel mainPanel;
    private JTextField txtfAddress;
    private JTextField txtfName;
    private JTextField txtfNumber;
    // Components declaration block ends here
    
    // Field variables block starts here
	private static final long serialVersionUID = 1L;
	private final static String insertQuery = 
            "INSERT INTO Students VALUES(?, ?, ?)";
    private PreparedStatement preparedStatement;
    private Connection connection;
    int updateResult = -1;
    // Field variables block ends here

    public NewRecordFrame(Connection connection) {
    	
        initComponents();
        this.connection = connection;
        setIcon();
    }
                
    private void initComponents() {

    	// Instantiation of components block starts here
        mainPanel = new JPanel();
        lbNumber = new JLabel();
        lbName = new JLabel();
        lbAddress = new JLabel();
        lbTitle = new JLabel();
        txtfAddress = new JTextField();
        txtfName = new JTextField();
        txtfNumber = new JTextField();
        btnAdd = new JButton();
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Record to Students Table");
        // Frame block starts here

        // Main panel components block starts here
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        lbNumber.setFont(new Font("Arial", 1, 12));
        lbNumber.setText("Student Number");
        lbName.setFont(new Font("Arial", 1, 12)); 
        lbName.setText("Student Name");
        lbAddress.setFont(new Font("Arial", 1, 12)); 
        lbAddress.setText("Student Address");
        btnAdd.setText("Add New Record");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        lbTitle.setFont(new Font("Arial", 1, 24)); 
        lbTitle.setText("New Student Record");
        // Main panel components block ends here
        

        // Main panel layout design starts here
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbNumber)
                    .addComponent(lbName)
                    .addComponent(lbAddress))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtfAddress, GroupLayout.Alignment.LEADING)
                    .addComponent(txtfName, GroupLayout.Alignment.LEADING)
                    .addComponent(txtfNumber))
                .addGap(20, 20, 20))
            .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addGap(76, 76, 76))
        );
        
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbNumber, GroupLayout.Alignment.TRAILING)
                    .addComponent(txtfNumber, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lbName)
                    .addComponent(txtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lbAddress)
                    .addComponent(txtfAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        // Main panel layout design ends here

        // Frame layout design starts here
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        // Frame layout design ends here

        pack();
    }                 

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
        
        if (txtfName.getText().isEmpty() || txtfAddress.getText().isEmpty()
                || txtfNumber.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, 
                    "Please fill in all fields",
                    "Information Message",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                preparedStatement = connection.prepareStatement(insertQuery);
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement = "
                        + "connection.prepareStatement(insertQuery)",
                        JOptionPane.ERROR_MESSAGE);
            }
            try {
                preparedStatement.setInt(1,
                        Integer.parseInt(txtfNumber.getText()));
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement."
                        + "setInt(1, Integer.parseInt(txtfName.getText()))",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.toString(),
                        "Student number should be integer!",
                        JOptionPane.ERROR_MESSAGE);
            }
            try {
                preparedStatement.setString(2, txtfName.getText());
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement.setString(2, "
                        + "txtfAddress.getText())",
                        JOptionPane.ERROR_MESSAGE);
            }
            try {
                preparedStatement.setString(3, txtfAddress.getText());
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in preparedStatement.setString(3, "
                        + "txtfNumber.getText())",
                        JOptionPane.ERROR_MESSAGE);
            }
            try {
                updateResult = preparedStatement.executeUpdate();
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, sQLException.toString(),
                        "Error in updateResult = "
                        + "preparedStatement.executeUpdate()",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (updateResult == 1) {
                JOptionPane.showMessageDialog(null,
                        "Record was successfully added!",
                        "Information Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (updateResult != 1) {
                JOptionPane.showMessageDialog(null,
                        "Failed to add new record!",
                        "Information Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            
            txtfNumber.setText("");
            txtfName.setText("");
            txtfAddress.setText("");
        }
    }                                      

    private void setIcon() { 
    	
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/add.png")));
    }   
}

