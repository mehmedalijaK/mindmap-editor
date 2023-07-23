package dsw.gerumap.app.gui.swing.view.Painters;

import dsw.gerumap.app.repository.implementation.Connection;
import dsw.gerumap.app.repository.implementation.Element;
import java.awt.*;

public class ConnectionPainter extends ElementPainter{

    public ConnectionPainter(Element e) {
        super(e);
    }

    @Override
    public void draw(Graphics2D g) {
        Color c = new Color(super.getElem().getColor());
        g.setColor(c);
        g.setStroke(new BasicStroke(super.getElem().getStroke()));

        if(((Connection)super.getElem()).getToElem() != null){
            g.drawLine(((Connection)super.getElem()).getFromElem().getPosition().x,
                    ((Connection)super.getElem()).getFromElem().getPosition().y,
                    ((Connection)super.getElem()).getToElem().getPosition().x,
                    ((Connection)super.getElem()).getToElem().getPosition().y);
        }

    }

    @Override
    public boolean elementAt(int x, int y, Element e) {
        return false;
    }

}
