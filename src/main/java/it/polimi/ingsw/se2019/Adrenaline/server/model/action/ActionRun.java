package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;

import java.io.Serializable;

/**
 * interface ActionStrategy
 * @author Xueman Mu
 */

public class ActionRun implements Serializable {
    // If the player cant reach the cell he wanted to go, this function will return false, then the player have to decide
    // direction to run
    public boolean ActionRun (Player player, int direction) throws InvalidRunException {
        boolean runSucceed = true;
        Cell nextcell = player.getCurrentCell().getNextCell(direction);
        if(nextcell == null) {
            runSucceed = false;
            throw new InvalidRunException();
        }
        player.getCurrentCell().removePlayer(player);
        player.setCurrentCell(nextcell);
        player.getCurrentCell().addPlayer(player);
        return runSucceed;
    }
}