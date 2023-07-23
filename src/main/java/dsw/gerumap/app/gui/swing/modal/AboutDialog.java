package dsw.gerumap.app.gui.swing.modal;


import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AboutDialog extends JDialog {

    public AboutDialog(Frame parent, String title, boolean modal){
        super(parent, title, modal);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/3, screenHeight/3);
        setLocationRelativeTo(parent);
        initializeComponents();

    }

    private void initializeComponents(){
        JPanel mainPanel=new JPanel(new GridLayout(0,2,20,0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel studentM = new JPanel();
        studentM.setLayout(new BoxLayout(studentM, BoxLayout.Y_AXIS));

        JPanel studentL = new JPanel();
        studentL.setLayout(new BoxLayout(studentL, BoxLayout.Y_AXIS));

        JLabel mName = new JLabel("Mehmedalija Karišik");
        JLabel lName = new JLabel("Lazar Vuksanović");
        lName.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel mIndex = new JLabel("RN4/21, grupa 201");
        JLabel lIndex = new JLabel("RN10/21, grupa 201");
        lIndex.setAlignmentX(Component.RIGHT_ALIGNMENT);

        Icon imgM = new ImageIcon(getClass().getResource("/profile/MehmedalijaK.jpg"));
        Icon imgL = new ImageIcon(getClass().getResource("/profile/LazarV.jpg"));
        JLabel profileM = new JLabel(imgM);
        JLabel profileL = new JLabel(imgL);
        profileL.setAlignmentX(Component.RIGHT_ALIGNMENT);

        studentM.add(mName);
        studentM.add(mIndex);
        studentM.add(profileM);
        studentL.add(lName);
        studentL.add(lIndex);
        studentL.add(profileL);

        mainPanel.add(studentM);
        mainPanel.add(studentL);
        this.add(mainPanel);
    }
}
