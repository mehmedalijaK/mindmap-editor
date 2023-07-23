package dsw.gerumap.app.gui.swing.view.Painters;

import dsw.gerumap.app.core.state.SelectionSquare;
import dsw.gerumap.app.repository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter@Setter
public class SelectionSquarePainter extends ElementPainter{

    public SelectionSquarePainter(Element e) {
        super(e);
    }

    @Override
    public void draw(Graphics2D g) {

        int x1 = ((SelectionSquare)super.getElem()).getStart().x;
        int y1 = ((SelectionSquare)super.getElem()).getStart().y;
        int x2 = ((SelectionSquare)super.getElem()).getEnd().x;
        int y2 = ((SelectionSquare)super.getElem()).getEnd().y;
        int px = Math.min(x1,x2);
        int py = Math.min(y1,y2);
        int pw=Math.abs(x1-x2);
        int ph=Math.abs(y1-y2);

        Stroke dashed = new BasicStroke(getElem().getStroke(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);

        g.setStroke(dashed);
        Color c = new Color(getElem().getColor());
        g.setColor(c);

        g.drawRect(px,py,pw,ph);

    }

    @Override
    public boolean elementAt(int x, int y, Element e) {
        return false;
    }
}
