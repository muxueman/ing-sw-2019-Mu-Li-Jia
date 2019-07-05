package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.utils.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.RemoteException;


/**
 *
 * The SpawnLocationState class describes the state of the controller
 * where a player has to choose one powerUp card to spawn location
 *
 * @author Xueman Mu
 *
 */

public class SpawnLocationState implements GameServerInterface {


    private MatchController matchController;
    public SpawnLocationState(ServerController serverController, ClientInterface client) throws RemoteException {
        matchController = serverController.getMatch();
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
