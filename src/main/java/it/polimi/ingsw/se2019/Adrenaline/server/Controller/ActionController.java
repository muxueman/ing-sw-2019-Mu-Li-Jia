package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.PlayServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ActionController{

    private int actionMode;
    public ActionController() {
    }

    public void TurnStep(int actionMode, Player player) {

        // we have Action(step), Round and Turn to represent
        int currentRound = 1;
        int currentStep = 1;
        int numRound = 1;
        ArrayList<String> seriesOFActions = new ArrayList<>();

        if (actionMode != 4)
            numRound = 2;
/*
        while(currentRound <= numRound){
            switch(actionMode){
                case 0: case 1: case 2:
                    showAvailableAction(["run", "grab", "shoot"]);

            }






            currentRound++;
        }
        if(actionMode !=3 || actionMode !=4 )
            state.update(reloadAction);
    }
    */
}
//public class ActionController implements PlayServerInterface {
/*
    private ServerController serverController;
    private MatchController matchController;
    private List<WindowPatternCard> windowPatternCards;

    public ChoosePatternState(ServerController serverController, ClientInterface client) throws RemoteException {
        this.serverController = serverController;
        matchController = serverController.getMatch();
        windowPatternCards = matchController.getChoosableWindowPatternCards(client);
        ServerMessage serverMessage = new ServerMessage("CHOOSE", true, new WindowPatternUpdate(windowPatternCards));
        PrivateObjectiveCard privateObjectiveCard = matchController.getPrivateObjectiveCard(client);
        serverMessage.addStatusUpdate(new PrivateObjectiveUpdate(privateObjectiveCard));
        client.updateStatus(new PlayMessage());
        client.updateStatus(serverMessage);
    }

    /**
     *
     * The update method is used to elaborate the message from the Client and update the model.
     * @param message message is the message to elaborate.
     * @param client is the reference to the client interface.
     * @return the current state.
     * @throws RemoteException when there is a RMI communication error.
     *

    @Override
    public PlayServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        int index = message.getMainParam() - 1;
        if (message.getTextMove().equalsIgnoreCase("CHOOSE") && index >= 0 && index < windowPatternCards.size()) {
            matchController.chooseWindowPatternCard(client, windowPatternCards.get(index));
            matchController.initPlayer(client);
            return new WaitingForMatchState();
        }
        client.sendError("Can't choose the window pattern card!");
        return this;
    }
    */

    }