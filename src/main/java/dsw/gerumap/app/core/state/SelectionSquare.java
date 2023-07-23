package dsw.gerumap.app.core.state;

import dsw.gerumap.app.repository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.beans.ConstructorProperties;

@Getter@Setter
public class SelectionSquare extends Element {

    private Point start;
    private Point end;

    public SelectionSquare(Point a, Point b){
        super("", null);
        Color c = new Color(0, 153, 255);
        super.setColor(c.getRGB());
        super.setStroke(2);
        this.start = a;
        this.end = b;
    }

    public void setStart(Point start) {
        this.start = start;
        this.notifySubscribers(this);
    }

    public void setEnd(Point end) {
        this.end = end;
        this.notifySubscribers(this);
    }
}
