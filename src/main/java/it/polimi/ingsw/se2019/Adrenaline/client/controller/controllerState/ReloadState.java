package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

import java.util.ArrayList;

public class ReloadState extends ControllerState {
    private ArrayList<String> previousActions;
    private int currentCell;

    public ReloadState(ClientController controller) {
        super(controller, "reloading...");
        this.currentCell = clientController.getModel().getBoardStatus().getPositions().get(clientController.getPlayerID());
        System.out.println("cell id" + currentCell);
        //this.currentCell = model>>>>>
    }

    @Override
    public ControllerState update(String message) {
            return this;
    }
    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isPlaying() == true) {
            System.out.println("is playing == true");
            return new ActionSelectState(clientController, new ArrayList<>()).initState();
        }else return this;
    }

}
