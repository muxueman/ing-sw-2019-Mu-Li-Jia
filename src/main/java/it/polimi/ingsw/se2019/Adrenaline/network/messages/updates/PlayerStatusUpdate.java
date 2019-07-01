package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;
/**
 *
 * The PlayerStatusUpdate object is used to update the client-side status of the playerStatus.
 * including the powerupcards owened, and weaponcards owned
 * It's sent from the server to the client.
 *
 * @author Xueman Mu
 *
 */
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

public class PlayerStatusUpdate implements StatusUpdate {

    private Player player;

    public PlayerStatusUpdate(PlayerStatus playerStatus) {
        this.player = player;
    }

    @Override
    //The updateStatus function is used to update the client-side status
    public void updateStatus(UpdatableModel model) {
        model.updatePlayer(player);
    }
}
