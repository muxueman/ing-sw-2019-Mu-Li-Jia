package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;

public class SelectKillState extends ControllerState {

    private int numKillShoot;

    public SelectKillState(ClientController clientController){
        super(clientController, "Please insert the number of killShoot: (suggested 5~8)\n");
        this.numKillShoot = 5;
    }
    @Override
    public ControllerState update(String message) {
        numKillShoot = Integer.valueOf(message);
        if (numKillShoot >= 5 && numKillShoot <= 8) {
            ClientMessage clientMessage = new ClientMessage("NUMKILLSHOOT", numKillShoot);
            clientController.sendToServer(clientMessage);
            return new WaitingResponseState(clientController, new NonPlayingState(clientController));
        }
        else {
            clientController.reportError("Not a valid number!");
            return this;
        }
    }
}
