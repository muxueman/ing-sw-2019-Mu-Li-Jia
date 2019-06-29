package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

/**
 *
 * The PlayerStatusUpdate object is used to update the client-side status of the playerStatus.
 * It's sent from the server to the client.
 *
 * @author Xueman Mu
 *
 */

public class PlayerStatusUpdate implements StatusUpdate {

    private PlayerStatus playerStatus;

    public PlayerStatusUpdate(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    @Override
    //The updateStatus function is used to update the client-side status
    public void updateStatus(UpdatableModel model) {
        model.updatePlayer(playerStatus);
    }
}
