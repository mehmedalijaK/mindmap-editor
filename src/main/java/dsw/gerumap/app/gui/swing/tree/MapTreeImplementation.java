package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.core.factory.FactoryUtils;
import dsw.gerumap.app.core.factory.NodeFactory;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.view.Painters.FigurePainter;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.composite.MapNodeComposite;
import dsw.gerumap.app.repository.implementation.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {

        if(parent==null){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You can not add child, node can not be empty!", MessageType.ERROR));
            return;
        }
        if (!(parent.getMapNode() instanceof MapNodeComposite)){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You can not add child to element!", MessageType.ERROR));
            return;
        }
        if ((parent.getMapNode() instanceof MindMap)){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You can not add element here", MessageType.ERROR));
            return;
        }

        NodeFactory nf = FactoryUtils.getFactory(parent);
        MapNode child = nf.createNode(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Node is successfully added!", MessageType.INFORMATION));
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();

    }

    @Override
    public void deleteNode(MapTreeItem node) {

        if(node == null){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You can not delete node!", MessageType.ERROR));
            return;
        }

        if(node.getMapNode().getParent()==null){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You can not delete Project Explorer!", MessageType.ERROR));
            return;
        }

        ((MapNodeComposite)node.getMapNode().getParent()).deleteChildren(node.getMapNode());
        treeModel.removeNodeFromParent(node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Node is successfully deleted!", MessageType.INFORMATION));

    }

    @Override
    public void loadProject(Project p) {

        p.setParent(((MapTreeItem)treeModel.getRoot()).getMapNode());
        MapTreeItem pro = new MapTreeItem(p);
        ((MapTreeItem) treeModel.getRoot()).add(pro);
        JTabbedPane tabs = MainFrame.getInstance().getProjectView().getTabs();
        p.addSubscriber(MainFrame.getInstance().getProjectView());
        for(MapNode s: p.getChildren()) {
            s.setParent(p);
            pro.add(new MapTreeItem(s));
            MapView mv = new MapView((MindMap) s);

            tabs.removeAll();
            tabs.add(s.getName(), mv);
            s.addSubscriber(mv);

            for (MapNode mn : ((MindMap) s).getChildren()) {
                mn.setParent(s);
                Element elem = (Element) mn;
                mn.addSubscriber(mv);
                if (elem instanceof Connection) {
                    mv.getPainters().add(new ConnectionPainter(elem));
                    for(MapNode node : ((MindMap)s).getChildren()){
                        if(node instanceof Figure){
                            if(node.equals(((Connection) elem).getFromElem()))
                                ((Connection) elem).setFromElem((Figure)node);
                            else if(node.equals(((Connection) elem).getToElem()))
                                ((Connection) elem).setToElem((Figure)node);
                        }
                    }
                } else {
                    mv.getPainters().add(new FigurePainter(elem));
                }
            }
        }

        MainFrame.getInstance().getProjectView().setProject(p);
        try{MainFrame.getInstance().getProjectView().setMindMap((MindMap)p.getChildren().get(0));}
        catch(Exception ignored){}

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadMindMap(MindMap m) {

        m.setParent(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());
        MapTreeItem mm = new MapTreeItem(m);
        MainFrame.getInstance().getMapTree().getSelectedNode().add(new MapTreeItem(m));
        ((Project)MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode()).addChild(mm.getMapNode());
        MapView mv = new MapView(m);
        m.addSubscriber(mv);
        m.addSubscriber(MainFrame.getInstance().getProjectView());
        JTabbedPane tabs = MainFrame.getInstance().getProjectView().getTabs();
        tabs.removeAll();
        tabs.add(m.getName(), mv);
        for(MapNode mn : m.getChildren()){
            mn.setParent(m);
            Element elem = (Element) mn;
            if (elem instanceof Connection) {
                mv.getPainters().add(new ConnectionPainter(elem));
                for(MapNode node : m.getChildren()){
                    if(node instanceof Figure){
                        if(node.equals(((Connection) elem).getFromElem()))
                            ((Connection) elem).setFromElem((Figure)node);
                        else if(node.equals(((Connection) elem).getToElem()))
                            ((Connection) elem).setToElem((Figure)node);
                    }
                }
            } else {
                mv.getPainters().add(new FigurePainter(elem));
            }
        }
        MainFrame.getInstance().getProjectView().setProject((Project)m.getParent());
        MainFrame.getInstance().getProjectView().setMindMap(m);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
