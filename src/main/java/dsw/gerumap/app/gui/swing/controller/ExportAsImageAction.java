package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportAsImageAction extends AbstractGerumapAction{

    public ExportAsImageAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("/images/exportAsImage.png"));
        putValue(NAME, "Export as image");
        putValue(SHORT_DESCRIPTION, "Export as image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().capture();
    }
}
