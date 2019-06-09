package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;

/**
 * interface ActionStrategy
 * @author Xueman Mu
 */

public class ActionRun{
    // If the player cant reach the cell he wanted to go, this function will return false, then the player have to decide
    // direction to run
    public boolean takeAction(Player player, int direction){
        boolean runSucceed = true;
        Cell nextcell = player.getCurrentCell().getNextCell(direction);
        if(nextcell == null) return false;
        player.getCurrentCell().removePlayer(player);
        player.setCurrentCell(nextcell);
        player.getCurrentCell().addPlayer(player);
        return runSucceed;
    }
}