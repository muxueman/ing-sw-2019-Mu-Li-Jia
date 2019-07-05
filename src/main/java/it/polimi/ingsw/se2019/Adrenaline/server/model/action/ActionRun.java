package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidRunException;

import java.io.Serializable;

/**
 * interface ActionStrategy
 * @author Xueman Mu
 */

public class ActionRun implements Serializable {
    // If the player cant reach the cell he wanted to go, this function will return false, then the player have to decide
    // direction to run
    Player player;
    public ActionRun(Player player) {
        this.player = player;
    }
    public boolean ActionRun (int direction) throws InvalidRunException {
        boolean runSucceed = true;
        Cell nextcell = player.getCurrentCell().getNextCell(direction);
        if(nextcell == null) {
            runSucceed = false;
            throw new InvalidRunException();
        }
        player.setCurrentCell(nextcell);
        return runSucceed;
    }
    public boolean ActionRunToCell(int CellID) throws InvalidRunException {
        for(Cell c : player.getCurrentCell().getAdjacentCells()){
            if(c.getCellID() == CellID) {
                player.setCurrentCell(c);
                return true;
            }
        }
        throw new InvalidRunException();
    }
}