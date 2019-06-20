package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.Lobby;

import java.rmi.RemoteException;

public class PlayerSetupState implements GameServerInterface {

    private ServerController serverController;
    private Lobby lobby;

    PlayerSetupState(ServerController serverController, Lobby lobby) {
        this.serverController = serverController;
        this.lobby = lobby;
    }

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

        System.out.println("trying to connect");
        if (lobby.connect(serverController, client, message.getTextMove())) {
            return new WaitingForMatchState();
        }
        return this;
    }
}
