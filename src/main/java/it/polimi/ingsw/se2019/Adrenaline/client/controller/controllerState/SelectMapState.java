package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;


import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

//first player select a map
public class SelectMapState extends ControllerState {

    private int selectedMap;

    public SelectMapState(ClientController clientController) {

        super(clientController, "Please select a map: 1.A   2.B   3.C   4.D\n");
        message += ("A:\n" + "\u001b[1;46m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;48m   "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;41m 6 " + "\u001b[1;41m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;48m   "
                + "\u001b[1;47m 10" + "\u001b[1;47m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n" +
                "B:\n" + "\u001b[1;41m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;48m   "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;45m 6 " + "\u001b[1;45m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;47m 9 "
                + "\u001b[1;47m 10" + "\u001b[1;47m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n" +
                "C:\n" + "\u001b[1;46m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;42m 4 "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;41m 6 " + "\u001b[1;43m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;48m   "
                + "\u001b[1;47m 10" + "\u001b[1;43m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n" +
                "D:\n" + "\u001b[1;41m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;42m 4 "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;45m 6 " + "\u001b[1;43m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;47m 9 "
                + "\u001b[1;47m 10" + "\u001b[1;43m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n");
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