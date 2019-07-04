package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowWeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowWeaponCardReload;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReloadState extends ControllerState {

    private ArrayList<String> previousActions;
    private int currentCell;
    private ArrayList<WeaponCard> weaponCardReloads;

    public ReloadState(ClientController controller, ArrayList<String> previousActions) {

        super(controller, "please enter the weapon card you want to reload: (0 for not reload)");
        this.previousActions = previousActions;
        this.previousActions.add("reload");
        new ShowWeaponCard(clientController.getModel().getBoardStatus().getPlayer(clientController.getPlayerID()).getWeaponsOwned());
        weaponCardReloads = new ShowWeaponCardReload(clientController.getModel().getBoardStatus().getPlayer(clientController.getPlayerID()).getWeaponsOwned()).getWeaponCards();
        this.currentCell = clientController.getModel().getBoardStatus().getPositions().get(clientController.getPlayerID());
        System.out.println("your current cell id" + currentCell);
    }

    @Override
    public ControllerState update(String message) {
        System.out.println(message);
            for (WeaponCard w :weaponCardReloads){
                if (w.getCardName().equalsIgnoreCase(message)){
                    ClientMessage cMessage = new ClientMessage("RELOAD", w.getCardName());
                    clientController.sendToServer(cMessage);
                    return this;
                }
            }
            if (message.equals("0")){
                //ClientMessage cMessage = new ClientMessage("ENDTURN",0);
                //clientController.sendToServer(cMessage);
                return new ActionSelectState(clientController, previousActions,true).initState();
            }else{
                clientController.reportError("not a valid reload action!");
                return this.initState();
            }
    }
    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isError()){
            clientController.sendMessage(serverMessage.getMessage());
            clientController.sendMessage("end of reload");
            return new ActionSelectState(clientController,previousActions,true).initState();
        }
        if (serverMessage.isPlaying() && serverMessage.getMessage().equalsIgnoreCase("RELOAD")) {
            List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
            if (!statusUpdates.isEmpty()) {
                for (StatusUpdate statusUpdate : statusUpdates) {
                    statusUpdate.updateStatus(clientController.getModel());
                }
                new ShowTotal(clientController.getModel().getBoardStatus(), clientController);
                clientController.getModel().pingUpdate(serverMessage.getMessage());
                return new ActionSelectState(clientController, previousActions,true).initState();
            }

        }
        clientController.reportError("cannot parse reload message from server!");
        return this;
    }
}
