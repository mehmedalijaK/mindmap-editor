package dsw.gerumap.app.core.state;

import dsw.gerumap.app.gui.swing.view.MapView;

public abstract class State {

    public abstract void misKliknut(int x, int y, MapView m);
    public abstract void misPomeren(int x, int y, MapView m);
    public  abstract void misPusten(int x, int y, MapView m);
}
