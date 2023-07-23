package dsw.gerumap.app.repository.implementation;

import dsw.gerumap.app.core.observer.ISubscriber;
import dsw.gerumap.app.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public abstract class Element extends MapNode {

    public Element(String name, MapNode parent) {
        super(name, parent);
    }

    private int color = 000000;
    private int stroke = 2;
    private boolean centered = false;
    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub==null)
            return;
        if(this.subscribers==null)
            this.subscribers=new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
        this.notifySubscribers(this);
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

    public void setColor(int color) {
        this.color = color;
        this.notifySubscribers(this);
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
        this.notifySubscribers(this);
    }
}
