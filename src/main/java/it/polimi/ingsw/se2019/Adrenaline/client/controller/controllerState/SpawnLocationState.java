package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowPowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowWeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;

import java.util.ArrayList;
import java.util.List;

public class SpawnLocationState extends ControllerState {

    private ArrayList<PowerupCard> powerupCards;


    public SpawnLocationState(ClientController controller) {
        super(controller, "Here are the set up power up cards: (please select and discard this one as generate color):");
        powerupCards = clientController.getModel().getBoardStatus().getPlayer(clientController.getPlayerID()).getPowerupsOwned();
        new ShowPowerupCard(powerupCards);

    }

    @Override
    public ControllerState update(String message) {

        for (PowerupCard p: powerupCards) {
            if (p.getCardName().equalsIgnoreCase(message)) {
                ClientMessage clientMessage = new ClientMessage("SPAWNLOCATION", p.getCardName());
                clientController.sendToServer(clientMessage);
//                switch (p.getColor()){
//                    case "red": clientController.getModel().setPosition(clientController.getPlayerID(),5);break;
//                    case "blue": clientController.getModel().setPosition(clientController.getPlayerID(), 3);break;
//                    case "yellow": clientController.getModel().setPosition(clientController.getPlayerID(), 3);break;
//                }
                return new WaitingResponseState(clientController, new NonPlayingState(clientController));
            }
        }
        clientController.reportError("Wait the powerup cards!");
        return initState();
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {

        return this;
    }


}
