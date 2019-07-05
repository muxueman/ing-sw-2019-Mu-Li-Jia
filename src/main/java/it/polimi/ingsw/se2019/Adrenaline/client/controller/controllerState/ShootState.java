package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowWeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ServerMessage;

import java.util.ArrayList;

public class ShootState extends ControllerState {

//    private MatchController matchController;
    private ArrayList<String> previousActions;
    private int messageUpadateTimes;
    private boolean isReload;

    public ShootState(ClientController controller, ArrayList<String> previousActions, boolean isReload){
        super(controller, "please select your weapon cards: ");
        this.previousActions = previousActions;
        this.isReload =isReload;
        previousActions.add("shoot");
        messageUpadateTimes = 0;
        new ShowWeaponCard(controller.getModel().getBoardStatus().getPlayer(controller.getPlayerID()).getWeaponsOwned());

    }
    @Override
    public ControllerState update(String message){
        switch (messageUpadateTimes){
            case 0:
                ClientMessage clientMessage = new ClientMessage("SHOOT", message);
                clientController.sendToServer(clientMessage);
                messageUpadateTimes ++;
                return this;
            case 1:
                ClientMessage targetbasic = new ClientMessage("TARGET BASIC",message);
                clientController.sendToServer(targetbasic);
                messageUpadateTimes ++;
                return this;
            case 2:
                ClientMessage targetMoveDirect = new ClientMessage("TARGET MOVE",message);
                clientController.sendToServer(targetMoveDirect);
                messageUpadateTimes ++;
        }
        return this;
    }
    @Override
    public ControllerState updateStatus(ServerMessage serverMessage){
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        } else {
            if (serverMessage.isPlaying()) {
                switch (serverMessage.getMessage()) {
                    case "TARGET BASIC":
                        clientController.sendMessage(serverMessage.getSubParameter());
                        if(serverMessage.getParm() == 0) return new ActionSelectState(clientController, previousActions,isReload).initState();
                        else return this;
                    case "TARGET MOVE":
                        clientController.sendMessage(serverMessage.getSubParameter());
                        return new ActionSelectState(clientController, previousActions,isReload).initState();
                    default:
                        clientController.reportError("cannot parse shoot message from server");
                        return this;
                }
            }
            else {
                clientController.reportError("no response from server!");
            }
        }
        return this;
    }
}
