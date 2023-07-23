package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Logger.FileLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGerumapAction {

    public ExitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON,loadIcon("/images/close.png"));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(ApplicationFramework.getInstance().getErrorLogger() instanceof FileLogger)
            ApplicationFramework.getInstance().getErrorLogger().log(((FileLogger)ApplicationFramework.getInstance().getErrorLogger()).sbLog.toString());
        System.exit(0);
    }


}
