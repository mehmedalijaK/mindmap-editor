package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener{

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
        if(treeItemSelected.getMapNode() instanceof Project){
            if(e.isAddedPath())MainFrame.getInstance().getProjectView().setProject((Project)treeItemSelected.getMapNode());
            else MainFrame.getInstance().getProjectView().setProject(null);
        }
        if(treeItemSelected.getMapNode() instanceof MindMap){
            MainFrame.getInstance().getProjectView().setMindMap((MindMap) treeItemSelected.getMapNode());
            MainFrame.getInstance().getProjectView().setProject((Project) treeItemSelected.getMapNode().getParent());
        }

    }
}


