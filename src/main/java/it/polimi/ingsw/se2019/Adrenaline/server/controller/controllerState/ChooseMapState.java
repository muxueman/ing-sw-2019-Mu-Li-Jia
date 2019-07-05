package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.utils.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChooseMapState implements GameServerInterface {
    private ServerController serverController;
    private MatchController matchController;

    public ChooseMapState(ServerController serverController) {
        Logger.getGlobal().log(Level.INFO,"choose map state..... ");
        this.serverController = serverController;
        matchController = serverController.getMatch();
    }

    //the message from the Client and update the model.
    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        Logger.getGlobal().log(Level.INFO,"CHOOSEMAP: {0} ",message.getMainParam());
        if (message.getTextMove().equals("CHOOSEMAP")) {
            matchController.chooseMap(message.getMainParam());
            return new ChooseKillState(serverController);
        }
        client.sendError("Can't choose the kill number!");
        return this;
    }
}