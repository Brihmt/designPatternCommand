package fr.designpatterncommand.model.command;

import fr.designpatterncommand.R;
import fr.designpatterncommand.model.Movement;

public class ActionLeft extends Command{
    public ActionLeft() {
        numImage = R.drawable.arrowleft;
    }

    @Override
    public Movement operation() {
        return Movement.LEFT;
    }
}
