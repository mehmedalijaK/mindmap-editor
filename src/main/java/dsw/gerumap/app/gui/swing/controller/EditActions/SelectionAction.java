package dsw.gerumap.app.gui.swing.controller.EditActions;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectionAction extends AbstractGerumapAction {

    public SelectionAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/select32.png"));
        putValue(NAME, "Selection");
        putValue(SHORT_DESCRIPTION, "Selection");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectionState();
    }
}
