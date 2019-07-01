package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.TokenStatus;

public class TokenUpdate implements StatusUpdate {

    private TokenStatus token;

    public TokenUpdate(String reconnectionToken) {
        token = new TokenStatus(reconnectionToken);
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateReconnectionToken(token);
    }
}
