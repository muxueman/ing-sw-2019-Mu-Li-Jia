package it.polimi.ingsw.se2019.Adrenaline.network;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

public interface UpdatableModel {
    void pingUpdate(String message);
    void updateMap(Map selectedMap);
    void updatePlayer(PlayerStatus playerStatus);

}

