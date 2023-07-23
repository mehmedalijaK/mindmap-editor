package dsw.gerumap.app.repository.implementation;

import dsw.gerumap.app.core.observer.ISubscriber;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Project extends MapNodeComposite {

    private String author;
    protected String filepath;
    protected boolean changed = true;
    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            if (!this.getChildren().contains(mindMap)){
                this.getChildren().add(mindMap);
                this.notifySubscribers(this);
            }
        }
        changed = true;
    }

    @Override
    public void deleteChildren(MapNode child) {
        if (child != null &&  child instanceof MindMap){
            super.getChildren().remove(child);
            this.notifySubscribers(this);
        }
        changed = true;
    }
    public void setAuthor(String author){
        this.author = author;
        this.notifySubscribers(this);
        changed = true;
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


    public boolean isChanged() {
        return changed;
    }

    public String getFilePath() {
        return filepath;
    }

    public void setFilePath(String path) {
        this.filepath = path;
    }

    public void setChanged(boolean b) {
        this.changed = b;
    }
}
