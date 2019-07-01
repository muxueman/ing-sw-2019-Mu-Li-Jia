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

    void pingUpdate(String message);
    //void updatePlayer(PlayerStatus playerStatus);
    void updateMap(Map map);

    //void updateDraftPool(DraftPoolStatus draftPool);

    //void updateRoundTrack(RoundTrackStatus roundTrack);

    //void updatePublicObjectiveCards(List<PublicObjectiveCard> publicObjectiveCards);

    //void updateToolCards(List<ToolCardStatus> toolCards);

    //void updatePrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard);

    //void updateAdditional(AdditionalStatus additionalStatus);

    //void updateChoosablePatterns(List<WindowPatternCard> windowPatternCards);

    //void updateReconnectionToken(TokenStatus token);
}