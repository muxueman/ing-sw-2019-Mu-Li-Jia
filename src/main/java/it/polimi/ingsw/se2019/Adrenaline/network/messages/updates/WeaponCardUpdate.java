package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import java.util.List;

/**
 *
 * The PowerupCardUpdate object is used to update the client-side status of the powerupCards.
 * It's sent from the server to the client.
 *
 * @author Xueman Mu
 */


public class WeaponCardUpdate implements StatusUpdate {

    private List<WeaponCard> weaponCards;

    public WeaponCardUpdate(List<WeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    public List<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        //model.updateOptionalWeaponCards(weaponCards);
    }
}