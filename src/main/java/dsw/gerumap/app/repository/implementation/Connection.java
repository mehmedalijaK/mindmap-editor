package dsw.gerumap.app.repository.implementation;

import dsw.gerumap.app.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Connection extends Element{
    public Connection(String name, MapNode parent) {
        super(name, parent);
    }

    private Figure fromElem;
    private Figure toElem;

    public void setFromElem(Figure fromElem) {
        this.fromElem = fromElem;
        this.notifySubscribers(this);
    }

    public void setToElem(Figure toElem) {
        this.toElem = toElem;
        this.notifySubscribers(this);
    }
}
