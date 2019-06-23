package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.updates.MapUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;

import java.rmi.RemoteException;
import java.util.List;


public class ChooseMapState implements GameServerInterface {
    private ServerController serverController;
    private MatchController matchController;
    private List<Map> chooseMaps;


    public ChooseMapState(ServerController serverController, ClientInterface client) throws RemoteException {
        this.serverController = serverController;
        matchController = serverController.getMatch();
        //chooseMaps = matchController.getChoosableMaps(client);
        ServerMessage serverMessage = new ServerMessage("CHOOSEMAP", true, new MapUpdate());//需改进
//        PrivateObjectiveCard privateObjectiveCard = matchController.getPrivateObjectiveCard(client);
//        serverMessage.addStatusUpdate(new PrivateObjectiveUpdate(privateObjectiveCard));
//        client.updateStatus(new PlayMessage());
//        client.updateStatus(serverMessage);
    }

    //the message from the Client and update the model.
    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        //int index = message.getMainParam() - 1;
        //if (message.getTextMove().equalsIgnoreCase("CHOOSEMAP") && index >= 0 && index < chooseMaps.size()) {
        if (message.getTextMove().equalsIgnoreCase("CHOOSEMAP")) {
            matchController.chooseMap(client, message.getMainParam());
            matchController.initPlayer(client);
            return new WaitingForMatchState();
        }
        client.sendError("Can't choose the map!");
        return this;
    }
}