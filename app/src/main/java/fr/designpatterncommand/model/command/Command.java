package fr.designpatterncommand.model.command;

import fr.designpatterncommand.model.Movement;

public abstract class Command {
    protected static int numImage;
    public abstract Movement operation();
    public int getNumImage(){return numImage;}
}
