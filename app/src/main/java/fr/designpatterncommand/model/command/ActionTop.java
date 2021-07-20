package fr.designpatterncommand.model.command;

import fr.designpatterncommand.R;
import fr.designpatterncommand.model.Movement;

public class ActionTop extends Command {
    public ActionTop() {
        numImage = R.drawable.arrowtop;
    }

    @Override
    public Movement operation() {
        return Movement.TOP;
    }
}
