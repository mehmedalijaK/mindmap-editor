package dsw.gerumap.app.repository;

import dsw.gerumap.app.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.composite.MapNodeComposite;
import dsw.gerumap.app.repository.implementation.ProjectExplorer;


public class MapRepositoryImpl implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {
        //TODO: implement add Child method
    }

    @Override
    public void deleteNode(MapNode node) {

    }
}
