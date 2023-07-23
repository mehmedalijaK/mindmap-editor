package dsw.gerumap.app.gui.swing.Commands;

import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;

public abstract class AbstractCommand {

    public abstract void doCommand();

    public abstract void undoCommand();

    public void removeSelection(MapView m){
        ElementPainter epPom = null;
        if(m.getPainters() != null){
            for(ElementPainter ep : m.getPainters()){
                if(ep instanceof SelectionSquarePainter)
                    epPom = ep;
            }
        }
        if(epPom!=null)m.getPainters().remove(epPom);
    }

}
