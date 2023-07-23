package dsw.gerumap.app.gui.swing.controller.EditActions;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class CenteredElementAction extends AbstractGerumapAction {

    public CenteredElementAction(){
        putValue(NAME, "Center selected element");
        putValue(SMALL_ICON,loadIcon("/images/centeredelem32.png"));
        putValue(SHORT_DESCRIPTION, "Center selected element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SelectionModel.selected.size() == 0)
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Please select an element!", MessageType.ERROR));
        else if (SelectionModel.selected.size() == 1)
            MainFrame.getInstance().getProjectView().CenteredElem();
    }
}
