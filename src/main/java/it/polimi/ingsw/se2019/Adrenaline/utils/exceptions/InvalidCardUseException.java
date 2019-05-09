package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

public class InvalidCardUseException extends Exception {

    public InvalidCardUseException() {
        super("cannot use this cards");
    }

    public InvalidCardUseException(String message) {
        super("cannot use this cards, due to " + message);
    }
}
