package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.Lobby;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerSetupState implements GameServerInterface {

    private ServerController serverController;
    private Lobby lobby;
    private MatchController matchController;

    public PlayerSetupState(ServerController serverController, Lobby lobby) {
        this.serverController = serverController;
        this.lobby = lobby;
        this.matchController = serverController.getMatch();
        Logger.getGlobal().log(Level.INFO,"player set up state....");
    }

    //update the "username" from client message
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {

        if (lobby.connect(serverController, client, message.getTextMove())) {
            return new WaitingForMatchState();
        }
        return this;
    }
}
