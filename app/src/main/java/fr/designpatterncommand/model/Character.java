package fr.designpatterncommand.model;

import android.widget.ImageView;

import java.util.Vector;

// TODO: Ajouter une image du perso/ ses déplacements
public class Character {
    private int posX, posY;
    private int characterImg;

    public Character(){
        posX = 1;
        posY = 1;
        //characterImg = R.drawable.character;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void moveTop(){
        if(posY>1) posY--;
    }

    public void moveBottom(){
        if(posY<5) posY++;
    }

    public void moveLeft(){
        if(posX>1) posX--;
    }

    public void moveRight(){
        if(posX<8) posX++;
    }

    public void move(Movement movement){
        if(movement==Movement.TOP) moveTop();
        if(movement==Movement.BOTTOM) moveBottom();
        if(movement==Movement.LEFT) moveLeft();
        if(movement==Movement.RIGHT) moveRight();
    }
}
