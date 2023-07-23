package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.Message.MessageGeneratorImplementation;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.core.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class GuiMessage extends JDialog implements ISubscriber {

    @Override
    public void update(Object notification) {
        String content = ((MessageGeneratorImplementation)notification).getContent();
        MessageType type = ((MessageGeneratorImplementation)notification).getType();
        if(type.equals(MessageType.ERROR))
            JOptionPane.showMessageDialog(MainFrame.getInstance(), content, "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        //else if(type.equals(MessageType.INFORMATION))
        //    JOptionPane.showMessageDialog(MainFrame.getInstance(), content);
        //else
        //    JOptionPane.showMessageDialog(MainFrame.getInstance(), content, "WARNING", JOptionPane.WARNING_MESSAGE);
        // Zakomentarisali da ne ometa. :)
    }
}
