package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class StateToolbar extends JToolBar {

    public StateToolbar() {
        super(VERTICAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getNewElementAction());
        add (MainFrame.getInstance().getActionManager().getNewConnectionAction());
        add (MainFrame.getInstance().getActionManager().getSelectionAction());
        add (MainFrame.getInstance().getActionManager().getMoveAction());
        add (MainFrame.getInstance().getActionManager().getDeleteAction());
        add (MainFrame.getInstance().getActionManager().getZoomInAction());
        add (MainFrame.getInstance().getActionManager().getZoomOutAction());
        add (MainFrame.getInstance().getActionManager().getCenteredElementAction());
        add (MainFrame.getInstance().getActionManager().getEditPropertiesAction());
    }
}
