package dsw.gerumap.app.gui.swing.controller.EditActions;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.modal.EditDialog;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditPropertiesAction extends AbstractGerumapAction {

    public EditPropertiesAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit32.png"));
        putValue(NAME, "New Element");
        putValue(SHORT_DESCRIPTION, "Edit element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SelectionModel.selected.size() == 0)
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Please select an element!", MessageType.ERROR));
        else if (SelectionModel.selected.size() > 1)
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Please select one element!", MessageType.ERROR));
        else{
            EditDialog editDialog = new EditDialog(MainFrame.getInstance(), "Edit element", true);
            editDialog.setVisible(true);
        }

    }
}
