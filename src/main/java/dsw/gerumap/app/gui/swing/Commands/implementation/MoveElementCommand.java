package dsw.gerumap.app.gui.swing.Commands.implementation;

import dsw.gerumap.app.core.state.SelectionSquare;
import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.repository.implementation.Figure;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MoveElementCommand extends AbstractCommand {

    List<Figure> figureList = new ArrayList<>();
    List<Point> firstXY = new ArrayList<>();
    List<Point> newXY = new ArrayList<>();
    List<Point> ssXY = new ArrayList<>();
    List<Point> ss2XY = new ArrayList<>();
    SelectionSquare ss = null;
    MapView m;
    public MoveElementCommand(MapView m){
        this.m = m;
    }
    @Override
    public void doCommand() {
        int i = 0;
        for(Figure f : figureList){
            f.setPosition(newXY.get(i++));
        }
        if(ss!=null){
            ss.setStart(ss2XY.get(0));
            ss.setEnd(ss2XY.get(1));
        }
    }

    @Override
    public void undoCommand() {
        int i = 0;
        for(Figure f : figureList){
            f.setPosition(firstXY.get(i++));
        }
        if(ss!=null){
            ss.setStart(ssXY.get(0));
            ss.setEnd(ssXY.get(1));
        }

    }
}
