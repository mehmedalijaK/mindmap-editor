package dsw.gerumap.app.gui.swing.Commands.implementation;

import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class DeleteElementCommand extends AbstractCommand {

    private List<ElementPainter> ep = new ArrayList<>();
    MapView m;
    ElementPainter pomSel;

    public DeleteElementCommand(MapView m){
        this.m = m;

    }
    @Override
    public void doCommand() {
        for(ElementPainter e : ep){
            ((MapNodeComposite)e.getElem().getParent()).deleteChildren(e.getElem());
        }
        if(pomSel!=null)m.getPainters().remove(pomSel);
        m.getPainters().removeAll(ep);
        SelectionModel.selected.clear();
    }

    @Override
    public void undoCommand() {
        for(ElementPainter e : ep){
            ((MapNodeComposite)e.getElem().getParent()).addChild(e.getElem());
            SelectionModel.selected.add(e.getElem());
        }
        if(pomSel!=null)m.getPainters().add(pomSel);
        m.getPainters().addAll(ep);
    }
}
