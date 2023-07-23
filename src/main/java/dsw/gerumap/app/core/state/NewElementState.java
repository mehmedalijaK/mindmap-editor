package dsw.gerumap.app.core.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.gui.swing.Commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.Commands.implementation.AddElementCommand;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.FigurePainter;
import dsw.gerumap.app.repository.implementation.Element;
import dsw.gerumap.app.repository.implementation.Figure;
import javax.swing.*;
import java.awt.*;

public class NewElementState extends State{
    //int elemnum = 1;
    @Override
    public void misKliknut(int x, int y, MapView m) {

        String input = JOptionPane.showInputDialog("Set element name", "Element" + m.getM().getElemNum());
        if(input==null) {
            return;
        }

        Point point = new Point(x, y);
        Element elem = new Figure(input, m.getM(), point);
        ElementPainter fp = new FigurePainter(elem);
        elem.addSubscriber(m);

        if(m.getPainters() != null){
            for(ElementPainter ep : m.getPainters()){
                if(ep.elementAt(x,y, ep.getElem())){
                    ApplicationFramework.getInstance().getMessageObserver().generate(new Message("Two elements can not overlap!", MessageType.ERROR));
                    return;
                }
            }
        }

        AbstractCommand command = new AddElementCommand(fp, m, elem);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
    }

    @Override
    public void misPomeren(int x, int y, MapView m) {

    }

    @Override
    public void misPusten(int x, int y, MapView m) {

    }
}