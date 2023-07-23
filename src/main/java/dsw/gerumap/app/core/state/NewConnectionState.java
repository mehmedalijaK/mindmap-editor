package dsw.gerumap.app.core.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.Commands.implementation.AddConnectionCommand;
import dsw.gerumap.app.gui.swing.controller.SelectionModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;
import dsw.gerumap.app.repository.implementation.Connection;
import dsw.gerumap.app.repository.implementation.Element;
import dsw.gerumap.app.repository.implementation.Figure;
import dsw.gerumap.app.repository.implementation.MindMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewConnectionState extends State{
    int elemnum = 0;
    Element elem;
    ElementPainter globalEp;
    SelectionSquarePainter sp;
    @Override
    public void misKliknut(int x, int y, MapView m) {

        List<ElementPainter> pom = new ArrayList<>();
        if(m.getPainters() != null){
            for(ElementPainter ep : m.getPainters()){
                if(ep instanceof SelectionSquarePainter)
                    pom.add(ep);
            }
        }

        if(!pom.isEmpty())
            sp = (SelectionSquarePainter) pom.get(0);

        for(ElementPainter ep: m.getPainters()){
            if(ep.elementAt(x,y, ep.getElem())){
                elem = new Connection("Connection" + m.getM().getConNum(), m.getM());
                ((Connection)elem).setFromElem(((Figure)ep.getElem()));
                ep = new ConnectionPainter(elem);
                this.globalEp = ep;
                m.getPainters().add(ep);
                m.getM().addChild(elem);
                elem.addSubscriber(m);
                return;
            }
        }
        ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You did not select element!", MessageType.ERROR));
    }

    @Override
    public void misPomeren(int x, int y, MapView m) {
        ((Connection)elem).setToElem(new Figure("",null, new Point(x,y)));
    }

    @Override
    public void misPusten(int x, int y, MapView m) {
        for(ElementPainter ep: m.getPainters()){
            if(ep.elementAt(x,y,ep.getElem())){
                ((Connection)elem).setToElem(((Figure)ep.getElem()));
                if(((Connection)elem).getFromElem().equals(((Figure) ep.getElem()))){
                    ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You can not have connection with same elements!", MessageType.ERROR));
                    m.getPainters().remove(globalEp);
                    return;
                }

                for(ElementPainter ep1:m.getPainters()){
                    if(ep1 instanceof ConnectionPainter) {
                        Element getTo1 = ((Connection)ep1.getElem()).getToElem();
                        Element getFrom1 = ((Connection)ep1.getElem()).getFromElem();
                        Element getTo = ((Connection)elem).getToElem();
                        Element getFrom = ((Connection)elem).getFromElem();
                        if((getTo.equals(getTo1) && getFrom.equals(getFrom1) &&  !ep1.getElem().equals(elem)) || (
                                getTo.equals(getFrom1) && getFrom.equals(getTo1) &&  !ep1.getElem().equals(elem)
                        ))
                        {
                            m.getPainters().remove(globalEp);
                            ApplicationFramework.getInstance().getMessageObserver().generate(new Message("This connection already exists!", MessageType.ERROR));
                            return;
                        }
                    }

                }

                String input = JOptionPane.showInputDialog("Set connection name name", "Connection" + elemnum);
                if(input==null) {
                    m.getPainters().remove(globalEp);
                    return;
                }
                //ovde dodaj connection
                elem.setName(input);
                m.getPainters().remove(globalEp);
                AbstractCommand command = new AddConnectionCommand(globalEp, m, elem, sp);
                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);


                return;
            }
        }

        ApplicationFramework.getInstance().getMessageObserver().generate(new Message("You have to connect to element!", MessageType.ERROR));
        m.getPainters().remove(m.getPainters().size()-1);
        ((MindMap)elem.getParent()).deleteChildren(elem);
    }
}
