package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;


import java.rmi.RemoteException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ChooseMapState implements GameServerInterface {
    private ServerController serverController;
    private MatchController matchController;

    public ChooseMapState(ServerController serverController, ClientInterface client) throws RemoteException {
        Logger.getGlobal().log(Level.INFO,"choose map state..... ");
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
        Logger.getGlobal().log(Level.INFO,"CHOOSEMAP: {0} ",message.getMainParam());
        if (message.getTextMove().equals("CHOOSEMAP")) {
            matchController.chooseMap(message.getMainParam());
            //matchController.initPlayer(client);
            //Logger.getGlobal().log(Level.INFO,"init player");
            return new ChooseKillState(serverController,client);
        }
        client.sendError("Can't choose the kill number!");
        return this;
    }
}