package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;

import java.util.ArrayList;
import java.util.List;

public class SpawnLocationState extends ControllerState {

    private ArrayList<PowerupCard> powerupCards;


    public SpawnLocationState(ClientController controller) {
        super(controller, "Here are the set up power up cards: (please select and discard this one as generate color):");
        powerupCards = clientController.getModel().getBoardStatus().getPlayer(clientController.getPlayerID()).getPowerupsOwned();
        for (PowerupCard p : powerupCards){
            clientController.sendMessage(p.getCardName());
        }
    }


    @Override
    public ControllerState update(String message) {
        int intMessage;
        try {
            intMessage = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            intMessage = -1;
        }
        if (powerupCards.isEmpty()) {
            if (intMessage > 0 && intMessage <= powerupCards.size()) {
                ClientMessage clientMessage = new ClientMessage("SPAWNLOCATION", intMessage);
                clientController.sendToServer(clientMessage);
                return new WaitingResponseState(clientController, new NonPlayingState(clientController));
            }
            clientController.reportError("Not a valid choice!");
        } else {
            clientController.reportError("Wait the powerup cards!");
        }
        return initState();
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {

        return this;
    }


}
