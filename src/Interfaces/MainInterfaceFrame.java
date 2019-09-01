package Interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import Utilities.DatabaseConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

public class MainInterfaceFrame extends JFrame {

    // Components declaration block starts here
    private JMenuItem aboutItem;
    private JButton btnAdd;
    private JButton btnConnect;
    private JButton btnDelete;
    private JButton btnQuery;
    private JButton btnShow;
    private JButton btnUpdate;
    private JPanel connectPanel;
    private JMenuItem docItem;
    private JMenuItem exitItem;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JLabel lbAdd;
    private JLabel lbConnectTitle;
    private JLabel lbDb;
    private JLabel lbDbExample;
    private JLabel lbDelete;
    private JLabel lbDisplay;
    private JLabel lbHeader;
    private JLabel lbHost;
    private JLabel lbHostExample;
    private JLabel lbLogTitle;
    private JLabel lbOptCenter;
    private JLabel lbOptHeader;
    private JLabel lbOptTitle;
    private JLabel lbPassword;
    private JLabel lbPort;
    private JLabel lbPortExample;
    private JLabel lbQuery;
    private JLabel lbUpdate;
    private JLabel lbUsername;
    private JPanel logPanel;
    private JMenuBar menuBar;
    private JPanel optPanel;
    private JPanel panelAuthentication;
    private JScrollPane scrLog;
    private JTextArea txtaLog;
    private JTextField txtfDb;
    private JTextField txtfHost;
    private JPasswordField txtfPassword;
    private JTextField txtfPort;
    private JTextField txtfUsername;
    // Components declaration block ends here
    
    // Field variables block starts here
    private static final long serialVersionUID = 1L;
    private Connection connection = null;
    // Field variables block ends here

    public MainInterfaceFrame() throws IOException {
        initComponents();
        setIcon();
    }

    private void initComponents() {

        // Instantiation of components block starts here
        connectPanel = new JPanel();
        lbConnectTitle = new JLabel();
        panelAuthentication = new JPanel();
        txtfHost = new JTextField();
        txtfPort = new JTextField();
        lbPort = new JLabel();
        lbHostExample = new JLabel();
        lbHost = new JLabel();
        txtfDb = new JTextField();
        lbDbExample = new JLabel();
        lbPortExample = new JLabel();
        lbDb = new JLabel();
        lbUsername = new JLabel();
        txtfUsername = new JTextField();
        txtfPassword = new JPasswordField();
        lbPassword = new JLabel();
        btnConnect = new JButton();
        logPanel = new JPanel();
        scrLog = new JScrollPane();
        txtaLog = new JTextArea();
        lbLogTitle = new JLabel();
        optPanel = new JPanel();
        lbOptTitle = new JLabel();
        btnAdd = new JButton();
        btnShow = new JButton();
        btnDelete = new JButton();
        btnUpdate = new JButton();
        btnQuery = new JButton();
        lbOptHeader = new JLabel();
        lbOptCenter = new JLabel();
        lbAdd = new JLabel();
        lbDisplay = new JLabel();
        lbDelete = new JLabel();
        lbUpdate = new JLabel();
        lbQuery = new JLabel();
        lbHeader = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutItem = new JMenuItem();
        docItem = new JMenuItem();
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MySQL Database Application");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        // Frame block ends here

        // Header block starts here
        lbHeader.setIcon(new ImageIcon(getClass().getResource("/images/header.jpg")));
        lbHeader.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // Header block ends here

        // Connection panel block starts here
        connectPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        connectPanel.setPreferredSize(new Dimension(300, 271));
        lbConnectTitle.setFont(new Font("Tahoma", 1, 18));
        lbConnectTitle.setText("Connection Menu");
        btnConnect.setText("Connect to MySQL Database");
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        // Connection panel block ends here

        // Authentication panel block starts here
        panelAuthentication.setBorder(BorderFactory.createEtchedBorder());
        lbPort.setText("Port Number");
        lbHostExample.setText("Example: localhost");
        lbHost.setText("Host Name");
        lbDbExample.setText("Example: mydb");
        lbPortExample.setText("Default: 3306");
        lbDb.setText("Database");
        lbUsername.setText("Username");
        lbPassword.setText("Password");
        // Authentication panel block ends here

        // Log panel block starts here
        logPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        txtaLog.setEditable(false);
        txtaLog.setBackground(new Color(204, 255, 255));
        txtaLog.setColumns(20);
        txtaLog.setFont(new Font("Tahoma", 1, 10));
        txtaLog.setForeground(new Color(204, 0, 0));
        txtaLog.setRows(5);
        scrLog.setViewportView(txtaLog);
        lbLogTitle.setFont(new Font("Tahoma", 1, 18));
        lbLogTitle.setText("Connection Log Screen");
        // Log panel block ends here

        // Operations panel block starts here
        optPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        optPanel.setPreferredSize(new Dimension(300, 233));
        lbOptTitle.setFont(new Font("Tahoma", 1, 18));
        lbOptTitle.setText("Database Operations");
        btnAdd.setText("Add Student Record");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        btnShow.setText("Display Student Record");
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });
        btnDelete.setText("Delete Student Record");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        btnUpdate.setText("Update Student Record");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        btnQuery.setText("Select Query MySQL");
        btnQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnQueryActionPerformed(evt);
            }
        });
        lbOptHeader.setIcon(new ImageIcon(getClass().getResource("/images/mysql_logo.png")));
        lbOptCenter.setIcon(new ImageIcon(getClass().getResource("/images/database.png")));
        lbAdd.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        lbDisplay.setIcon(new ImageIcon(getClass().getResource("/images/find.png")));
        lbDelete.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
        lbUpdate.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
        lbQuery.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
        // Operations panel block ends here

        // Menu items block starts here
        fileMenu.setText("File");
        exitItem.setText("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        helpMenu.setText("Help");
        aboutItem.setText("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                aboutItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutItem);
        docItem.setText("Documentation");
        docItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                docItemActionPerformed(evt);
            }
        });
        helpMenu.add(docItem);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        // Menu items block ends here

        // Authentication panel layout design starts here
        GroupLayout panelAuthenticationLayout = new GroupLayout(panelAuthentication);
        panelAuthentication.setLayout(panelAuthenticationLayout);
        panelAuthenticationLayout.setHorizontalGroup(
                panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelAuthenticationLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(lbPort)
                .addComponent(lbHost)
                .addComponent(lbUsername)
                .addComponent(lbPassword)
                .addComponent(lbDb))
                .addGap(18, 18, 18)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbPortExample)
                .addComponent(lbDbExample)
                .addComponent(lbHostExample)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(txtfHost, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addComponent(txtfUsername)
                .addComponent(txtfPassword)
                .addComponent(txtfDb)
                .addComponent(txtfPort)))
                .addGap(0, 20, Short.MAX_VALUE)));

        panelAuthenticationLayout.setVerticalGroup(
                panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelAuthenticationLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtfHost, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbHost))
                .addGap(5, 5, 5)
                .addComponent(lbHostExample)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lbDb)
                .addComponent(txtfDb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDbExample)
                .addGap(4, 4, 4)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lbPort)
                .addComponent(txtfPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(lbPortExample)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(txtfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbUsername))
                .addGap(18, 18, 18)
                .addGroup(panelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lbPassword)
                .addComponent(txtfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE)));
        // Authentication panel layout design ends here

        // Connection panel layout design starts here
        GroupLayout connectPanelLayout = new GroupLayout(connectPanel);
        connectPanel.setLayout(connectPanelLayout);
        connectPanelLayout.setHorizontalGroup(
                connectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(panelAuthentication, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
                .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(lbConnectTitle)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(btnConnect)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        connectPanelLayout.setVerticalGroup(
                connectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbConnectTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelAuthentication, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnect)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        // Authentication panel layout design ends here

        // Log panel layout design starts here
        GroupLayout logPanelLayout = new GroupLayout(logPanel);
        logPanel.setLayout(logPanelLayout);
        logPanelLayout.setHorizontalGroup(
                logPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(logPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lbLogTitle)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, logPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrLog)
                .addContainerGap()));

        logPanelLayout.setVerticalGroup(
                logPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, logPanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lbLogTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrLog, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        // Log panel layout design ends here

        // Operations panel layout design starts here
        GroupLayout optPanelLayout = new GroupLayout(optPanel);
        optPanel.setLayout(optPanelLayout);
        optPanelLayout.setHorizontalGroup(
                optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbQuery, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGroup(optPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, optPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbDisplay, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(optPanelLayout.createSequentialGroup()
                .addComponent(lbOptHeader, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnShow, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
                .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnQuery, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                .addComponent(lbOptCenter, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))))
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(lbOptTitle)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        optPanelLayout.setVerticalGroup(
                optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(optPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbOptTitle)
                .addGap(18, 18, 18)
                .addComponent(lbOptHeader, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbDisplay, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnShow, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnQuery, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbQuery, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
                .addGroup(optPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbOptCenter, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        // Operations panel layout design ends here

        // Frame layout design starts here
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(lbHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(connectPanel, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addComponent(logPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optPanel, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHeader, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                .addComponent(logPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectPanel, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
                .addComponent(optPanel, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        // Frame layout design starts here

        pack();
    }

    private void btnConnectActionPerformed(ActionEvent evt) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.setPassword(txtfPassword.getPassword());
        databaseConnection.setHostName(txtfHost.getText());
        databaseConnection.setDataBase(txtfDb.getText());
        databaseConnection.setPortNumber(txtfPort.getText());
        databaseConnection.setUserName(txtfUsername.getText());

        txtaLog.setText("");

        try {
            txtaLog.append("Connecting to "
                    + databaseConnection.getDatabaseURL() + " . . .\n");
            databaseConnection.setConnection();
            this.connection = databaseConnection.getConnection();
            txtaLog.append("*****Connection Successful*****\n");
        } catch (SQLException e) {
            txtaLog.append(e.toString());
        } catch (ClassNotFoundException e) {
            txtaLog.append(e.toString());
        }
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/data.png")));
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {

        if (this.connection != null) {
            NewRecordFrame newRecord = new NewRecordFrame(connection);
            newRecord.setResizable(false);
            newRecord.setLocationRelativeTo(this);
            ImageIcon ImageIcon =
                    new ImageIcon(getClass().getResource("/images/add.png"));
            Image Image = ImageIcon.getImage();
            newRecord.setIconImage(Image);
            newRecord.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Connection is not established!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnDeleteActionPerformed(ActionEvent evt) {

        if (this.connection != null) {
            DeleteRecordFrame deleteRecordFrame = new DeleteRecordFrame(connection);
            deleteRecordFrame.setVisible(true);
            deleteRecordFrame.setLocationRelativeTo(null);
            deleteRecordFrame.setResizable(false);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Connection is not established!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnShowActionPerformed(ActionEvent evt) {

        if (this.connection != null) {
            ShowRecordFrame showRecordFrame = new ShowRecordFrame(connection);
            showRecordFrame.setVisible(true);
            showRecordFrame.setLocationRelativeTo(this);
            showRecordFrame.setResizable(false);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Connection is not established!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aboutItemActionPerformed(ActionEvent evt) {

        AboutFrame aboutFrame = new AboutFrame();
        aboutFrame.setLocationRelativeTo(this);
        aboutFrame.setResizable(false);
        aboutFrame.setVisible(true);
    }

    private void btnQueryActionPerformed(ActionEvent evt) {


        if (this.connection != null) {
            MultiTableFrame frame = new MultiTableFrame(this.getConnection());
            frame.setLocationRelativeTo(this);
            frame.setResizable(false);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Connection is not established!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void docItemActionPerformed(ActionEvent evt) {

        DocumentationFrame doc = new DocumentationFrame();
        doc.setLocationRelativeTo(this);
        doc.setResizable(true);
        doc.setVisible(true);
    }

    private void btnUpdateActionPerformed(ActionEvent evt) {

        if (this.connection != null) {
            UpdateRecordFrame frame =
                    new UpdateRecordFrame(this.getConnection());
            frame.setLocationRelativeTo(this);
            frame.setResizable(false);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Connection is not established!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formWindowClosing(WindowEvent evt) {

        JFrame frame = (JFrame) evt.getSource();

        int result = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to exit the application?",
                "Exit Application",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(),
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    private void exitItemActionPerformed(ActionEvent evt) {

        int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit the application?",
                "Exit Application",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(),
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }

            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Failed to set look and feel", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Failed to set look and feel", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Failed to set look and feel", JOptionPane.ERROR_MESSAGE);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Failed to set look and feel", JOptionPane.ERROR_MESSAGE);
        }

        MainInterfaceFrame mainframe = new MainInterfaceFrame();
        mainframe.setVisible(true);
        mainframe.setLocationRelativeTo(null);

    }

    public Connection getConnection() {
        return connection;
    }
}
