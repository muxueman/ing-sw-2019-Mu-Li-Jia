
package it.polimi.ingsw.se2019.Adrenaline.server.model;
import it.polimi.ingsw.se2019.Adrenaline.Model.weaponCards.WeaponCard;

import it.polimi.ingsw.se2019.Adrenaline.Model.powerupCards.PowerupCard;

import java.util.*;

/**
 * 
 */
public class PlayBoard  {

    /**
     * Default constructor
     */
    public PlayBoard() {
    }

    private ArrayList<Player> players;

    private ArrayList<Cell> cells;

    private int playBoardMode;

    private int numKillShoot;

    private int numPlayer;

    private Player currentPlayer;

    private ArrayList<Integer> killShootRecord;


    public void initialPlayBoard(int playBoardMode, int numKillShoot) {
        // TODO implement here

    }


    public void getPlayerFromColor(Color color) {
        // TODO implement here

    }


    public Player getCurrentPlayer() {
        // TODO implement here
        return currentPlayer;
    }


    public void nextTurn() {
        // TODO implement here

    }

    public void endGame() {
        // TODO implement here

    }


    public void thisTurn() {
        // TODO implement here

    }


    public void selectFirstPlayer() {
        // TODO implement here

    }

}