package dsw.gerumap.app.core.factory;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;
import dsw.gerumap.app.repository.implementation.ProjectExplorer;

public class FactoryUtils {

    static ProjectFactory pf = new ProjectFactory();
    static MindMapFactory mf = new MindMapFactory();

    public static NodeFactory getFactory(MapTreeItem node) {
        if (node.getMapNode() instanceof ProjectExplorer) {
            return pf;
        } else if (node.getMapNode() instanceof Project) {
            return mf;
        }
        return null;
    }
}
