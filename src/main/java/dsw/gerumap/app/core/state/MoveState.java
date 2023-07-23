package dsw.gerumap.app.core.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.Commands.implementation.AddElementCommand;
import dsw.gerumap.app.gui.swing.Commands.implementation.MoveElementCommand;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.repository.implementation.Connection;
import dsw.gerumap.app.repository.implementation.Element;
import dsw.gerumap.app.repository.implementation.Figure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveState extends State{

    int doleX = 0, doleY = 0;
    boolean move = true;
    AbstractCommand command;
    List<Figure> movedFigures = new ArrayList<>();
    List<Point> firstPoint = new ArrayList<>();
    List<Point> firstssPoint = new ArrayList<>();
    SelectionSquare ss;

    @Override
    public void misKliknut(int x, int y, MapView m) {

        doleX = x;
        doleY = y;

        if(SelectionModel.selected.size() == 1 && SelectionModel.selected.get(0) instanceof Connection){
            move = false;
        }
        else{
            MainFrame.getInstance().setCursor(new Cursor(Cursor.MOVE_CURSOR));
            move = true;
            command = new MoveElementCommand(m);
        }
    }

    @Override
    public void misPomeren(int x, int y, MapView m) {

        if(move) {
            if (SelectionModel.selected.isEmpty()) {
                for (ElementPainter ep : m.getPainters()) {
                    if (ep.getElem() instanceof Figure) {
                        int trX = ((Figure) ep.getElem()).getPosition().x + x - doleX;
                        int trY = ((Figure) ep.getElem()).getPosition().y + y - doleY;


                        if(!movedFigures.contains(((Figure) ep.getElem()))){
                            movedFigures.add(((Figure) ep.getElem()));
                            firstPoint.add(new Point(((Figure) ep.getElem()).getPosition().x,((Figure) ep.getElem()).getPosition().y));
                        }

                        ((Figure) ep.getElem()).setPosition(new Point(trX, trY));
                    }
                }
            }

            for (Element ep : SelectionModel.selected) {
                if (ep instanceof Figure) {
                    int trX = ((Figure) ep).getPosition().x + x - doleX;
                    int trY = ((Figure) ep).getPosition().y + y - doleY;

                    if(!movedFigures.contains(((Figure) ep))){
                        movedFigures.add(((Figure) ep));
                        firstPoint.add(new Point(((Figure) ep).getPosition().x,((Figure) ep).getPosition().y));
                    }

                    ((Figure) ep).setPosition(new Point(trX, trY));

                }
            }
            for (ElementPainter ep : m.getPainters()) {
                ep.getElem().addSubscriber(m);
                if (ep.getElem() instanceof SelectionSquare) {
                    int x1 = ((SelectionSquare) ep.getElem()).getStart().x;
                    int y1 = ((SelectionSquare) ep.getElem()).getStart().y;
                    int x2 = ((SelectionSquare) ep.getElem()).getEnd().x;
                    int y2 = ((SelectionSquare) ep.getElem()).getEnd().y;

                    if(ss==null){
                        ss = (SelectionSquare) ep.getElem();
                        firstssPoint.add(new Point(x1,y1));
                        firstssPoint.add(new Point(x2,y2));
                    }
                    ((SelectionSquare) ep.getElem()).setStart(new Point(x1 + x - doleX, y1 + y - doleY));
                    ((SelectionSquare) ep.getElem()).setEnd(new Point(x2 + x - doleX, y2 + y - doleY));
                }
            }
            doleX = x;
            doleY = y;
        }
    }

    @Override
    public void misPusten(int x, int y, MapView m) {

        MainFrame.getInstance().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        ((MoveElementCommand)command).getFigureList().addAll(movedFigures);
        ((MoveElementCommand)command).getFirstXY().addAll(firstPoint);

        if(ss!=null){
            ((MoveElementCommand)command).getSsXY().add(firstssPoint.get(0));
            ((MoveElementCommand)command).getSsXY().add(firstssPoint.get(1));
            ((MoveElementCommand)command).getSs2XY().add(ss.getStart());
            ((MoveElementCommand)command).getSs2XY().add(ss.getEnd());
            ((MoveElementCommand)command).setSs(ss);
        }

        for(Figure f : movedFigures) ((MoveElementCommand)command).getNewXY().add(new Point(f.getPosition().x, f.getPosition().y));
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);


        movedFigures.clear();
        firstPoint.clear();
        firstssPoint.clear();
        ss = null;

    }
}
