package it.polimi.ingsw.se2019.Adrenaline.server.controller;


import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.PlayServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.Lobby;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayBoard;

import java.rmi.RemoteException;

//set the username
public class SetupState implements PlayServerInterface {

    private ServerController serverController;
    private Lobby lobby;

    SetupState(ServerController serverController, PlayBoard playBoard){
        this.serverController = serverController;
        this.lobby = lobby;
    }

    //The update method is used to elaborate the message from the Client and update the model.
    @Override
    public PlayServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        //if (lobby.connect(serverController, client, message.getTextMove())) {
        //    return new WaitingForMatchState();
       // }
        return this;
    }

}
