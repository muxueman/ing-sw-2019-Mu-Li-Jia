package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;

public class SelectKillState extends ControllerState {

    private int numKillShoot;

    public SelectKillState(ClientController clientController){
        super(clientController, "Please insert the number of killShoot:\n");
        this.numKillShoot = 5;
    }
    @Override
    public ControllerState update(String message) {
        numKillShoot = Integer.valueOf(message);
        ClientMessage clientMessage = new ClientMessage("numKillShoot", numKillShoot);
        clientController.sendToServer(clientMessage);
        return null;
        //return this;
    }
}
