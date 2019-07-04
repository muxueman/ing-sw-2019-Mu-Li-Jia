package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.util.ArrayList;
import java.util.List;

public class WalkState extends ControllerState {
    private ArrayList<String> previousActions;
    private int currentCell;

    public WalkState(ClientController controller, ArrayList<String> previousActions) {
        super(controller, "please select walk direction: 0.up 1.right 2.down 3.left");
        this.previousActions = previousActions;
        this.previousActions.add("walk");
        this.currentCell = clientController.getModel().getBoardStatus().getPositions().get(clientController.getPlayerID());
        System.out.println("cell id" + currentCell);
        //this.currentCell = model>>>>>
    }

    //if it's ammotile, then 0; if it's an generation points with three weapon cards, then 1,2,3
    @Override
    public ControllerState update(String message){
        ClientMessage clientMessage;
        System.out.println(message);
        switch (message) {
            case ("0"):
            case ("up"):
                clientMessage = new ClientMessage("WALK", 0);
                clientController.sendToServer(clientMessage);
                return this;
            case ("1"):
            case ("right"):
                clientMessage = new ClientMessage("WALK", 1);
                clientController.sendToServer(clientMessage);
                return this;
            case ("2"):
            case ("down"):
                clientMessage = new ClientMessage("WALK",2);
                clientController.sendToServer(clientMessage);
                return this;
            case ("3"):
            case ("left"):
                clientMessage = new ClientMessage("WALK", 3);
                clientController.sendToServer(clientMessage);
                return this;
            default:
                //clientMessage = null;
                clientController.reportError("not valid direction");
                return this;
        }

//        return new WaitingResponseState()
//        return new ActionSelectState(clientController,previousActions).initState();

    }
    @Override
    public ControllerState updateStatus(ServerMessage serverMessage){
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        } else {
            if (serverMessage.isPlaying()) {
                if (serverMessage.getMessage().equalsIgnoreCase("WALK")) {
                    List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
                    if (!statusUpdates.isEmpty()) {
                        for (StatusUpdate statusUpdate : statusUpdates) {
                            statusUpdate.updateStatus(clientController.getModel());
                            new ShowTotal(clientController.getModel().getBoardStatus(), clientController);
                        }
                        clientController.getModel().pingUpdate(serverMessage.getMessage());
                        return new ActionSelectState(clientController, previousActions).initState();
                    } else {
                        clientController.reportError("no response from server!");
                    }
                }
            }
        }
        return this;
    }
}
