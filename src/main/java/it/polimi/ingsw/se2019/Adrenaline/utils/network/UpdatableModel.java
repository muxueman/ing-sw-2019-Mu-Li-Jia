package it.polimi.ingsw.se2019.Adrenaline.utils.network;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;

public interface UpdatableModel {

    void setBoardStatus(Board board);
    void pingUpdate(String message);
    void updatePlayer(Player player);
    void updateMap(Map map);
    void updateSkull(Board board);
    void updateAllPlayers(Board board);
    void updateAdditional(AdditionalStatus additionalStatus);
    void updateReconnectionToken(TokenStatus token);
    void updateSpawnLocation(Board board, Map map);
}

