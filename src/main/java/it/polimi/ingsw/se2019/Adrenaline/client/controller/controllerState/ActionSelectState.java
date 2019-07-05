package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.ServerMessage;
import java.util.ArrayList;

public class ActionSelectState extends ControllerState {

    private int actionMode;
    private String additionalMessage;
    private ArrayList<String> previousActions;
    private String actionSelected;
    //below is only to check whether client choose the right action (CLI exclusive)
    private ArrayList<String> selectedActions;
    private boolean isReload;

    public ActionSelectState(ClientController clientController, ArrayList<String> previousActions, boolean isReload) {


        super(clientController, "Please select an Action:\n");
        actionMode = clientController.getModel().getBoardStatus().getPlayer(clientController.getPlayerID()).getActionMode();
        this.isReload = isReload;
        this.previousActions = previousActions;
        this.selectedActions = new ArrayList<>();
        this.actionMode = this.clientController.getActionMode();
        additionalMessage =  addChoice();
        message += additionalMessage;
        for (String m: additionalMessage.split(",")){
            selectedActions.add(m);
        }
    }


    private String addChoice() {
        int num = 0;
        String actions3 = "run,grab,end round";
        String actions4 = "run,grab,shoot,end round";
        String actions5 = "run,grab,shoot,reload,end round";
        System.out.println(previousActions);
        switch (actionMode) {
            case 0:
            case 1:
            case 2:
                if (previousActions.isEmpty()) {
                    return actions4;
                } else {
                    switch (previousActions.get(0)) {
                        case "grab":
                        case "shoot":
                        case "end round":
                            num += 1;
                            break;
                        case "run":
                            num += 1;
                            if (actionMode == 2) {
                                if (previousActions.size() == 1) {
                                    return actions4;
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "shoot":
                                        case "end round":
                                            num += 1;
                                            break;
                                        case "run":
                                            num += 1;
                                            if (previousActions.size() == 2) {
                                                return actions3;
                                            }
                                            break;
                                    }
                                }
                            } else if (previousActions.size() == 1) {
                                return actions3;
                            } else {
                                switch (previousActions.get(1)) {
                                    case "grab":
                                    case "end round":
                                        num += 1;
                                        break;
                                    case "run":
                                        num += 1;
                                        if (previousActions.size() == 2) {
                                            if (actionMode == 1) {
                                                return actions3;
                                            }
                                            if (actionMode == 0) {
                                                return "run,grab";
                                            }
                                        }
                                        break;
                                }
                            }
                    }
                }
                break;
            case 3:
            case 4:
                if (previousActions.isEmpty()) {
                    return actions5;
                } else {
                    switch (previousActions.get(0)) {
                        case "grab":
                        case "shoot":
                        case "end round":
                            num += 1;
                            break;
                        case "reload":
                            num += 1;
                            if (previousActions.size() == 1) {
                                return actions3;
                            }
                            break;
                        case "run":
                            num += 1;
                            if (previousActions.size() == 1) {
                                return actions5;
                            } else {
                                switch (previousActions.get(1)) {
                                    case "grab":
                                    case "shoot":
                                    case "end round":
                                        num += 1;
                                        break;
                                    case "reload":
                                        num += 1;
                                        if (previousActions.size() == 2) {
                                            return "shoot,end round";
                                        }
                                        break;
                                    case "run":
                                        num += 1;
                                        if (actionMode == 3) {
                                            if (previousActions.size() == 2) {
                                                return actions3;
                                            } else {
                                                switch (previousActions.get(2)) {
                                                    case "grab":
                                                    case "end round":
                                                        num += 1;
                                                        break;
                                                    case "run":
                                                        num += 1;
                                                        if (previousActions.size() == 3) {
                                                            return "run,end round";
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                        if (actionMode == 4) {
                                            if (previousActions.size() == 2) {
                                                return actions5;
                                            } else {
                                                switch (previousActions.get(2)) {
                                                    case "grab":
                                                    case "shoot":
                                                    case "end round":
                                                        num += 1;
                                                        break;
                                                    case "reload":
                                                        num += 1;
                                                        if (previousActions.size() == 3) {
                                                            return "shoot,end round";
                                                        }
                                                        break;
                                                    case "run":
                                                        num += 1;
                                                        if (previousActions.size() == 3) {
                                                            return "grab,end round";
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                        break;
                                }
                                break;
                            }
                    }
                }
                break;
        }

        if (actionMode != 4){
            switch (actionMode) {
                case 0:
                case 1:
                case 2:
                    if (previousActions.size() == num) {
                        return actions4;
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "run":
                                if (actionMode == 2) {
                                    if (previousActions.size() == num + 1) {
                                        return actions4;
                                    } else {
                                        switch (previousActions.get(1)) {
                                            case "grab":
                                            case "shoot":
                                            case "end round":
                                                break;
                                            case "run":
                                                if (previousActions.size() == num + 2) {
                                                    return "run,grab,end round";
                                                }
                                                break;
                                        }
                                    }
                                } else if (previousActions.size() == num + 1) {
                                    return "run,grab,end round";
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "end round":
                                            break;
                                        case "run":
                                            if (previousActions.size() == num + 2) {
                                                if (actionMode == 1) {
                                                    return "run,grab,end round";
                                                }
                                                if (actionMode == 0) {
                                                    return "run,grab";
                                                }
                                            }
                                            break;
                                    }
                                }
                        }
                    }
                    break;
                case 3:
                    if (previousActions.size() == num) {
                        return "run,grab,shoot,reload,end round";
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "reload":
                                if (previousActions.size() == 1 + num ) {
                                    return "shoot,end round";
                                }
                                break;
                            case "run":
                                if (previousActions.size() == num + 1) {
                                    return "run,grab,shoot,reload,end round";
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "shoot":
                                        case "end round":
                                            break;
                                        case "reload":
                                            if (previousActions.size() == num + 2) {
                                                return "shoot,end round";
                                            }
                                            break;
                                        case "run":
                                            if (actionMode == 3) {
                                                if (previousActions.size() == num + 2) {
                                                    return "run,grab,end round";
                                                } else {
                                                    switch (previousActions.get(2)) {
                                                        case "grab":
                                                        case "end round":
                                                            break;
                                                        case "run":
                                                            if (previousActions.size() == num + 3) {
                                                                return "run,end round";
                                                            }
                                                            break;
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                    break;
                                }
                        }
                    }
                    break;
            }
        }
        if (!isReload){
            return "reload";
        }
        else {
            clientController.sendMessage("end of your turn");
            return "end turn";
        }
    }
    public ControllerState update(String message) {
        System.out.println("action selected: " + message);
        if (selectedActions.contains(message)) {
            switch (message) {
                case ("grab"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","grab"));
                    actionSelected = "grab";
                    break;
                case ("run"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","run"));
                    actionSelected = "run";
                    break;
                case ("shoot"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","shoot"));
                    actionSelected = "shoot";
                    break;
                case ("reload"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","reload"));
                    actionSelected = "reload";
                    break;
                case ("end round"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","end round"));
                    previousActions.add("end round");
                    actionSelected = "end round";
                    return new ActionSelectState(clientController,previousActions,isReload);
                case ("end turn"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","end turn"));
                    actionSelected = "end turn";
                    break;
                default:
                    clientController.reportError("not valid action!");
            }
        }
       return this;
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isPlaying() && serverMessage.getMessage().equalsIgnoreCase("ACTIONSELECTED")){
            switch (actionSelected){
                case "grab": return new GrabState(clientController, previousActions,isReload).initState();
                case "shoot": return new ShootState(clientController, previousActions,isReload).initState();
                case "run": return new WalkState(clientController, previousActions,isReload).initState();
                case "reload": return new ReloadState(clientController, previousActions).initState();
                case "end turn": return new NonPlayingState(clientController).initState();
                //case "end round": return new ActionSelectState(clientController, previousActions,isReload).initState();
            }
        }
        clientController.reportError(serverMessage.getMessage());
        return this;
    }

}

