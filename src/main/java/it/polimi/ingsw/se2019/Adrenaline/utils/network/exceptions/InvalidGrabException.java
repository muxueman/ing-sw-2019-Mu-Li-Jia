package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

/**
 * @author Xueman Mu
 */

public class InvalidGrabException extends Exception{

    public InvalidGrabException() {
        super("cannot grab,");
    }

    public InvalidGrabException(String message) {
        super("cannot grab, you have more than 3 " + message);
    }
}
