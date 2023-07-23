package dsw.gerumap.app.core.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.Commands.implementation.AddConnectionCommand;
import dsw.gerumap.app.gui.swing.Commands.implementation.DeleteElementCommand;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;
import dsw.gerumap.app.repository.composite.MapNodeComposite;
import dsw.gerumap.app.repository.implementation.Connection;
import dsw.gerumap.app.repository.implementation.Element;

import java.util.ArrayList;
import java.util.List;

public class DeleteState extends State{
    @Override
    public void misKliknut(int x, int y, MapView m) {

        AbstractCommand command = new DeleteElementCommand(m);

        if(m.getPainters().isEmpty())
            return;

        List<ElementPainter> pom = new ArrayList<>();

        for(ElementPainter ep:m.getPainters()){
            if(SelectionModel.selected.contains(ep.getElem())){
                ((DeleteElementCommand)command).getEp().add(ep);
                pom.add(ep);
            }
            if(ep.elementAt(x,y, ep.getElem())){
                ((DeleteElementCommand)command).getEp().add(ep);
                pom.add(ep);
            }
            if(ep instanceof SelectionSquarePainter){
                ((DeleteElementCommand)command).setPomSel(ep);
            }

        }

        for(ElementPainter ep:m.getPainters()){
            if(ep instanceof ConnectionPainter){
                for(ElementPainter ep2 : pom){
                    if(ep2.getElem().equals(((Connection)ep.getElem()).getToElem())||
                    ep2.getElem().equals(((Connection)ep.getElem()).getFromElem())){
                        ((DeleteElementCommand)command).getEp().add(ep);
                    }
                }
            }
        }

        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
    }

    @Override
    public void misPomeren(int x, int y, MapView m) {

    }

    @Override
    public void misPusten(int x, int y, MapView m) {

    }
}
