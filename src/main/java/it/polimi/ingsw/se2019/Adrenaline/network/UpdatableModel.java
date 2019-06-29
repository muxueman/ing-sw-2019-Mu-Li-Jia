package it.polimi.ingsw.se2019.Adrenaline.network;


import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;

public interface UpdatableModel {
    void pingUpdate(String message);
    void updatePlayboard(BoardStatus boardStatus);
    void updatePlayer(PlayerStatus playerStatus);
    void updateReconnectionToken(TokenStatus token);
    void updateAdditional(AdditionalStatus additionalStatus);
}

