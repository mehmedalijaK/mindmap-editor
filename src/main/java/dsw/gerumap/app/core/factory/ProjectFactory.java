package dsw.gerumap.app.core.factory;

import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.implementation.Project;

public class ProjectFactory extends NodeFactory{

    private static int pb = 1;
    @Override
    public MapNode createNode(MapNode parent) {

        return new Project("Project" + pb++, parent);
    }
}
