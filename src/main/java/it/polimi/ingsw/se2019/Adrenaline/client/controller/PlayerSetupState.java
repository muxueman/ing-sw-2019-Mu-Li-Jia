package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;

public class PlayerSetupState extends ControllerState{

    private String username;

    public PlayerSetupState(ClientController clientController){
        super(clientController,"insert your user name:");
        username = null;
    }

    @Override
    public ControllerState update(String message){
        username = message;
        ClientMessage clientMessage = new ClientMessage(username, 0);
        clientController.sendToServer(clientMessage);
        return new WaitingResponseState(clientController, new );
    }
}
