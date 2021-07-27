package fr.designpatterncommand.model;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

import fr.designpatterncommand.model.command.Command;
import fr.designpatterncommand.view.FieldView;
import fr.designpatterncommand.view.MainActivity;

// (https://lucid.app/lucidchart/7739d042-abe3-40e2-874b-5fccab3fe446/edit?shared=true&page=0_0)
public class ManagerAction {
    private LinkedList<Command> commandList;
    private Character character;
    private Exit exit;

    public ManagerAction(){
        commandList = new LinkedList<Command>();
        character = new Character();
        exit = new Exit();
        Random ran= new Random();
    }

    public void addAction(Command command){
        commandList.add(command);
    }

    public void delAction(Command command){
        commandList.remove(command);
    }

    public void delAllAction() { commandList.clear(); }

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
            SystemClock.sleep(5);
            view.invalidate();

            Log.i("testPerso", "x: " + character.getPosX());
            Log.i("testPerso", "y: " + character.getPosY());
            Log.i("testPerso", "exit x: " + exit.getPosX());
            Log.i("testPerso", "exit y: " + exit.getPosY());
        }
    }

    public void init(FieldView view){
        Random ran= new Random();
        int var1,var2;
        //TODO: restart la sortie/ possition du perso / des murs et les passer à la view
        character.changePosition();

        do{
            exit.changePosition();
        } while (exit.getPosX()==character.getPosX() && exit.getPosY()==character.getPosY());


        view.setCharacter(character);
        view.setExit(exit);
        view.invalidate();
    }
}
