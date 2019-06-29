package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.PlayMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.PowerupCardUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * The SpawnLocationState class describes the state of the controller
 * where a player has to choose one powerUp card to spawn location
 *
 * @author Xueman Mu
 *
 */

public class SpawnLocationState {

    private ServerController serverController;
    private MatchController matchController;
    private List<PowerupCard> powerupCards;

    public SpawnLocationState(ServerController serverController, ClientInterface client) throws RemoteException {
        this.serverController = serverController;
        matchController = serverController.getMatch();
        powerupCards = matchController.getChoosablePowerupCards(client);
        ServerMessage serverMessage = new ServerMessage("CHOOSE", true, new PowerupCardUpdate(powerupCards));
        client.updateStatus(new PlayMessage());
        client.updateStatus(serverMessage);
    }
}
