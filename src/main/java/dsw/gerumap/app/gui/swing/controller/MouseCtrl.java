package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseCtrl extends MouseAdapter {

    private MapView mv;

    @Override
    public void mousePressed(MouseEvent e){
        mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        MainFrame.getInstance().getProjectView().kliknutMis(e.getPoint().x, e.getPoint().y, mv);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        MainFrame.getInstance().getProjectView().pomerenMis(e.getPoint().x, e.getPoint().y, mv);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        mv = (MapView)MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();
        MainFrame.getInstance().getProjectView().pustenMis(e.getPoint().x, e.getPoint().y, mv);
    }
}
