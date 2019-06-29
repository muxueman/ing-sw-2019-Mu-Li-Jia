package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;

public class EndGameState extends ControllerState {

    public EndGameState(ClientController controller) {
        super(controller, "The match is over");
    }

    @Override
    public ControllerState update(String message) {
        clientController.reportError("The match is over");
        return this;

    }
}
