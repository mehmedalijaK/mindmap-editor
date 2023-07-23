package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.controller.EditActions.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private AboutAction aboutAction;
    private EditAction editAction;
    private AddAuthorAction addAuthorAction;
    private DeleteProjectAction deleteProjectAction;
    private NewElementAction newElementAction;
    private DeleteAction deleteAction;
    private NewConnectionAction newConnectionAction;
    private SelectionAction selectionAction;
    private EditPropertiesAction editPropertiesAction;
    private MoveAction moveAction;
    private ZoomOutAction zoomOutAction;
    private ZoomInAction zoomInAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private CenteredElementAction centeredElementAction;
    private OpenTemplateAction openTemplateAction;
    private ExportAsImageAction exportAsImageAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutAction = new AboutAction();
        editAction = new EditAction();
        deleteProjectAction = new DeleteProjectAction();
        addAuthorAction = new AddAuthorAction();
        newElementAction = new NewElementAction();
        deleteAction = new DeleteAction();
        newConnectionAction = new NewConnectionAction();
        selectionAction = new SelectionAction();
        editPropertiesAction = new EditPropertiesAction();
        centeredElementAction = new CenteredElementAction();
        moveAction = new MoveAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();

        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openAction = new OpenAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        openTemplateAction = new OpenTemplateAction();
        exportAsImageAction = new ExportAsImageAction();
    }
}
