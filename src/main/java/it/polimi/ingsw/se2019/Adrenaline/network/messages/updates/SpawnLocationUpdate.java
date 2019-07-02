package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;

public class SpawnLocationUpdate implements StatusUpdate{

    private Board board;
    private Map map;
    public SpawnLocationUpdate(Board board, Map map){
        this.board = board;
        this.map = map;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateSpawnLocation(board, map);
    }
}
