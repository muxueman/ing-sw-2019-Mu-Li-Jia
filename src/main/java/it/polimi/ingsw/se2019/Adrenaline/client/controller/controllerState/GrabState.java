package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.CommandLineTable;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.ShowTotal;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import java.util.ArrayList;
import java.util.List;

public class GrabState extends ControllerState {

    private ArrayList<String> previousActions;
    private boolean isReload;

    public GrabState(ClientController controller, ArrayList<String> previousActions,boolean isReload) {
        super(controller, "please select the cards...(card name or 0 for ammoile, 1, 2, 3 for each weapon cards)\n");
        this.previousActions = previousActions;
        this.previousActions.add("grab");
        this.isReload = isReload;
        int currentCell = clientController.getModel().getBoardStatus().getPositions().get(clientController.getPlayerID());
        System.out.println("cell id" + currentCell);
        if (currentCell == 5 || currentCell == 3 ||currentCell ==12){
            ArrayList<WeaponCard> weaponCards = clientController.getModel().getBoardStatus().getWeaponsInCell().get(currentCell);
            CommandLineTable st = new CommandLineTable("\u001b[1;32m","\u001b[1;31m");
            st.setShowVerticalLines(true);
            int length = weaponCards.size();
            switch (length){
                case 1: st.addRow("Weapon Cards",weaponCards.get(0).getCardName());st.print();break;
                case 2: st.addRow("Weapon Cards",weaponCards.get(0).getCardName(),weaponCards.get(1).getCardName());st.print();break;
                case 3: st.addRow("Weapon Cards",weaponCards.get(0).getCardName(),weaponCards.get(1).getCardName(),weaponCards.get(2).getCardName());st.print();break;
                case 0:
                default:
                    clientController.sendMessage("no weapon cards for you to grab");break;
            }
        }else{
            AmmotileCard ammotileCard = clientController.getModel().getBoardStatus().getAmmotilesInCell().get(currentCell);
            clientController.sendMessage(ammotileCard.getContent());
        }

    }

    //if it's ammotile, then 0; if it's an generation points with three weapon cards, then 1,2,3
    @Override
    public ControllerState update(String message){
        System.out.println(message);
        switch (message) {
            case ("0"):
                ClientMessage clientMessageA = new ClientMessage("GRABAMMOTILE",0);
                clientController.sendToServer(clientMessageA);
                break;
            case ("1"):
            case ("2"):
            case ("3"):
                System.out.println("update message weapons");
                ClientMessage clientMessageB = new ClientMessage("GRABWEAPON", Integer.valueOf(message));
                clientController.sendToServer(clientMessageB); // not success
                break;
            default:
                clientController.reportError("not valid card!");
                return this.initState();
        }
        return this;
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage){
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        } else {
            if (serverMessage.isPlaying() && serverMessage.getMessage().equalsIgnoreCase("GRAB")) {
                List<StatusUpdate> statusUpdates = serverMessage.getStatusUpdates();
                if (!statusUpdates.isEmpty()) {
                    for (StatusUpdate statusUpdate : statusUpdates) {
                        statusUpdate.updateStatus(clientController.getModel());
                    }
                    new ShowTotal(clientController.getModel().getBoardStatus(), clientController);
                    clientController.getModel().pingUpdate(serverMessage.getMessage());
                    return new ActionSelectState(clientController, previousActions,isReload).initState();
                } else {
                    clientController.reportError("no response from server!");
                }
            }
        }
        return this;
    }
}
