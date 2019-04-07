
package it.polimi.ingsw.se2019.Adrenaline.Model;
import it.polimi.ingsw.se2019.Adrenaline.Model.Player;
import it.polimi.ingsw.se2019.Adrenaline.Model.Cell;
import it.polimi.ingsw.se2019.Adrenaline.Model.weaponcards.WeaponCard;

import it.polimi.ingsw.se2019.Adrenaline.Model.powerupcards.PowerupCard;
i
import it.polimi.ingsw.se2019.Adrenaline.Model.KillShootTrack;

/**
 * 
 */
public class PlayBoard extends Observable {

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


    public Player getPlayerFromColor(Color color) {
        // TODO implement here
        return player;
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