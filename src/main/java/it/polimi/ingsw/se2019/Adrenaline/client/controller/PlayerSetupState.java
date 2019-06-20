package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

public class PlayerSetupState extends ControllerState{

    private String username;

    public PlayerSetupState(ClientController clientController){
        super(clientController,"insert your user name:");
        username = null;
    }

    @Override
    public ControllerState update(String message){
        username = message;
        System.out.println("receive username!");
        ClientMessage clientMessage = new ClientMessage(username, 0);
        clientController.sendToServer(clientMessage);
        return new WaitingResponseState(clientController, new SelectMapState(clientController, 3));
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        }
        return nextState(serverMessage.isError(), serverMessage.isPlaying());
    }
}
