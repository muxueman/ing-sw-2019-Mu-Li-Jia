package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class NotReloadWeaponCardException extends InvalidCardUseException {

    public NotReloadWeaponCardException() {
        super("you haven't reload this card!") ;
    }
}
