package dsw.gerumap.app.gui.swing.modal;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.controller.ExitAction;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog {

    public EditDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/3, (int)(screenHeight/2.5));
        setLocationRelativeTo(parent);

        initializeComponents();
    }

    private void initializeComponents() {

        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel labelName = new JLabel("Name:");
        JLabel labelStroke = new JLabel("Stroke: ");
        JTextField tfname = new JTextField();
        tfname.setText(SelectionModel.selected.get(0).getName());
        JTextField tfstroke = new JTextField();
        tfstroke.setText(SelectionModel.selected.get(0).getStroke() + "");
        JPanel props = new JPanel();
        props.setLayout(new BoxLayout(props, BoxLayout.X_AXIS));
        props.add(labelName);
        props.add(tfname);
        props.add(labelStroke);
        props.add(tfstroke);

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.setColor(SelectionModel.selected.get(0).getColor());

        JButton btnOk = new JButton("Ok");

        mainPanel.add(errorLabel);
        mainPanel.add(props);
        mainPanel.add(colorChooser);
        mainPanel.add(btnOk);

        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean off = true;
                if(!tfname.getText().equals("")){SelectionModel.selected.get(0).setName(tfname.getText());}
                if(!tfstroke.getText().equals("")){
                    try{
                        SelectionModel.selected.get(0).setStroke(Integer.parseInt(tfstroke.getText()));
                    }

                    catch(NumberFormatException ex){
                        ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Please enter a number for the stroke", MessageType.ERROR));
                        tfstroke.setText("");
                        off = false;
                    }

                }

                SelectionModel.selected.get(0).setColor(colorChooser.getColor().getRGB());

                if(off)
                    dispose();
            }
        });

        this.add(mainPanel);
    }
}
