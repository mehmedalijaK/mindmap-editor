package dsw.gerumap.app.core.state;

import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;
import dsw.gerumap.app.repository.implementation.Connection;
import dsw.gerumap.app.repository.implementation.Element;
import dsw.gerumap.app.repository.implementation.Figure;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SelectionState extends State{

    private Element figure;
    private ElementPainter ep;
    @Override
    public void misKliknut(int x, int y, MapView m) {
        SelectionSquarePainter pom = null;
        for(ElementPainter ep : m.getPainters()){
            if(ep instanceof SelectionSquarePainter){
                pom = ((SelectionSquarePainter)ep);
            }
        }
        if(pom!=null){
            m.getPainters().remove(pom);
        }
        SelectionModel.selected.clear();
        figure = new SelectionSquare(new Point(x,y), new Point(x,y));
        ep = new SelectionSquarePainter(figure);
        m.getPainters().add(ep);
        figure.addSubscriber(m);
    }

    @Override
    public void misPomeren(int x, int y, MapView m) {
        ((SelectionSquare)figure).setEnd(new Point(x,y));
    }

    @Override
    public void misPusten(int x, int y, MapView m) {
        ((SelectionSquare)figure).setEnd(new Point(x,y));
        Figure a = new Figure("", null, new Point(0,0));

        int maxX = Math.max(((SelectionSquare)figure).getEnd().x, ((SelectionSquare)figure).getStart().x) + a.getDimension().width / 6;
        int maxY = Math.max(((SelectionSquare)figure).getEnd().y, ((SelectionSquare)figure).getStart().y) + a.getDimension().width / 6;
        int minX = Math.min(((SelectionSquare)figure).getEnd().x, ((SelectionSquare)figure).getStart().x) - a.getDimension().height / 6 - 20;
        int minY = Math.min(((SelectionSquare)figure).getEnd().y, ((SelectionSquare)figure).getStart().y) - a.getDimension().height / 6;

        for(ElementPainter ep : m.getPainters()){
            if(ep.getElem() instanceof Figure){
                if(((Figure) ep.getElem()).getPosition().x > minX && ((Figure) ep.getElem()).getPosition().x < maxX && ((Figure) ep.getElem()).getPosition().y > minY && ((Figure) ep.getElem()).getPosition().y < maxY )
                {
                    SelectionModel.selected.add(ep.getElem());
                }
            }
            if(ep.getElem() instanceof Connection && SelectionModel.selected.isEmpty()){
                Connection c = (Connection) ep.getElem();

                int maxX2 = Math.max(((SelectionSquare)figure).getEnd().x, ((SelectionSquare)figure).getStart().x);
                int maxY2 = Math.max(((SelectionSquare)figure).getEnd().y, ((SelectionSquare)figure).getStart().y);
                int minX2 = Math.min(((SelectionSquare)figure).getEnd().x, ((SelectionSquare)figure).getStart().x);
                int minY2 = Math.min(((SelectionSquare)figure).getEnd().y, ((SelectionSquare)figure).getStart().y);

                int cminY = Math.min(c.getFromElem().getPosition().y, c.getToElem().getPosition().y);
                int cmaxY = Math.max(c.getFromElem().getPosition().y, c.getToElem().getPosition().y);
                int cminX = Math.min(c.getFromElem().getPosition().x, c.getToElem().getPosition().x);
                int cmaxX = Math.max(c.getFromElem().getPosition().x, c.getToElem().getPosition().x);

                Point lineCenter = new Point((cminX + cmaxX)/2, (cminY + cmaxY)/2);
                Point lineCenterd = new Point((cminX + lineCenter.x)/2, (cminY + lineCenter.y)/2);
                Point lineCenteru = new Point((lineCenter.x + cmaxX)/2, (lineCenter.y + cmaxY)/2);

                if(lineCenter.x > minX2 && lineCenter.x < maxX2 && lineCenter.y > minY2 && lineCenter.y < maxY2)
                    SelectionModel.selected.add(ep.getElem());
                else if(lineCenterd.x > minX2 && lineCenterd.x < maxX2 && lineCenterd.y > minY2 && lineCenterd.y < maxY2)
                    SelectionModel.selected.add(ep.getElem());
                else if(lineCenteru.x > minX2 && lineCenteru.x < maxX2 && lineCenteru.y > minY2 && lineCenteru.y < maxY2)
                    SelectionModel.selected.add(ep.getElem());

            }
        }

        int mL = 10000;
        int mT = 10000;
        int mR = -1;
        int mB = -1;

        if(SelectionModel.selected.size() == 1 && SelectionModel.selected.get(0) instanceof Connection){
            Connection c = (Connection) SelectionModel.selected.get(0);
            mL = Math.min(c.getFromElem().getPosition().x, c.getToElem().getPosition().x);
            mT = Math.min(c.getFromElem().getPosition().y, c.getToElem().getPosition().y);
            mR = Math.max(c.getFromElem().getPosition().x, c.getToElem().getPosition().x);
            mB = Math.max(c.getFromElem().getPosition().y, c.getToElem().getPosition().y);
        }
        else {
            for (Element elem : SelectionModel.selected) {
                if (((Figure) elem).getPosition().x - ((Figure) elem).getDimension().getWidth() / 2 - 5 < mL)
                    mL = (int) (((Figure) elem).getPosition().x - ((Figure) elem).getDimension().getWidth() / 2 - 5);
                if (((Figure) elem).getPosition().y - ((Figure) elem).getDimension().getHeight() / 2 - 5 < mT)
                    mT = (int) (((Figure) elem).getPosition().y - ((Figure) elem).getDimension().getHeight() / 2 - 5);
                if (((Figure) elem).getPosition().x + ((Figure) elem).getDimension().getWidth() / 2 + 23 > mR)
                    mR = (int) (((Figure) elem).getPosition().x + ((Figure) elem).getDimension().getWidth() / 2 + 23);
                if (((Figure) elem).getPosition().y + ((Figure) elem).getDimension().getHeight() / 2 - 1 > mB)
                    mB = (int) (((Figure) elem).getPosition().y + ((Figure) elem).getDimension().getHeight() / 2 - 1);
            }
        }

        Element border = new SelectionSquare(new Point(mL,mT), new Point(mR,mB));
        ElementPainter epBorder = new SelectionSquarePainter(border);
        if(mL != 10000 || mT != 10000)
            m.getPainters().add(epBorder);
        m.getPainters().remove(ep);
    }
}
