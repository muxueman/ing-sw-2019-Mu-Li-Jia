package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

import java.util.ArrayList;

public class PlayingState extends ControllerState {

    //private int selectedAction;
    private ClientMessage clientMessage;
    private int actionMode;

    public PlayingState(ClientController clientController, int actionMode) {
        super(clientController, "");
        this.actionMode = actionMode;
        clientMessage = new ClientMessage("ACTION", actionMode);
        System.out.println("playing state!");
    }

    @Override
    public ControllerState initState() {
        clientController.setPlaying(true);
        return super.initState();
    }

    @Override
    public ControllerState update(String message) {
        //
        return initState();
    }

    //@Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        return null;
    }

}

