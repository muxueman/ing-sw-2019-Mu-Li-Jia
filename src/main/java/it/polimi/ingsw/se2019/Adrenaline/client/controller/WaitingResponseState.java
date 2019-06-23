package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

import java.util.logging.Logger;

public class WaitingResponseState extends ControllerState{

    private ControllerState nextState;
    private boolean wpc;

    //constructor: wait a response from server
    public WaitingResponseState(ClientController clientController, ControllerState nextState) {
        super(clientController, "Waiting for a response from the server...");
        this.nextState = nextState;
        wpc = true;
    }
    public WaitingResponseState(ClientController clientController, ControllerState previousState, ControllerState nextState) {
        this(clientController, nextState);
        this.previousState = previousState;
        wpc = true;
    }

    @Override
    public ControllerState initState(){
        return this;
    }

    @Override
    public ControllerState update(String message) {
        sendMessage();
        return this;
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage){
        if(serverMessage.getMessage().equals("CONNECT")){
            wpc = false;
            nextState = new NonPlayingState(clientController);
            Logger.getGlobal().info("next state: nonplaying state");
        }
        return super.updateStatus(serverMessage);
    }
    @Override
    public ControllerState nextState(boolean error, boolean playing) {
        if (error) {
            return previousState.initState();
        } else if (playing) {
            clientController.nextView(wpc);
            Logger.getGlobal().info("next state: select map state");
            return nextState.initState();
        } else {
            return this;
        }
    }
    @Override
    public void sendMessage() {
        clientController.reportError(message);
    }
}