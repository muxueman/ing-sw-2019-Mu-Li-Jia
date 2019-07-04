package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowWeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowWeaponCardReload;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import java.util.ArrayList;
import java.util.List;

public class ShootState extends ControllerState {

//    private MatchController matchController;
    private ArrayList<String> previousActions;
    private int messageUpadateTimes;
    private ArrayList<WeaponCard> availableWeapon;

    public ShootState(ClientController controller, ArrayList<String> previousActions){
        super(controller, "please select your weapon cards: ");
        this.previousActions = previousActions;
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
            case 1: System.out.println("message");
                ClientMessage targetbasic = new ClientMessage("TARGETBASIC",message);
                clientController.sendToServer(targetbasic);
                messageUpadateTimes ++;
                return this;
        }
        return this; //
    }
    @Override
    public ControllerState updateStatus(ServerMessage serverMessage){
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        } else {
            if (serverMessage.isPlaying()) {
                switch (serverMessage.getMessage()) {
                    case "TARGET":
                        clientController.sendMessage(serverMessage.getSubParameter());
                        return this;
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

//    message("    ",),
}
