package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Logger.FileLogger;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindowListener implements WindowListener {


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        JFrame frame = (JFrame) e.getComponent();
        int code = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Closing application", JOptionPane.YES_NO_OPTION);
        if(code!= JOptionPane.YES_OPTION){
            frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
        else{
            if(ApplicationFramework.getInstance().getErrorLogger() instanceof FileLogger)
                ApplicationFramework.getInstance().getErrorLogger().log(((FileLogger)ApplicationFramework.getInstance().getErrorLogger()).sbLog.toString());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
