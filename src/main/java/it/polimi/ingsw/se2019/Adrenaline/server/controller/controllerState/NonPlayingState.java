package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.utils.network.ClientInterface;

import it.polimi.ingsw.se2019.Adrenaline.utils.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ErrorMessage;

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
