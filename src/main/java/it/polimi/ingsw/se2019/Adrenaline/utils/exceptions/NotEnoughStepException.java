package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

public class NotEnoughStepException extends InvalidRunException {

    public NotEnoughStepException() {
        super("you do not have enough steps!");
    }
}
