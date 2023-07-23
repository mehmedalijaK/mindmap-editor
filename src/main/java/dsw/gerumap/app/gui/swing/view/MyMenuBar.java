package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExportAsImageAction());
        fileMenu.addSeparator();
        fileMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
        fileMenu.addSeparator();
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(MainFrame.getInstance().getActionManager().getEditAction());

        JMenu infoMenu = new JMenu("Info");
        infoMenu.add(MainFrame.getInstance().getActionManager().getAboutAction());

        this.add(fileMenu);
        this.add(helpMenu);
        this.add(infoMenu);

    }
}
