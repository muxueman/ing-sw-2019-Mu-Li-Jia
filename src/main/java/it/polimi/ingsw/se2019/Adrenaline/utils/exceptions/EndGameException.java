package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

/**
 * The class EndGameException represents a exception and
 * it's thrown when game has ended.
 * @author Xueman Mu
 */

public class EndGameException extends Exception{

    public EndGameException() {
        super("The game has ended!");
    }
}
