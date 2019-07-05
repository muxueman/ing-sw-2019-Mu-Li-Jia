package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class NotVisibleException extends InvalidShootException{

    public NotVisibleException() {
        super("you cannot see the target!");
    }

}
