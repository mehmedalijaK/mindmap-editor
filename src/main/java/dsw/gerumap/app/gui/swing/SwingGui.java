package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.gui.swing.Commands.CommandManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.core.Gui;

public class SwingGui implements Gui {

    private MainFrame instance;
    private transient CommandManager commandManager;

    public SwingGui() {
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
        commandManager = new CommandManager();
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }


}
