package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;

import java.util.List;

/**
 *
 * The PowerupCardUpdate object is used to update the client-side status of the powerupCards.
 * It's sent from the server to the client.
 *
 * @author Xueman Mu
 */


public class PowerupCardUpdate implements StatusUpdate {

    private List<PowerupCard> powerupCards;

    public PowerupCardUpdate(List<PowerupCard> powerupCards) {
        this.powerupCards = powerupCards;
    }

    public List<PowerupCard> getPowerupCards() {
        return powerupCards;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        //odel.updateOptionalPowerupCards(powerupCards);
    }
}