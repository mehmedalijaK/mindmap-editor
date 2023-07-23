package dsw.gerumap.app.gui.swing.view.Painters;

import dsw.gerumap.app.repository.implementation.Element;
import dsw.gerumap.app.repository.implementation.Figure;
import java.awt.*;

public class FigurePainter extends ElementPainter{

    public FigurePainter(Element e) {
        super(e);
    }

    @Override
    public void draw(Graphics2D g) {
        Element elem = super.getElem();
        Color c = new Color(elem.getColor());
        g.setColor(c);
        g.setStroke(new BasicStroke(elem.getStroke()));
        Figure f = (Figure)elem;
        f.setDimension(new Dimension(g.getFontMetrics().stringWidth(elem.toString()), 50));
        g.setColor(Color.white);
        g.fillOval(f.getPosition().x - f.getDimension().width/2 - 5, f.getPosition().y - f.getDimension().height/2 - 4,
                g.getFontMetrics().stringWidth(elem.toString()) + 25,  50);
        g.setColor(c);
        g.drawOval(f.getPosition().x - f.getDimension().width/2 - 5, f.getPosition().y - f.getDimension().height/2 - 4,
                g.getFontMetrics().stringWidth(elem.toString()) + 25,  50);
        g.setColor(c);
        g.drawString(elem.toString(), f.getPosition().x -  g.getFontMetrics().stringWidth(elem.toString())/2 + 8,
                f.getPosition().y + g.getFontMetrics().getHeight()/2 - 7);
    }

    @Override
    public boolean elementAt(int x, int y, Element m) {
        Figure figure = (Figure)m;
        double p = (Math.pow((x - figure.getPosition().x), 2)
                / Math.pow(figure.getDimension().width+25, 2))
                + (Math.pow((y - figure.getPosition().y), 2)
                / Math.pow(50, 2));
        return p<=1;
    }

}
