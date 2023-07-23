package dsw.gerumap.app.core.state;

import lombok.Getter;

@Getter
public class StateManager {

    DeleteState deleteState = new DeleteState();
    NewConnectionState newConnectionState = new NewConnectionState();
    NewElementState newElementState = new NewElementState();
    SelectionState selectionState = new SelectionState();
    MoveState moveState = new MoveState();

    State currentState = newElementState;

    public void setCurrentState(State cur){
        currentState = cur;
    }

    public void setDeleteState(){
        currentState = deleteState;
    }

    public void setNewConnectionState(){
        currentState = newConnectionState;
    }

    public void setNewElementState(){
        currentState = newElementState;
    }

    public void setSelectionState(){
        currentState = selectionState;
    }
    public void setMoveState(){
        currentState = moveState;
    }
}
