package dsw.gerumap.app.repository.implementation;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.observer.ISubscriber;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.composite.MapNodeComposite;

import java.util.ArrayList;

public class ProjectExplorer extends MapNodeComposite {


    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
                this.notifySubscribers(this);
            }
        }
    }

    @Override
    public void deleteChildren(MapNode child) {
        if (child != null &&  child instanceof Project){
            super.getChildren().remove(child);
            this.notifySubscribers(this);
        }
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub==null)
            return;
        if(this.subscribers==null)
            this.subscribers=new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notifications) {
        if(notifications==null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(notifications);
        }
    }


}
