package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class NotEnoughAmmoTileException extends InvalidGrabException{

    public NotEnoughAmmoTileException() {
        super("this place haven't reload the ammoTile!");
    }
}
