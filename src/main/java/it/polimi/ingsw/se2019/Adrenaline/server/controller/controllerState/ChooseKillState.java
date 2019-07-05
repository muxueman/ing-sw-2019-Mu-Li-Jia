package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChooseKillState implements GameServerInterface {
    private ServerController serverController;
    private MatchController matchController;

    public ChooseKillState(ServerController serverController)  {
        Logger.getGlobal().log(Level.INFO, "choose kill state..... ");
        this.serverController = serverController;
        matchController = serverController.getMatch();
    }

    //the message from the Client and update the model.
    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        Logger.getGlobal().log(Level.INFO, "CHOOSEKILL: {0} ", message.getMainParam());
        if (message.getTextMove().equals("CHOOSEKILL")) {
            matchController.chooseKill(message.getMainParam());
            return new SpawnLocationState(serverController,client);
        }
        client.sendError("Can't choose the map!");
        return this;
    }
}