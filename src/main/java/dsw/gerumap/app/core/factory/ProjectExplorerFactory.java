package dsw.gerumap.app.core.factory;

import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.implementation.ProjectExplorer;

public class ProjectExplorerFactory extends NodeFactory{

    @Override
    public MapNode createNode(MapNode parent) {
        return new ProjectExplorer("My Project Explorer");
    }
}
