package fr.designpatterncommand.model;

import java.util.Random;

import fr.designpatterncommand.R;

public class Exit {
    private int posX, posY;
    private int exitImg;

    public Exit(){
        Random ran = new Random();
        posX = 1 + ran.nextInt(7);
        posY = 1 + ran.nextInt(4);
        exitImg = R.drawable.door;
    }

    public void changePosition(){
        Random ran=new Random();
        posX = 1 + ran.nextInt(7);
        posY = 1 + ran.nextInt(4);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getImg() {
        return exitImg;
    }
}
