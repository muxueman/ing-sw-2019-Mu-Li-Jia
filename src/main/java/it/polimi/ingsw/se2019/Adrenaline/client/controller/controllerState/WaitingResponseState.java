package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.GUIView;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ViewMessage;

import java.util.logging.Logger;

public class WaitingResponseState extends ControllerState {

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
        switch (serverMessage.getMessage()){
            case("CONNECT"):
                wpc = false;
                nextState = new NonPlayingState(clientController);
                Logger.getGlobal().info("next state: nonplaying state");
                return super.updateStatus(serverMessage);
            case("FIGURE"):
                String figureColor = String.valueOf(serverMessage.getSubParameter());
                String messageColor = "your color of figure " + figureColor;
                clientController.sendMessage(messageColor);
                return this;
            case("CHOOSEMAP"):
                String finalMap = String.valueOf(serverMessage.getParm());
                String messageMap = "the map of this match: " + finalMap;
                ViewMessage messageVM = new ViewMessage(messageMap, finalMap);
                if (clientController.getView() instanceof GUIView){
                    clientController.sendMessage(messageVM.getParmS());
                }
                else clientController.sendMessage(messageMap);
                return nextState(false,true);
            case("CHOOSEKILL"):
                String finalKill = String.valueOf(serverMessage.getParm());
                String messageKill = "the kill number of this match: " + finalKill;
                ViewMessage messageVK = new ViewMessage(messageKill, finalKill);
                if (clientController.getView() instanceof GUIView){
                    clientController.sendMessage(messageVK.getParmS());
                }
                else clientController.sendMessage(messageKill);

                return (new SpawnLocationState(clientController)).initState();
//                return new WaitingResponseState(clientController,new SpawnLocationState(clientController));

                default:

                    return nextState(false,true);

        }
    }
    @Override
    public ControllerState nextState(boolean error, boolean playing) {
        if (error) {
            return previousState.initState();
        } else if (playing) {
            clientController.nextView(wpc);
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