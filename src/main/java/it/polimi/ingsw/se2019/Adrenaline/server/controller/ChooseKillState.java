package it.polimi.ingsw.se2019.Adrenaline.server.controller;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChooseKillState implements GameServerInterface {
    private ServerController serverController;
    private MatchController matchController;

    public ChooseKillState(ServerController serverController, ClientInterface client) throws RemoteException {
        Logger.getGlobal().log(Level.INFO, "choose kill state..... ");
        this.serverController = serverController;
        matchController = serverController.getMatch();
//        matchController = serverController.getMatch();
//        ServerMessage serverMessage = new ServerMessage("CHOOSEMAP", true, new MapUpdate());//需改进
//        //MapNum = matchController.getSelectedMap();
//        //serverMessage.addStatusUpdate(new MapUpdate(MapNum));
//        serverMessage.addStatusUpdate(new MapUpdate());
//        client.updateStatus(new PlayMessage());
//        client.updateStatus(serverMessage);
    }

    //the message from the Client and update the model.
    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        Logger.getGlobal().log(Level.INFO, "CHOOSEKILL: {0} ", message.getMainParam());
        if (message.getTextMove().equals("CHOOSEKILL")) {
            matchController.chooseKill(message.getMainParam());
            //matchController.initPlayer(client);
            //在选择所有玩家选择完kill并更新之后再initPlayer;
            return new WaitingForMatchState();
        }
        client.sendError("Can't choose the map!");
        return this;
    }
}