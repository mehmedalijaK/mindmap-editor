package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn =null;
    private JTextField edit=null;

    public MapTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }

    public boolean isCellSelected(EventObject arg0){
        return true;
    }

    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof MapTreeItem)){
            return;
        }

        if(e.getActionCommand().equals("")){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Project name can not be empty!", MessageType.ERROR));
            return;
        }

        MapTreeItem clicked = (MapTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());
        clicked.getMapNode().notifySubscribers(this);
        if(clicked.getMapNode() instanceof Project)
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Project name is successfully changed!", MessageType.INFORMATION));
        if(clicked.getMapNode() instanceof MindMap)
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("MindMap name is successfully changed!", MessageType.INFORMATION));

    }
}
