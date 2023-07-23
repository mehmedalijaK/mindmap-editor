package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;
import dsw.gerumap.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractGerumapAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/open.png"));
        putValue(NAME, "Open project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null)
            return;

        JFileChooser jfc = new JFileChooser();

        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer){
            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = jfc.getSelectedFile();
                    Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                    p.setName(file.getName());
                    MainFrame.getInstance().getMapTree().loadProject(p);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You must select the project explorer!", MessageType.ERROR));

    }
}
