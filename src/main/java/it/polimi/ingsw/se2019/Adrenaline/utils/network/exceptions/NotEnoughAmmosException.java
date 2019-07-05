package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class NotEnoughAmmosException extends InvalidCardUseException {

    public NotEnoughAmmosException() {
        super("you do not have enough ammos!");
    }
}
