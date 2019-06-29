package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;


import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

//first player select a map
public class SelectMapState extends ControllerState {

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

        //几乎没用 =@.@=
        if (serverMessage.getMessage().equalsIgnoreCase("CHOOSEMAP")) {
            String finalMap = String.valueOf(serverMessage.getParm());
            String messageMap = "(select map state) the map of this match: " + finalMap;
            clientController.sendMessage(messageMap);
            return nextState(false,true);
        }
        return this;
    }

}