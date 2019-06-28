package it.polimi.ingsw.se2019.Adrenaline.network;


import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.MapStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

public interface UpdatableModel {
    void pingUpdate(String message);
    void updatePlayboard(BoardStatus boardStatus);
    void updatePlayer(PlayerStatus playerStatus);

}

