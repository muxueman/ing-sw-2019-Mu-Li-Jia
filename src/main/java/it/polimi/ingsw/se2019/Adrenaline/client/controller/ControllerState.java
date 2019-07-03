package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.EndGameState;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.NonPlayingState;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.WaitingResponseState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;

import java.util.List;
import java.util.logging.Logger;

public abstract class ControllerState {

    protected ClientController clientController;
    protected ControllerState previousState;
    protected String message;

    public ControllerState(){}
    //constructor
    protected ControllerState(ClientController clientController, String message){
        this.clientController = clientController;
        this.previousState = clientController.getState();
        this.message = message;
    }

    //deal with the message from client, if it is accepted updates the state of the controller, else sends error.
    public abstract ControllerState update(String message);
    //deal with the server message, if it is accepted change the status of the controller, else sends error.
    public ControllerState updateStatus(ServerMessage serverMessage){
        List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
        for (StatusUpdate statusUpdate : statusUpdates) {
            statusUpdate.updateStatus(clientController.getModel());
        }
        clientController.getModel().pingUpdate(serverMessage.getMessage());
        if (serverMessage.isError()) {
            if(serverMessage.getMessage().equals("ENDGAME")) {
                Logger.getGlobal().info("next state: end game");
                return new EndGameState(clientController).initState();
            }
            if(serverMessage.getMessage().equals("TIMER")) {
                Logger.getGlobal().info("next state: waiting for response");
                return new WaitingResponseState(clientController, new NonPlayingState(clientController));
            }
            clientController.reportError(serverMessage.getMessage());
        }
        Logger.getGlobal().info("next state: next state");
        return nextState(serverMessage.isError(), serverMessage.isPlaying());
    }

    //next state if called by a response from the server, return next state
    protected ControllerState nextState(boolean error, boolean playing) {
        return initState();
    }

    //create new state of controller (inform clientController and view of client), return myself
    public ControllerState initState() {
        sendMessage();
        return this;
    }

    //send message to the controller, then view
    protected void sendMessage() {
        clientController.sendMessage(message);
    }


}