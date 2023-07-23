package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractGerumapAction{

    public AddAuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Add author");
        putValue(SHORT_DESCRIPTION, "Add author");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if((MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode() == null)
        {
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You need to select node!", MessageType.ERROR));
            return;
        }
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selected.getMapNode() instanceof Project){
            String input = JOptionPane.showInputDialog("Set " + selected.getMapNode().getName() + " author", ((Project) selected.getMapNode()).getAuthor());

            if(input==null) {
                return;
            }
            else if(input.equals(""))
                ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Author name can not be empty!", MessageType.ERROR));
            else{
                ((Project)selected.getMapNode()).setAuthor(input);
                ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Author name is changed!", MessageType.INFORMATION));
            }
        }else{
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Only projects can have authors!", MessageType.ERROR));
        }
    }
}
