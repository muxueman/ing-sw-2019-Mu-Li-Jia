package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

public class InvalidRunException extends Exception{

    public InvalidRunException() {
        super("cannot move!");
    }

    public InvalidRunException(String message) {
        super("cannot move, due to " + message);
    }
}
