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

    void pingUpdate(String message);
    void updateMap(Map map);
    void updateBoard(Board board);
    void updatePlayerBoard(PlayerBoard playerBoard);
    void updatePlayer(Player player);
//    void updateReconnectionToken(TokenStatus token);
//    void updateAdditional(AdditionalStatus additionalStatus);
//    void updateOptionalPowerupCards(List<PowerupCard> powerupCardsCards);
//    void updateOptionalWeaponCards(List<WeaponCard> weaponCards);
}

