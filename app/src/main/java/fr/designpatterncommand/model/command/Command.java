package fr.designpatterncommand.model.command;
// TODO: Coder les classes qui dérivent de celle-ci. (Les images sont les reférences aux images des ressources drawable)
public abstract class Command {
    protected static int numImage;
    public abstract void operation(Character perso);
    public int getNumImage(){return numImage;}
}
