package dsw.gerumap.app.gui.swing.Commands.implementation;

import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;
import dsw.gerumap.app.repository.implementation.Element;

public class AddConnectionCommand extends AbstractCommand {

    SelectionSquarePainter sp;
    ElementPainter ep;
    MapView m;
    Element elem;

    public AddConnectionCommand(ElementPainter ep, MapView m, Element elem, SelectionSquarePainter sp){
        this.ep = ep;
        this.m = m;
        this.elem = elem;
        this.sp = sp;
    }

    @Override
    public void doCommand() {
        m.getPainters().add(ep);
        m.getM().addChild(elem);
        removeSelection(m);
    }

    @Override
    public void undoCommand() {
        m.getPainters().remove(ep);
        m.getM().deleteChildren(elem);
        removeSelection(m);
    }
}
