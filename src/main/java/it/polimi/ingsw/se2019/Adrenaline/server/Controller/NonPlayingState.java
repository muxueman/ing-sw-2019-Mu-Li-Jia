package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;

import java.rmi.RemoteException;

public class NonPlayingState implements GameServerInterface{

    /**
     *
     * The update method is used to elaborate the message from the Client and update the model.
     * @param message message is the message to elaborate.
     * @param client is the reference to the client interface.
     * @return the current state.
     * @throws RemoteException when there is a RMI communication error.
     *
     */

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        client.updateStatus(new ErrorMessage("Wait for your turn!"));
        return this;
    }
}
