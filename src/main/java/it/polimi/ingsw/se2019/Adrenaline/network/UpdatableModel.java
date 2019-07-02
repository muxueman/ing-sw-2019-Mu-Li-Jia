package it.polimi.ingsw.se2019.Adrenaline.network;


import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;

import java.util.List;

public interface UpdatableModel {

    /**
     * The pingUpdate method notifies the View telling that nothing
     * has changed from the previous state (possibly useful for GUI).
     */
    void setBoardStatus(Board board);
    void pingUpdate(String message);
    void updatePlayer(Player player);
    void updateMap(Map map);
    void updateSkull(Board board);
    void updateAllPlayers(Board board);
    void updateAdditional(AdditionalStatus additionalStatus);
    void updateReconnectionToken(TokenStatus token);
}