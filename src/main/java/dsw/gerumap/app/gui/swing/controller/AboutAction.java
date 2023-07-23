package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.modal.AboutDialog;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AboutAction extends AbstractGerumapAction{

    public AboutAction(){
        putValue(NAME, "About");
        putValue(SMALL_ICON,loadIcon("/images/info.png"));
        putValue(SHORT_DESCRIPTION, "Learn about project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutDialog aboutDialog = new AboutDialog(MainFrame.getInstance(), "About project", true);
        aboutDialog.setVisible(true);
    }
}
