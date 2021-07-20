package fr.designpatterncommand.model.command;

import fr.designpatterncommand.model.Movement;

// TODO: Coder les classes qui dérivent de celle-ci. (Les images sont les reférences aux images des ressources drawable)
public abstract class Command {
    protected static int numImage;
    public abstract Movement operation();
    public int getNumImage(){return numImage;}
}
