package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class InvalidShootException extends Exception{

    public InvalidShootException() {
        super("cannot shoot!");
    }

    public InvalidShootException(String message) {
        super("cannot shoot, due to " + message);
    }
}
