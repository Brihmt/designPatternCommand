package fr.designpatterncommand.model.command;

import fr.designpatterncommand.R;
import fr.designpatterncommand.model.Movement;

public class ActionRight extends Command{
    public ActionRight() {
        numImage = R.drawable.arrowright;
    }

    @Override
    public Movement operation() {
        return Movement.RIGHT;
    }
}
