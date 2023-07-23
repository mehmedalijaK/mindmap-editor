package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenTemplateAction extends AbstractGerumapAction{

    public OpenTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/opentemplate.png"));
        putValue(NAME, "Open template");
        putValue(SHORT_DESCRIPTION, "Open template");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null)
            return;
        JFileChooser jfc = new JFileChooser("src/main/resources/Templates");

        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project){
            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = jfc.getSelectedFile();
                    MindMap m = ApplicationFramework.getInstance().getSerializer().loadMindMap(file);
                    m.setName(file.getName());
                    MainFrame.getInstance().getMapTree().loadMindMap(m);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You must select the project!", MessageType.ERROR));
    }
}
