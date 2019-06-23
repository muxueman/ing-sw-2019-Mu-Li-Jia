package it.polimi.ingsw.se2019.Adrenaline.network.updates;


import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

public class PlayerStatusUpdate implements StatusUpdate {

    private PlayerStatus playerStatus;
    public PlayerStatusUpdate(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }


    @Override
    public void updateStatus(UpdatableModel model) {
        model.updatePlayer(playerStatus);
    }
}
