package dsw.gerumap.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Figure extends Element{


    private Point position;
    private Dimension dimension;

    public Figure(String name, MindMap parent, Point point) {
        super(name, parent);
        this.position = point;
        this.dimension = new Dimension(200, 200);
    }

    public void setPosition(Point position) {
        this.position = position;
        this.notifySubscribers(this);
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        this.notifySubscribers(this);
    }
}
