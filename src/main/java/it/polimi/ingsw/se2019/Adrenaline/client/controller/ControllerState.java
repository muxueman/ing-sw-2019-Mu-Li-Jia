package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.EndGameState;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.NonPlayingState;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.WaitingResponseState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;

import java.util.List;
import java.util.logging.Logger;
/**
 *
 * The ControllerState class is the abstract representation of the states of the controller.
 *
 * @author Mu xueman
 *
 */
public abstract class ControllerState {

    protected ClientController clientController;
    protected ControllerState previousState;
    protected String message;

    public ControllerState(){}
    /**
     *
     * The constructor creates the state of the controller.
     * @param clientController is the reference to the Controller.
     * @param message is the message to be elaborated.
     *
     */
    protected ControllerState(ClientController clientController, String message){
        this.clientController = clientController;
        this.previousState = clientController.getState();
        this.message = message;
    }

    /**
     *The controllerstate is deal with the message from client, if it is accepted updates the state of the controller, else sends error.
     * deal with the server message, if it is accepted change the status of the controller, else sends error.
     *
     * @param message
     * @return
     */
    public abstract ControllerState update(String message);
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

    /**
     *
     * The nextState method gives the Controller a new state and is called
     * by a response from the server. The default method simply returns the
     * state on which it's called, while some states define their own method.
     * @param error tells if the response is an error or not.
     * @param playing tells if the player is playing or not.
     * @return the new state for the Controller.
     *
     */    protected ControllerState nextState(boolean error, boolean playing) {
        return initState();
    }

    /**
     *
     * The initState method is used to create the new state of the controller.
     * @return the new controller state.
     *
     */
    public ControllerState initState() {
        sendMessage();
        return this;
    }

    /**
     *
     * The sendMessage method is used to send the message to the controller.
     *
     */
    protected void sendMessage() {
        clientController.sendMessage(message);
    }


}