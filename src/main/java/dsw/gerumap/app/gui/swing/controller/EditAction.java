package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Logger.FileLogger;

import java.awt.event.ActionEvent;

public class EditAction extends AbstractGerumapAction{

    public EditAction(){
        putValue(NAME, "Edit");
        putValue(SMALL_ICON,loadIcon("/images/edit.png"));
        putValue(SHORT_DESCRIPTION, "Edit Mind Map");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
