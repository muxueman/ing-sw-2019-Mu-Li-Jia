package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

public class InvalidReloadException extends Exception{

    public InvalidReloadException() {
        super("cannot reload");
    }

    public InvalidReloadException(String message) {
        super("cannot reload, due to " + message);
    }
}
