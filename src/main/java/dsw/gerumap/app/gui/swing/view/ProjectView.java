package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.core.observer.ISubscriber;
import dsw.gerumap.app.core.state.StateManager;
import dsw.gerumap.app.gui.swing.controller.EditActions.CenteredElementAction;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.gerumap.app.gui.swing.view.Painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.FigurePainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.implementation.*;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;
import java.awt.desktop.AppForegroundListener;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {

    JTabbedPane tabs;

    StateToolbar stateToolbar;
    private Project project;
    private MindMap mindMap;
    StateManager stateManager;
    JLabel projectName = new JLabel("Project is not selected", JLabel.LEFT);
    JLabel projectAuthor = new JLabel("Author name: ", JLabel.LEFT);
    JPanel left = new JPanel();
    public ProjectView() {
        stateManager = new StateManager();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        projectName.setAlignmentX(Component.CENTER_ALIGNMENT);
        projectAuthor.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.add(projectName);
        left.add(projectAuthor);
        left.add(tabs);
        this.add(left);
        stateToolbar = new StateToolbar();
        this.add(stateToolbar);
    }

    public void setProject(Project project) {
        if(this.project != null)
            this.project.removeSubscriber(this);
        if(project == null)
        {
            this.project = null;
            update(this);
            return;
        }
        this.project = project;
        this.project.addSubscriber(this);
        /*-----------------------------
        tabs.removeAll();
        for(MapNode s: project.getChildren()){
            MapView mv = new MapView((MindMap) s);
            tabs.add(s.getName(), mv);
            s.addSubscriber(mv);
            for (MapNode mn : ((MindMap)s).getChildren()) {
                Element elem = (Element) mn;
                if (elem instanceof Connection) {
                    mv.getPainters().add(new ConnectionPainter(elem));
                }
                else {
                    mv.getPainters().add(new FigurePainter(elem));
                }
            }

         */
        this.update(this);
        }

    public void setMindMap(MindMap mindMap){
        if(this.mindMap != null)
            this.mindMap.removeSubscriber(this);
        if(mindMap == null)
        {
            this.mindMap = null;
            update(this);
            return;
        }
        this.mindMap = mindMap;
        this.mindMap.addSubscriber(this);
        /*----------------------------
        tabs.removeAll();
        MapView mv = new MapView(mindMap);
        tabs.add(mindMap.getName(), mv);
        for (MapNode mn : mindMap.getChildren()) {
            Element elem = (Element) mn;
            if (elem instanceof Connection) {
                mv.getPainters().add(new ConnectionPainter(elem));
            }
            else {
                mv.getPainters().add(new FigurePainter(elem));
            }
        }

         */
        this.update(this);
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof MindMap) return;
        if(project == null){
            projectName.setText("Project is not selected");
            projectAuthor.setText("Author name: ");
            if(tabs.getTabCount()>0)
                tabs.removeAll();
            return;
        }

        String name = project.getName();
        String author = project.getAuthor();

        if(tabs.getTabCount()>0)
            tabs.removeAll();

        projectName.setText("Project name: " + name);
        projectAuthor.setText("Author name: " + author);

        for(MapNode s: project.getChildren()){
            MapView mv = new MapView((MindMap) s);
            tabs.add(s.getName(), mv);
            s.addSubscriber(mv);
            for (MapNode mn : ((MindMap)s).getChildren()) {
                Element elem = (Element) mn;
                if (elem instanceof Connection) {
                    mv.getPainters().add(new ConnectionPainter(elem));
                }
                else {
                    mv.getPainters().add(new FigurePainter(elem));
                }
                mv.update(this);
            }
        }


    }

    public void startNewElementState(){ this.stateManager.setNewElementState(); }
    public void startDeleteState(){
        this.stateManager.setDeleteState();
        MapView mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        stateManager.getCurrentState().misKliknut(-5, -5, mv);
    }
    public void startNewConnectionState(){
        this.stateManager.setNewConnectionState();
    }
    public void startSelectionState(){
        this.stateManager.setSelectionState();
    }
    public void startMoveState(){
        this.stateManager.setMoveState();
    }

    public void kliknutMis(int x, int y, MapView m) {stateManager.getCurrentState().misKliknut(x, y, m);}

    public void pomerenMis(int x, int y, MapView m) {
        stateManager.getCurrentState().misPomeren(x, y, m);
    }

    public void pustenMis(int x, int y, MapView m) {
        stateManager.getCurrentState().misPusten(x, y, m);
    }

    public void zumiraj(){
        MapView mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        mv.zoomIn();
    }
    public void odZumiraj(){
        MapView mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        mv.zoomOut();
    }

    public void capture(){
        MapView mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        try{
            mv.capture();
        }
        catch(Exception e){
            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You must select a mindmap!", MessageType.ERROR));
        }
    }

    private Figure prev = null;
    private Figure centar = null;
    public void CenteredElem(){
        try{
            prev.setStroke(2);
        }
        catch(Exception ignored){

        }

        MapView mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        ElementPainter pom = null;
        for(ElementPainter ep : mv.getPainters())
            if(ep instanceof SelectionSquarePainter) pom = ep;
        mv.getPainters().remove(pom);
        ((Figure)SelectionModel.selected.get(0)).setPosition(new Point(mv.getWidth()/2, mv.getHeight()/2));
        Figure centar = ((Figure)SelectionModel.selected.get(0));
        centar.setPosition(new Point(mv.getWidth()/2, mv.getHeight()/2));
        for(ElementPainter ep : mv.getPainters())
            ep.getElem().setCentered(false);
        centar.setCentered(true);
        centar.setStroke(5);
        centriraj(centar, mv);
        prev = centar;
    }

    public void centriraj(Figure f, MapView mv){
        int len = 65;
        for(ElementPainter p : mv.getPainters()){
            if(p instanceof ConnectionPainter){
                if(((Connection)p.getElem()).getFromElem().equals(f) && !((Connection)p.getElem()).getToElem().isCentered()){
                    boolean search = true;
                    while(search){
                        double theta = Math.random() * 2 * Math.PI;
                        double newX = f.getPosition().x + len * Math.cos(theta) + place(len);
                        double newY = f.getPosition().y + len * Math.sin(theta) + place(len);
                        if(Math.sqrt(Math.pow(Math.abs(newX - f.getPosition().x), 2) + Math.pow(Math.abs(newY - f.getPosition().y), 2)) < 100)
                            continue;
                        boolean can = true;
                        for(ElementPainter ep : mv.getPainters()){

                            if(ep.elementAt((int)newX, (int)newY, ep.getElem()))
                                can = false;
                        }
                        if(can){
                            ((Connection)p.getElem()).getToElem().setPosition(new Point((int)newX, (int)newY));
                            ((Connection)p.getElem()).getToElem().setCentered(true);
                            search = false;
                        }
                    }
                    centriraj(((Connection)p.getElem()).getToElem(), mv);
                }
                else if(((Connection)p.getElem()).getToElem().equals(f) && !((Connection)p.getElem()).getFromElem().isCentered()){
                    boolean search = true;
                    while(search){
                        double theta = Math.random() * 2 * Math.PI;
                        double newX = f.getPosition().x + len * Math.abs(Math.cos(theta)) + place(len);
                        double newY = f.getPosition().y + len * Math.abs(Math.sin(theta)) + place(len);
                        if(Math.sqrt(Math.pow(Math.abs(newX - f.getPosition().x), 2) + Math.pow(Math.abs(newY - f.getPosition().y), 2)) < 100)
                            continue;
                        boolean can = true;
                        for(ElementPainter ep : mv.getPainters()){

                            if(ep.elementAt((int)newX, (int)newY, ep.getElem()))
                                can = false;
                        }
                        if(can){
                            ((Connection)p.getElem()).getFromElem().setPosition(new Point((int)newX, (int)newY));
                            ((Connection)p.getElem()).getFromElem().setCentered(true);
                            search = false;
                        }
                    }
                    centriraj(((Connection)p.getElem()).getFromElem(), mv);
                }
            }
        }
    }
    public int place(int l){
        if(Math.random() > 0.5)
            return l * -1;
        else
            return l;
    }
}

