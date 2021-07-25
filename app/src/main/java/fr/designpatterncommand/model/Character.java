package fr.designpatterncommand.model;

import android.widget.ImageView;

import java.util.Random;
import java.util.Vector;

import fr.designpatterncommand.R;

// TODO: Ajouter une image du perso/ ses déplacements
public class Character {
    private int posX, posY;
    private int characterImg;

    public Character(){
        Random ran= new Random();
        posX = ran.nextInt(8);
        posY = ran.nextInt(5);
        characterImg = R.drawable.character;
    }

    public void changePosition(){
        Random ran=new Random();
        posX=ran.nextInt(8);
        posY=ran.nextInt(5);
    }
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getImg(){ return characterImg; }

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

}
