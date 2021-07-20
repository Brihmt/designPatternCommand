package fr.designpatterncommand.model.command;

import fr.designpatterncommand.R;
import fr.designpatterncommand.model.Movement;

public class ActionBottom extends Command{
    public ActionBottom() {
        numImage = R.drawable.arrowbottom;
    }

    @Override
    public Movement operation() {
        return Movement.BOTTOM;
    }
}
