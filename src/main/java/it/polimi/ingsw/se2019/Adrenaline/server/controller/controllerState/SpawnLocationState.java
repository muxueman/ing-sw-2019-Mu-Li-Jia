package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.PlayMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.PowerupCardUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * The SpawnLocationState class describes the state of the controller
 * where a player has to choose one powerUp card to spawn location
 *
 * @author Xueman Mu
 *
 */

public class SpawnLocationState implements GameServerInterface {

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

    //the message from the Client and update the model.
    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {

        if (message.getTextMove().equals("SPAWNLOCATION")) {
            matchController.spawnLocationDrop(message.getMainParamS(),client);
            return new WaitingForMatchState();
        }
        client.sendError("cannot spawn location while dropping this powerup card!");
        return this;
    }
}
