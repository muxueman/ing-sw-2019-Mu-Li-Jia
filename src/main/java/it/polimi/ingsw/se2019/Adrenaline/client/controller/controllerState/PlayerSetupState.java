package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ServerMessage;

public class PlayerSetupState extends ControllerState {

    private String username;

    public PlayerSetupState(ClientController clientController){
        super(clientController,"insert your user name:");
        username = null;
    }

    @Override
    public ControllerState update(String message){
        username = message;
        ClientMessage clientMessage = new ClientMessage(username, 0);
        clientController.sendToServer(clientMessage);
        return new WaitingResponseState(clientController, new SelectMapState(clientController));
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        }
        return nextState(serverMessage.isError(), serverMessage.isPlaying());
    }
}
