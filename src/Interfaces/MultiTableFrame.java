package Interfaces;

import Utilities.GeneralTableModel;
import Utilities.TableInformation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class MultiTableFrame extends JFrame
        implements TreeSelectionListener {

    // Components declaration block starts here
    private JButton btnFilter;
    private JButton btnPerformQuery;
    private JButton btnResetQuery;
    private JScrollPane scrQuery;
    private JScrollPane scrTree;
    private JScrollPane scrTable;
    private JLabel lbFilter1;
    private JLabel lbFilter2;
    private JLabel lbQuery;
    private JPanel panelFilter;
    private JPanel panelQuery;
    private JTable table;
    private JTree tree;
    private JTextArea txtaQuery;
    private JTextField txtfFilter;
    // Components declaration block ends here
    // Field variables block starts here
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private TableInformation tableInformation;
    private ArrayList<String> tableNames;
    private GeneralTableModel tableModel;
    private TableRowSorter<GeneralTableModel> tableSorter;
    private DefaultMutableTreeNode top;
    // Field variables block ends here

    public MultiTableFrame(Connection connection) {

        this.connection = connection;

        top = new DefaultMutableTreeNode("Database");
        tableInformation = new TableInformation(connection);

        try {
            tableNames = tableInformation.getTableNames();
            createNodes(top);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in getting table names",
                    e.toString(), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error in getting table names",
                    e.toString(), JOptionPane.ERROR_MESSAGE);
        }

        initComponents();
        setIcon();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            MultiTableFrame.TreeNode tableNode =
                    (MultiTableFrame.TreeNode) nodeInfo;

            tableModel = new GeneralTableModel(connection,
                    "SELECT * FROM " + tableNode.tableName);
            table.setModel(tableModel);

            tableSorter = new TableRowSorter<>(tableModel);
            table.setRowSorter(tableSorter);
        }
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/table.png")));
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DocumentationFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Couldn't find file: " + path,
                    null, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private class TreeNode {

        public String tableName;

        public TreeNode(String table) {
            tableName = table;
        }

        @Override
        public String toString() {
            return tableName;
        }
    }

    private void createNodes(DefaultMutableTreeNode top)
            throws SQLException {
        DefaultMutableTreeNode category;
        DefaultMutableTreeNode tableName;

        category = new DefaultMutableTreeNode("Tables");
        top.add(category);

        for (int i = 0; i < tableNames.size(); i++) {
            tableName = new DefaultMutableTreeNode((new MultiTableFrame.TreeNode(tableNames.get(i))));
            category.add(tableName);
        }
    }

    private void initComponents() {

        // Instantiation of components block starts here
        scrTable = new JScrollPane();
        table = new JTable();
        panelQuery = new JPanel();
        scrQuery = new JScrollPane();
        txtaQuery = new JTextArea();
        lbQuery = new JLabel();
        btnPerformQuery = new JButton();
        btnResetQuery = new JButton();
        panelFilter = new JPanel();
        lbFilter1 = new JLabel();
        txtfFilter = new JTextField();
        lbFilter2 = new JLabel();
        btnFilter = new JButton();
        scrTree = new JScrollPane();
        tree = new JTree(top);
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("All Tables From User Defined Database");
        // Frame block ends here

        // Table block starts here
        tableModel = new Utilities.GeneralTableModel(connection,
                "SELECT * FROM " + tableNames.get(0));
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.blue);
        table.setAutoCreateRowSorter(true);
        table.setFont(new java.awt.Font("Arial", 0, 12));
        table.setModel(tableModel);
        scrTable.setViewportView(table);
        // Tree block ends here

        // SELECT Query panel block starts here
        panelQuery.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        txtaQuery.setColumns(20);
        txtaQuery.setFont(new Font("Arial", 1, 12));
        txtaQuery.setForeground(new Color(0, 0, 255));
        txtaQuery.setLineWrap(true);
        txtaQuery.setRows(5);
        scrQuery.setViewportView(txtaQuery);
        lbQuery.setText("Enter SELECT Query ");
        btnPerformQuery.setText("Perform Select Query");
        btnPerformQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnPerformQueryActionPerformed(evt);
            }
        });
        btnResetQuery.setText("Reset Query Result");
        btnResetQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnResetQueryActionPerformed(evt);
            }
        });
        // SELECT Query panel block ends here

        // Filter panel block starts here
        panelFilter.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        lbFilter1.setText("Enter keyword to filter");
        txtfFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtfFilterActionPerformed(evt);
            }
        });
        lbFilter2.setText("To reset filter leave this field empty and press Filter");
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        // Filter panel block ends here

        // Tree block starts here
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        ImageIcon leafIcon = createImageIcon("/images/tables.png");
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setLeafIcon(leafIcon);
            tree.setCellRenderer(renderer);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Leaf icon missing!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
        tree.addTreeSelectionListener(this);
        scrTree.setViewportView(tree);
        // Tree block ends here

        // SELECT Query panel layout design starts here
        GroupLayout panelQueryLayout = new GroupLayout(panelQuery);
        panelQuery.setLayout(panelQueryLayout);
        panelQueryLayout.setHorizontalGroup(
                panelQueryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelQueryLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelQueryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbQuery)
                .addComponent(scrQuery, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelQueryLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnPerformQuery, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResetQuery, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)));

        panelQueryLayout.setVerticalGroup(
                panelQueryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelQueryLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbQuery)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(scrQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(panelQueryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnPerformQuery)
                .addComponent(btnResetQuery))
                .addContainerGap(20, Short.MAX_VALUE)));
        // SELECT Query panel layout design starts here

        // Filter panel layout design starts here
        GroupLayout panelFilterLayout = new GroupLayout(panelFilter);
        panelFilter.setLayout(panelFilterLayout);
        panelFilterLayout.setHorizontalGroup(
                panelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbFilter1)
                .addComponent(lbFilter2)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addComponent(txtfFilter, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        panelFilterLayout.setVerticalGroup(
                panelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelFilterLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbFilter1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFilterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtfFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnFilter))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFilter2)
                .addContainerGap(20, Short.MAX_VALUE)));
        // Filter panel layout design ends here

        // Frame layout design starts here
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(scrTree, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(panelFilter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(scrTree)
                .addGroup(layout.createSequentialGroup()
                .addComponent(scrTable, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(panelQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20)));
        // Frame layout design ends here

        pack();
    }

    private void txtfFilterActionPerformed(ActionEvent evt) {

        String text = txtfFilter.getText();
        tableSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableSorter);

        if (text.length() == 0) {
            tableSorter.setRowFilter(null);
        } else {
            try {
                tableSorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                JOptionPane.showMessageDialog(null, pse.toString(),
                        "Bad regex pattern",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnFilterActionPerformed(ActionEvent evt) {

        String text = txtfFilter.getText();
        tableSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableSorter);

        if (text.length() == 0) {
            tableSorter.setRowFilter(null);
        } else {
            try {
                tableSorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                JOptionPane.showMessageDialog(null, pse.toString(),
                        "Bad regex pattern",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnPerformQueryActionPerformed(ActionEvent evt) {

        String query = txtaQuery.getText();
        tableModel = new GeneralTableModel(connection, query);

        String text = txtfFilter.getText();
        tableSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableSorter);

        if (text.length() == 0) {
            tableSorter.setRowFilter(null);
        } else {
            try {
                tableSorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                JOptionPane.showMessageDialog(null, pse.toString(),
                        "Bad regex pattern",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnResetQueryActionPerformed(ActionEvent evt) {

        txtaQuery.setText("");

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            MultiTableFrame.TreeNode tableNode =
                    (MultiTableFrame.TreeNode) nodeInfo;

            tableModel = new GeneralTableModel(connection,
                    "SELECT * FROM " + tableNode.tableName);
            table.setModel(tableModel);

            tableSorter = new TableRowSorter<>(tableModel);
            table.setRowSorter(tableSorter);
        }
    }
}