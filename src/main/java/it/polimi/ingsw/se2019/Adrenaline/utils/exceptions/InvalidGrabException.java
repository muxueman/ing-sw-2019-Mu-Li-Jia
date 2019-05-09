package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

/**
 * @author Xueman Mu
 */

public class InvalidGrabException extends Exception{

    public InvalidGrabException() {
        super("cannot grab");
    }

    public InvalidGrabException(String message) {
        super("cannot grab, due to " + message);
    }
}