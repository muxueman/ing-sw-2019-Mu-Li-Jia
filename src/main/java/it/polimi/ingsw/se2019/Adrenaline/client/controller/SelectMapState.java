package it.polimi.ingsw.se2019.Adrenaline.client.controller;


import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;

//first player select a map
public class SelectMapState extends ControllerState{

    private int selectedMap;

    public SelectMapState(ClientController clientController, int numberPlayers) {

        super(clientController, "Please select a map:\n");
        switch (numberPlayers) {
            case 3:
                message += "1.A   2.B   3.C";
                break;
            case 4:
                message += "1.A   2.B   3.C   4.D";
                break;
            case 5:
                message += "2.B   3.C   4.D";
                break;
        }
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

}
