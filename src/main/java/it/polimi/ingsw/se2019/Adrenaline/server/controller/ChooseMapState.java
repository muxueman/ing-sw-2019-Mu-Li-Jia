package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;

import java.rmi.RemoteException;
import java.util.List;


public class ChooseMapState implements GameServerInterface {
    private ServerController serverController;
    private MatchController matchController;
    private List<Map> choooseMap;


    public ChooseMapState(ServerController serverController, ClientInterface client) throws RemoteException {
        this.serverController = serverController;
        matchController = serverController.getMatch();
        choooseMap = matchController.getChoosableMap(client);
//        ServerMessage serverMessage = new ServerMessage("CHOOSE", true, new WindowPatternUpdate(windowPatternCards));
//        PrivateObjectiveCard privateObjectiveCard = matchController.getPrivateObjectiveCard(client);
//        serverMessage.addStatusUpdate(new PrivateObjectiveUpdate(privateObjectiveCard));
//        client.updateStatus(new PlayMessage());
//        client.updateStatus(serverMessage);
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
//        int index = message.getMainParam() - 1;
//        if (message.getTextMove().equalsIgnoreCase("CHOOSE") && index >= 0 && index < windowPatternCards.size()) {
//            matchController.chooseWindowPatternCard(client, windowPatternCards.get(index));
//            matchController.initPlayer(client);
//            return new WaitingForMatchState();
//        }
        client.sendError("Can't choose the window pattern card!");
        return this;
    }
}
