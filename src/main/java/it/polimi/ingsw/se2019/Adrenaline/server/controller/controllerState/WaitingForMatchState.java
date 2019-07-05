package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.utils.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import java.rmi.RemoteException;

public class WaitingForMatchState implements GameServerInterface {

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        client.sendError("Waiting for the match to start...");
        return this;
    }
}
