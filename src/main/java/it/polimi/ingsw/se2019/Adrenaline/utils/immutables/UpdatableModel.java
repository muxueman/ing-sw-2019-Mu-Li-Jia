package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;

public interface UpdatableModel {
    void pingUpdate(String message);
    void updateMap(Map selectedMap);

}

