package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class InvalidRunException extends Exception{

    public InvalidRunException() {
        super("cannot move, its not reachable!");
    }

    public InvalidRunException(String message) {
        super("cannot move, due to " + message);
    }
}
