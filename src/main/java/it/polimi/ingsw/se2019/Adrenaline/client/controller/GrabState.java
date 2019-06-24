package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ChooseKillState;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrabState extends ControllerState{

    private ServerController serverController;
    private MatchController matchController;
    private ArrayList<String> previousActions;
    private int currentCell;

    public GrabState(ClientController controller,  ArrayList<String> previousActions) {
        super(controller, "please select the cards...");
        this.serverController = serverController;
        this.matchController = serverController.getMatch();
        this.previousActions = previousActions;
        this.previousActions.add("grab");
        //this.currentCell = model>>>>>
    }

    //if it's ammotile, then 0; if it's an generation points with three weapon cards, then 1,2,3
    @Override
    public ControllerState update(String message){
        ClientMessage clientMessage;
        switch (message) {
            case ("0"):
                clientMessage = new ClientMessage("GRABAMMOTILE", currentCell);
                break;
            case ("1"):
            case ("2"):
            case ("3"):
                Integer intMessage = Integer.valueOf(message);
                clientMessage = new ClientMessage("GRABWEAPON", currentCell, intMessage);
                break;
                default:
                    clientController.reportError("not valid card!");
                    return this;
        }
        clientController.sendToServer(clientMessage);
        return new ActionSelectState(clientController,previousActions);
    }


    //updateStatus:
}
