package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;

public class SelectKillState extends ControllerState {

    private int numKillShoot;

    //debug 有两次输出message
    public SelectKillState(ClientController clientController){
        super(clientController, "Please insert the number of killShoot: (suggested 5~8)");
        this.numKillShoot = 5;
    }
    @Override
    public ControllerState update(String message) {
        numKillShoot = Integer.valueOf(message);
        if (numKillShoot >= 5 && numKillShoot <= 8) {
            ClientMessage clientMessage = new ClientMessage("CHOOSEKILL", numKillShoot);
            clientController.sendToServer(clientMessage);
            return new WaitingResponseState(clientController, new NonPlayingState(clientController));
        }
        else {
            clientController.reportError("Not a valid number!");
            return this;
        }
    }
}
