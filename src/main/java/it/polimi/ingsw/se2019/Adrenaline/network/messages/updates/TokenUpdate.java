package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;


import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.TokenStatus;

public class TokenUpdate implements StatusUpdate {

    private TokenStatus token;

    public TokenUpdate(String reconnectionToken) {
        token = new TokenStatus(reconnectionToken);
    }

    @Override
    public void updateStatus(Model model) {
        model.updateReconnectionToken(token);
    }
}
