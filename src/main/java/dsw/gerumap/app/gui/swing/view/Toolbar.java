package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);


        add (MainFrame.getInstance().getActionManager().getOpenAction());
        add (MainFrame.getInstance().getActionManager().getOpenTemplateAction());
        add (MainFrame.getInstance().getActionManager().getSaveAction());
        add (MainFrame.getInstance().getActionManager().getSaveAsAction());
        add (MainFrame.getInstance().getActionManager().getExportAsImageAction());
        addSeparator();
        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
        add (MainFrame.getInstance().getActionManager().getDeleteProjectAction());
        add (MainFrame.getInstance().getActionManager().getAddAuthorAction());
        addSeparator();
        add (MainFrame.getInstance().getActionManager().getUndoAction());
        add (MainFrame.getInstance().getActionManager().getRedoAction());

    }
}
