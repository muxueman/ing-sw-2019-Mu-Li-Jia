package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;

//first player select a map
public class SelectMapState extends ControllerState {

    public SelectMapState(ClientController clientController) {

        super(clientController, "Please select a map: 1.A   2.B   3.C   4.D\n");
        new ShowTotal().printMap(1);
        new ShowTotal().printMap(2);
        new ShowTotal().printMap(3);
        new ShowTotal().printMap(4);
    }

    @Override
    public ControllerState update(String message) {
        int selectedMap;
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
}