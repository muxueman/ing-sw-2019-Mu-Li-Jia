package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ShootState extends ControllerState {

    private ServerController serverController;
    private MatchController matchController;
    private ArrayList<Player> availbleShootPlayers;
    private ArrayList<String> previousActions;
    private int messageUpadateTimes;

    public ShootState(ClientController controller, ArrayList<String> previousActions){
        super(controller, "please select your weapon cards: ");
        this.serverController = serverController;
        this.previousActions = previousActions;
        this.matchController = serverController.getMatch();
        messageUpadateTimes = 0;
        // model.get availble weapon cards
        //message += >>>
        this.initState();
    }
    @Override
    public ControllerState update(String message){
        switch (messageUpadateTimes){
            case 0: ClientMessage clientMessage = new ClientMessage("SHOOT",message);
                clientController.sendToServer(clientMessage);
                messageUpadateTimes ++;
                return this;
            case 1: ClientMessage targetbasic = new ClientMessage("TARGETBASIC",message);
                clientController.sendToServer(targetbasic);
                messageUpadateTimes ++;
                return this;
        }
        return this; //
    }
    @Override
    public ControllerState updateStatus(ServerMessage serverMessage){
        //show message to cli
        return this;
    }

//    message("    ",),
}
