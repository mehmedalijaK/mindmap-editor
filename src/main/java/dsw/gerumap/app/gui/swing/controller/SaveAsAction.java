package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAsAction extends AbstractGerumapAction{

    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/saveAs.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)
                && !(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap)) return;

        if((MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)){
            Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
            File projectFile = null;

            if (!project.isChanged()) {
                return;
            }

            if (project.getFilePath() == null || project.getFilePath().length()==0) {
                JFileChooser jfc = new JFileChooser();
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    project.setFilePath(projectFile.getPath());
                }
                else {
                    return;
                }
            }
            else{
                JFileChooser jfc1 = new JFileChooser();
                if (jfc1.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc1.getSelectedFile();
                    project.setFilePath(projectFile.getPath());
                }
            }

            ApplicationFramework.getInstance().getSerializer().saveProject(project);
            project.setChanged(false);
        }
        else{
            MindMap mindmap = (MindMap) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
            File projectFile = null;

            if (!mindmap.isChanged()) {
                return;
            }

            if (mindmap.getFilePath() == null || mindmap.getFilePath().length()==0) {
                JFileChooser jfc = new JFileChooser("src/main/resources/Templates");
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    mindmap.setFilePath(projectFile.getPath());
                }
                else {
                    return;
                }
            }
            else{
                JFileChooser jfc1 = new JFileChooser();
                if (jfc1.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc1.getSelectedFile();
                    mindmap.setFilePath(projectFile.getPath());
                }
            }

            ApplicationFramework.getInstance().getSerializer().saveMindMap(mindmap);
            mindmap.setChanged(false);
        }

    }
}
