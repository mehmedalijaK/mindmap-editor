package dsw.gerumap.app.core.factory;

import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.implementation.MindMap;

public class MindMapFactory extends NodeFactory{

    private static int mmb = 1;
    @Override
    public MapNode createNode(MapNode parent) {
        return new MindMap("MindMap" + mmb++, parent);
    }
}
