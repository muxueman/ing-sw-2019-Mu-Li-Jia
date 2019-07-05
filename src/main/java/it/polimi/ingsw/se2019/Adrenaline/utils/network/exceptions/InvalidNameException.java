package it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException(){
        super("invalid name, try again");
    }
}
