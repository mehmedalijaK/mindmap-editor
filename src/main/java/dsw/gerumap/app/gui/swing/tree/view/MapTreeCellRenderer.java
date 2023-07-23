package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.repository.implementation.Element;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;
import dsw.gerumap.app.repository.implementation.ProjectExplorer;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

@NoArgsConstructor
public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
            URL imageURL = null;

            if (((MapTreeItem)value).getMapNode() instanceof ProjectExplorer) {
                imageURL = getClass().getResource("/images/tdiagram.gif");
            }
            else if (((MapTreeItem)value).getMapNode() instanceof Project) {
                imageURL = getClass().getResource("/images/tproject.gif");
            }
            else if (((MapTreeItem)value).getMapNode() instanceof MindMap) {
                imageURL = getClass().getResource("/images/mindmap.gif");
            }
            else if (((MapTreeItem)value).getMapNode() instanceof Element) {
                imageURL = getClass().getResource("/images/element.gif");
            }

            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

            return this;
        }

}


