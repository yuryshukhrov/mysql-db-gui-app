package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class AboutFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // Components declaration block starts here
    private JPanel infoPanel;
    private JLabel lbAuthor;
    private JLabel lbDegree;
    private JLabel lbGroup;
    private JLabel lbMail;
    private JLabel lbMir;
    private JLabel lbSinichka;
    private JLabel lbStudentNumber;
    private JLabel lbVersion;
    // Components declaration block ends here

    public AboutFrame() {
        initComponents();
        setIcon();
    }

    private void initComponents() {

        // Instantiation of components block starts here
        lbSinichka = new JLabel();
        infoPanel = new JPanel();
        lbAuthor = new JLabel();
        lbStudentNumber = new JLabel();
        lbGroup = new JLabel();
        lbDegree = new JLabel();
        lbMail = new JLabel();
        lbVersion = new JLabel();
        lbMir = new JLabel();
        // Instantiation of components block ends here

        // Frame properties starts here
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About MySQL Database Application");
        setBackground(new Color(255, 255, 255));
        // Frame properties ends here

        // Sinichka&Samovar picture block starts here
        lbSinichka.setIcon(new ImageIcon(getClass().getResource("/About/sinichka.jpg")));
        lbSinichka.setBorder(BorderFactory.
                createLineBorder(new java.awt.Color(0, 0, 0)));
        // Sinichka&Samovar picture block ends here

        // Information panel components block starts here
        infoPanel.setBorder(BorderFactory.
                createLineBorder(new java.awt.Color(0, 0, 0)));
        lbAuthor.setFont(new Font("Arial", 1, 11));
        lbAuthor.setText("Author: Yuri Shukhrov");
        lbStudentNumber.setFont(new Font("Arial", 1, 11));
        lbStudentNumber.setText("Student ID: 1005165");
        lbGroup.setFont(new Font("Arial", 1, 11));
        lbGroup.setText("Group: TI10S1");
        lbDegree.setFont(new Font("Arial", 1, 11));
        lbDegree.setText("Degree Program: Information Technology");
        lbMail.setFont(new Font("Arial", 1, 11));
        lbMail.setText("E-Mail: Yury.Shukhrov@Metropolia.fi");
        lbVersion.setFont(new Font("Arial", 1, 11));
        lbVersion.setText("Version: 1.0");
        lbMir.setIcon(new ImageIcon(getClass().
                getResource("/About/mir.png")));
        // Information panel components block ends here

        // Information panel layout design starts here
        GroupLayout infoPanelLayout = new GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);

        infoPanelLayout.setHorizontalGroup(
                infoPanelLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoPanelLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbGroup)
                .addComponent(lbStudentNumber)
                .addComponent(lbAuthor)
                .addComponent(lbDegree)
                .addComponent(lbVersion)
                .addComponent(lbMail))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbMir, GroupLayout.PREFERRED_SIZE, 180,
                GroupLayout.PREFERRED_SIZE)));

        infoPanelLayout.setVerticalGroup(
                infoPanelLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.
                createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(lbAuthor)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStudentNumber)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGroup)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDegree)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbVersion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMail)
                .addGap(0, 9, Short.MAX_VALUE))
                .addComponent(lbMir, GroupLayout.Alignment.TRAILING,
                GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap()));
        // Information panel layout design ends here

        // Frame layout design block starts here
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.
                createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(lbSinichka, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoPanel, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbSinichka,
                GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(
                LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(infoPanel, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE)));
        // Frame layout design block ends here

        pack();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/About/user_info.png")));
    }
}
