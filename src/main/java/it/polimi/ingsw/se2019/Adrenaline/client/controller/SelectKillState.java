package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

public class SelectKillState extends ControllerState {

    private int numKillShoot;

    public SelectKillState(ClientController clientController){
        //debug 有两次输出message
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

    public ControllerState updateStatus(ServerMessage serverMessage) {

        if (serverMessage.getMessage().equalsIgnoreCase("CHOOSEMAP")) {
            String finalMap = String.valueOf(serverMessage.getParm());
            String messageMap = "(select map state) the map of this match: " + finalMap;
            clientController.sendMessage(messageMap);
            return nextState(false, true);
        }
        return this;
    }
}
