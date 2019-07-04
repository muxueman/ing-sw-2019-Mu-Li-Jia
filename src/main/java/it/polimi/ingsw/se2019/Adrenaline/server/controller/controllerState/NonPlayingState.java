package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;

import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NonPlayingState implements GameServerInterface{

//    private MatchController matchController;
//    private ClientInterface client;
//
//    public NonPlayingState(ClientInterface client, MatchController matchController){
//        this.matchController = matchController;
//        this.client = client;
//    }
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
     //   Logger.getGlobal().log(Level.INFO," not play! current player:", matchController.getPlayers().get(client).getUserName());
        client.updateStatus(new ErrorMessage("Wait for your turn!"));
        return this;
    }
}
