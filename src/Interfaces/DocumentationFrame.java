package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class DocumentationFrame extends JFrame
        implements TreeSelectionListener {

    // Components declaration block starts here
    private JEditorPane htmlPane;
    private JScrollPane htmlScrPane;
    private JTree tree;
    private JScrollPane treeScrPane;
    // Components declaration block ends here
    // Field variables block starts here
    private static final long serialVersionUID = 1L;
    private URL helpURL;
    private static boolean DEBUG = false;
    private DefaultMutableTreeNode top;
    // Field variables block ends here

    public DocumentationFrame() {
        top = new DefaultMutableTreeNode("Help Content");
        createNodes(top);
        initComponents();
        setIcon();
    }

    private void initComponents() {

        // Instantiation of components block starts here
        treeScrPane = new JScrollPane();
        tree = new JTree(top);
        htmlScrPane = new JScrollPane();
        htmlPane = new JEditorPane();
        // Instantiation of components block ends here

        // Frame block starts here
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Simple Guide How To Use MySQL Database Application");
        setPreferredSize(new Dimension(1000, 600));
        // Frame block ends here

        // Tree block starts here
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        ImageIcon leafIcon = createImageIcon("/help/leaf_icon.gif");
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setLeafIcon(leafIcon);
            tree.setCellRenderer(renderer);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Leaf icon missing!",
                    null, JOptionPane.ERROR_MESSAGE);
        }
        tree.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        tree.setMaximumSize(new Dimension(150, 100));
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent evt) {
                treeValueChanged(evt);
            }
        });
        treeScrPane.setViewportView(tree);
        // Tree block ends here

        // HTML pane block starts here
        htmlPane.setEditable(false);
        htmlPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        htmlPane.setPreferredSize(new java.awt.Dimension(200, 40));
        htmlScrPane.setViewportView(htmlPane);
        initHelp();
        // HTML pane block ends here

        // Frame layout design block starts here
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(treeScrPane, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(htmlScrPane, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addContainerGap()));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(htmlScrPane)
                .addComponent(treeScrPane, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
                .addContainerGap()));
        // Frame layout design block ends here

        pack();
    }

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            NodeInfo helpNode = (NodeInfo) nodeInfo;
            displayURL(helpNode.nodeURL);
            if (DEBUG) {
                JOptionPane.showMessageDialog(null,
                        helpNode.nodeURL + ":  \n    ",
                        null, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            displayURL(helpURL);
        }
        if (DEBUG) {
            JOptionPane.showMessageDialog(null,
                    nodeInfo.toString(),
                    null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/help/doc_icon.png")));
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

    private class NodeInfo {

        public String nodeName;
        public URL nodeURL;

        public NodeInfo(String nodeName, String filename) {
            this.nodeName = nodeName;
            nodeURL = getClass().getResource(filename);
            if (nodeURL == null) {
                JOptionPane.showMessageDialog(null,
                        "Couldn't find file: " + filename,
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public String toString() {
            return nodeName;
        }
    }

    private void initHelp() {
        String s = "/help/welcome_page.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            JOptionPane.showMessageDialog(null,
                    "Couldn't open help file: " + s,
                    null, JOptionPane.ERROR_MESSAGE);
        } else if (DEBUG) {
            JOptionPane.showMessageDialog(null,
                    "Help URL is " + helpURL,
                    null, JOptionPane.INFORMATION_MESSAGE);
        }

        displayURL(helpURL);
    }

    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
                htmlPane.setText("File Not Found");
                if (DEBUG) {
                    JOptionPane.showMessageDialog(null,
                            "Attempted to display a null URL.",
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Attempted to read a bad URL: " + url,
                    e.toString(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode helpNode = null;

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("How to connect to MySQL database",
                "/help/how_to_connect.html"));
        top.add(helpNode);

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("About table Students",
                "/help/about_students_table.html"));
        top.add(helpNode);

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("How to add new record into table Students",
                "/help/add_new_record.html"));
        top.add(helpNode);

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("How to show records from table Students", "/help/show_student_records.html"));
        top.add(helpNode);

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("How to delete records from table Students", "/help/delete_student_records.html"));
        top.add(helpNode);

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("How to update records from Students", "/help/update_student_records.html"));
        top.add(helpNode);

        helpNode = new DefaultMutableTreeNode(
                new NodeInfo("How to use SELECT query", "/help/select_query.html"));
        top.add(helpNode);
    }
}
