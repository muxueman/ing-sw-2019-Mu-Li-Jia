package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.util.ArrayList;

public class WalkState extends ControllerState{
    private ServerController serverController;
    private MatchController matchController;
    private ArrayList<String> previousActions;
    private int currentCell;

    public WalkState(ClientController controller,  ArrayList<String> previousActions) {
        super(controller, "please select walk direction: 1.up 2.right 3.down 4.left");
        this.serverController = serverController;
        this.matchController = serverController.getMatch();
        this.previousActions = previousActions;
        this.previousActions.add("walk");
        //this.currentCell = model>>>>>
    }

    //if it's ammotile, then 0; if it's an generation points with three weapon cards, then 1,2,3
    @Override
    public ControllerState update(String message){
        ClientMessage clientMessage;
        switch (message) {
            case ("1"):
            case ("up"):
                clientMessage = new ClientMessage("WALK", currentCell,1);
                break;
            case ("2"):
            case ("right"):
                clientMessage = new ClientMessage("WALK", currentCell,2);
                break;
            case ("3"):
            case ("down"):
                clientMessage = new ClientMessage("WALK", currentCell,3);
                break;
            case ("4"):
            case ("left"):
                clientMessage = new ClientMessage("WALK", currentCell,4);
                break;
            default:
                clientController.reportError("not valid direction");
                return this;
        }
        clientController.sendToServer(clientMessage);
        return new ActionSelectState(clientController,previousActions);
    }
}
