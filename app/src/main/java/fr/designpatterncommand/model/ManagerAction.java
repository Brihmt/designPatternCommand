package fr.designpatterncommand.model;

import android.os.SystemClock;

import java.util.LinkedList;
import fr.designpatterncommand.model.command.Command;
import fr.designpatterncommand.view.FieldView;

// TODO: Ajouter les méthodes qu'il doit contenir (https://lucid.app/lucidchart/7739d042-abe3-40e2-874b-5fccab3fe446/edit?shared=true&page=0_0)
public class ManagerAction {
    private LinkedList<Command> commandList;
    private Character character;

    public ManagerAction(){
        commandList = new LinkedList<Command>();
        character = new Character();
    }

    public void addAction(Command command){
        commandList.add(command);
    }

    public void delAction(Command command){
        commandList.remove(command);
    }

    public void execCommandList(FieldView view){
        for (Command command:commandList) {
            switch (command.operation()){
                case TOP:
                    character.moveTop();
                    break;
                case LEFT:
                    character.moveLeft();
                    break;
                case RIGHT:
                    character.moveRight();
                    break;
                case BOTTOM:
                    character.moveBottom();
                    break;
            }
            view.setCharacter(character);
            SystemClock.sleep(500);
            view.invalidate();
        }
    }
}
