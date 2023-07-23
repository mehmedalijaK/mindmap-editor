package dsw.gerumap.app.repository.composite;

import dsw.gerumap.app.core.observer.IPublisher;
import dsw.gerumap.app.core.observer.ISubscriber;
import lombok.*;

import java.util.List;

@Getter
@Setter
public abstract class MapNode implements IPublisher {

    protected transient List<ISubscriber> subscribers;
    private String name;
    @ToString.Exclude
    private transient MapNode parent;

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MapNode) {
            MapNode otherObj = (MapNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

    public void setName(String name){
        this.name = name;
        this.notifySubscribers(this);
    }

    public void setParent(MapNode parent){
        this.parent = parent;
        this.notifySubscribers(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
