package dsw.gerumap.app.gui.swing.Commands;

import dsw.gerumap.app.core.ApplicationFramework;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    //lista koja predstavlja stek na kome se nalaze konkretne izvršene komande
    private List<AbstractCommand> commands = new ArrayList<AbstractCommand>();
    //pokazivač steka, sadrži redni broj komande za undo / redo operaciju
    private int currentCommand = 0;

    /*
     * Dodaje novu komandu na stek i poziva izvršavanje komande
     */
    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    /*
     * Metoda koja poziva izvršavanje konkretne komande
     */
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().getGui().disableRedoAction();
        }
    }

    /*
     * Metoda koja poziva redo konkretne komande
     */
    public void undoCommand(){
        if(currentCommand > 0){
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }
}
