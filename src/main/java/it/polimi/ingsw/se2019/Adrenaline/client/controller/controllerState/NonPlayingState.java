package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;

import java.util.ArrayList;
import java.util.List;

public class NonPlayingState extends ControllerState {

    //constructor,"You passed the turn to the next player."
    public NonPlayingState(ClientController clientController){
        super(clientController, "non_playing state");
        System.out.println("non playing State");
    }

    @Override
    public ControllerState initState() {
        //clientController.getView().play(false);
        clientController.setPlaying(false);
        return super.initState();
    }
    @Override
    public ControllerState update(String message) {
        clientController.reportError("It's not your turn!");
        return this;
    }
    @Override
    public ControllerState nextState(boolean error, boolean playing) {
        if (playing) {
            //clientController.getView().launchTimer();
            return new ActionSelectState(clientController, new ArrayList<>() ).initState();
        }
        return this;
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isPlaying()) {
            System.out.println("is playing == true");
            clientController.sendMessage("now it's your turn!");
            return new ActionSelectState(clientController, new ArrayList<>()).initState();
        }else {
            switch (serverMessage.getMessage()) {
                case "OTHER":
                    clientController.sendMessage(serverMessage.getSubParameter());
                    return this;
                case "UPDATE":
                    List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
                    if (!statusUpdates.isEmpty()) {
                        for (StatusUpdate statusUpdate : statusUpdates) {
                            statusUpdate.updateStatus(clientController.getModel());
                            new ShowTotal(clientController.getModel().getBoardStatus(), clientController);
                        }
                        clientController.getModel().pingUpdate(serverMessage.getMessage());
                    }
                    return this;
                    default:
                        clientController.reportError("fail to analysis the message from server!");
                        return this;
//            serverMessage.getMessage().equalsIgnoreCase("OTHER");

            }
        }
    }

}
