package dsw.gerumap.app.repository.implementation;

import dsw.gerumap.app.core.observer.ISubscriber;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class MindMap extends MapNodeComposite {

    private boolean template;
    private boolean changed = true;
    private String filePath;
    private int elemNum = 1;
    private int conNum = 1;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        this.template = false;
    }

    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof Element){
            Element element = (Element) child;
            if (!this.getChildren().contains(element)){
                this.getChildren().add(element);
                this.notifySubscribers(this);
            }
        }
        changed = true;
    }

    @Override
    public void deleteChildren(MapNode child) {
        if (child != null &&  child instanceof Element){
            super.getChildren().remove(child);
            this.notifySubscribers(this);
        }
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

        for(int i = 0; i<subscribers.size();i++)
            subscribers.get(i).update(notifications);

    }

    public int getElemNum() {
        return elemNum++;
    }

    public int getConNum() {
        return conNum++;
    }
}
