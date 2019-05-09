package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

public class NotVisibleException extends InvalidShootException{

    public NotVisibleException() {
        super("you cannot see the target!");
    }

}
