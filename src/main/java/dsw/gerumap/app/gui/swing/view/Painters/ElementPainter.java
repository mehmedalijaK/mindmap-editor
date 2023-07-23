package dsw.gerumap.app.gui.swing.view.Painters;

import dsw.gerumap.app.repository.implementation.Element;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
@Getter
@Setter
public abstract class ElementPainter {

    private Element elem;
    private Shape s;

    public ElementPainter(Element e){
        this.elem = e;
    }
    public abstract void draw(Graphics2D g);
    public abstract boolean elementAt(int x, int y, Element e);

    @Override
    public String toString() {
        return elem.getName() + " painter  ";
    }


}
