package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.client.view.GUI.GUIView;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ViewMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

import java.util.List;
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
        if (serverMessage.isPlaying() == false){
            switch (serverMessage.getMessage()) {
                case ("CONNECT"):
                    wpc = false;
                    nextState = new NonPlayingState(clientController);
                    Logger.getGlobal().info("next state: nonplaying state");
                    return super.updateStatus(serverMessage);
                case ("FIGURE"):
                    String figureColor = String.valueOf(serverMessage.getSubParameter());
                    String messageColor = "your color of figure " + figureColor;
                    clientController.sendMessage(messageColor);
                    return this;
                case ("ID"):
                    String ID = String.valueOf(serverMessage.getSubParameter());
                    String messageID = "your ID " + ID;
                    clientController.setPlayerID(ID);
                    clientController.sendMessage(messageID);
                    return this;
                case ("CHOOSEMAP"):
                    String finalMap = String.valueOf(serverMessage.getParm());
                    String messageMap = "the map of this match: " + finalMap;
                    ViewMessage messageVM = new ViewMessage(messageMap, finalMap);
                    if (clientController.getView() instanceof GUIView) {
                        clientController.sendMessage(messageVM.getParmS());
                    } else clientController.sendMessage(messageMap);
                    return nextState(false, true);
                case ("CHOOSEKILL"):
                    String finalKill = String.valueOf(serverMessage.getParm());
                    String messageKill = "the kill number of this match: " + finalKill;
                    ViewMessage messageVK = new ViewMessage(messageKill, finalKill);
                    if (clientController.getView() instanceof GUIView) {
                        clientController.sendMessage(messageVK.getParmS());
                    } else clientController.sendMessage(messageKill);
                    return this;
                case ("Already start the match!"):
                    List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
                    if (!statusUpdates.isEmpty()) {
                        for (StatusUpdate statusUpdate : statusUpdates) {
                            statusUpdate.updateStatus(clientController.getModel());
                        }
                        clientController.getModel().pingUpdate(serverMessage.getMessage());
                    } else {
                        clientController.reportError("no response from server!");
                    }
                    String viewMessage = "";
                    for (String pID : clientController.getModel().getBoardStatus().getPlayers().keySet()) {
                        viewMessage += clientController.getModel().getBoardStatus().getUsernames().get(pID) + ": cell "
                                + clientController.getModel().getBoardStatus().getPositions().get(pID) + "  ";
                    }
                    //new ShowTotal(clientController.getModel().getBoardStatus(),clientController);
                    return this;
                case ("OPPONENTS"):
                    new ShowTotal(clientController.getModel().getBoardStatus(),clientController);
                    clientController.sendMessage(serverMessage.getSubParameter());
                    return new SpawnLocationState(clientController).initState();
                case ("SpawnLocation"):
                    List<StatusUpdate> statusUpdatesS = serverMessage.getStatusUpdates();
                    if (!statusUpdatesS.isEmpty()) {
                        for (StatusUpdate statusUpdate : statusUpdatesS) {
                            statusUpdate.updateStatus(clientController.getModel());
                        }
                    }
//                    String viewMessage = "";
//                    for (String pID : clientController.getModel().getBoardStatus().getPlayers().keySet()){
//                        viewMessage += clientController.getModel().getBoardStatus().getUsernames().get(pID) + ": cell "
//                                 + clientController.getModel().getBoardStatus().getPositions().get(pID) + "  ";
//                    }
//                    clientController.sendMessage(viewMessage);
                    new ShowTotal(clientController.getModel().getBoardStatus(),clientController);
                    return new NonPlayingState(clientController);

                default:
                 //   return super.updateStatus(serverMessage);
                    return nextState(false,true);
            }

        }
        if (serverMessage.isPlaying() == true){
            System.out.println("is playing == true");
            return new PlayingState(clientController,clientController.getActionMode());

        }

        return this;
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