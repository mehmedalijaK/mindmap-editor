package dsw.gerumap.app.gui.swing.Commands.implementation;

import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.repository.implementation.Element;

public class AddElementCommand extends AbstractCommand {

    ElementPainter fp;
    MapView m;
    Element elem;

    public AddElementCommand(ElementPainter fp, MapView m, Element elem){
        this.fp = fp;
        this.m = m;
        this.elem = elem;
    }

    @Override
    public void doCommand() {
        m.getPainters().add(fp);
        // addSub, sme li ovdje?
        elem.addSubscriber(m);
        m.getM().addChild(elem);
        removeSelection(m);

    }

    @Override
    public void undoCommand() {
        if(fp == null ||  m==null || elem==null) return;
        m.getPainters().remove(fp);
        elem.removeSubscriber(m);
        m.getM().deleteChildren(elem);
        removeSelection(m);
    }
}
