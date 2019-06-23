package it.polimi.ingsw.se2019.Adrenaline.client.controller;


import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;

import java.util.List;

//first player select a map
public class SelectMapState extends ControllerState{

    private int selectedMap;

    public SelectMapState(ClientController clientController) {

        super(clientController, "Please select a map: 1.A   2.B   3.C   4.D");
    }

    @Override
    public ControllerState update(String message) {

        switch (message) {
            case "1":
            case "A":
                selectedMap = 1;break;
            case "2":
            case "B":
                selectedMap = 2;break;
            case "3":
            case "C":
                selectedMap = 3;break;
            case "4":
            case "D":
                selectedMap = 4;break;
            default:
                clientController.reportError("Not a valid map!");
                return this;
        }
        ClientMessage clientMessage = new ClientMessage("CHOOSEMAP", selectedMap);
        clientController.sendToServer(clientMessage);
        return new WaitingResponseState(clientController,new SelectKillState(clientController));
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.getMessage().equalsIgnoreCase("CHOOSEMAP")) {
            List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
            if (!statusUpdates.isEmpty()) {
//                WindowPatternUpdate windowPatternUpdate = (WindowPatternUpdate) statusUpdates.get(0);
//                windowPatternCards.addAll(windowPatternUpdate.getWindowPatternCards());
                for (StatusUpdate statusUpdate : statusUpdates) {
                    statusUpdate.updateStatus(clientController.getModel());
                }
                clientController.getModel().pingUpdate(serverMessage.getMessage());
                clientController.sendMessage("Choose your map:");
            } else {
                clientController.reportError("Can't obtain the window pattern cards!");
            }
        } else {
            clientController.reportError("There has been a problem.");
        }
        return this;
    }

}