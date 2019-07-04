package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

import java.util.ArrayList;

public class ActionSelectState extends ControllerState {

    private int currentRound;
    private int numRound;
    private int actionMode;
    private String additionalMessage;
    private ArrayList<String> previousActions;
    private String actionSelected;
    //below is only to check whether client choose the right action (CLI exclusive)
    private ArrayList<String> selectedActions;

    public ActionSelectState(ClientController clientController, ArrayList<String> previousActions) {


        super(clientController, "Please select an Action:\n");
        actionMode = clientController.getModel().getBoardStatus().getPlayer(clientController.getPlayerID()).getActionMode();
        if (actionMode == 4) {
            numRound = 1;
        } else {
            numRound = 2;
        }
        this.currentRound = 1;
        this.previousActions = previousActions;
        this.selectedActions = new ArrayList<>();
        this.actionMode = this.clientController.getActionMode();
        additionalMessage =  addChoice();
        message += additionalMessage;
        for (String m: additionalMessage.split(",")){
            selectedActions.add(m);
        }
    }

    //only for test
    public ArrayList<String> getPreviousActions(){return this.previousActions;}
    //need test!!!
    public String addChoice() {
        System.out.println(previousActions);
        while (currentRound <= numRound) {
            switch (actionMode) {
                case 0:
                case 1:
                case 2:
                    if (previousActions.size() == 0) {
                        return "run,grab,shoot,end round";
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "run":
                                if (actionMode == 2) {
                                    if (previousActions.get(1) == null) {
                                        return "run,grab,shoot,end round";
                                    } else {
                                        switch (previousActions.get(1)) {
                                            case "grab":
                                            case "shoot":
                                            case "end round":
                                                break;
                                            case "run":
                                                if (previousActions.size() == 2) {
                                                    return "run,grab,end round";
                                                }
                                                break;
                                        }
                                    }
                                } else if (previousActions.size() == 1) {
                                    return "run,grab,end round";
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "end round":
                                            break;
                                        case "run":
                                            if (previousActions.size() == 2) {
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
                    currentRound += 1;
                    break;
                case 3:
                case 4:
                    if (previousActions.size() == 0) {
                        return "run,grab,shoot,reload,end round";
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "reload":
                                if (previousActions.size() == 1) {
                                    return "shoot,end round";
                                }
                                break;
                            case "run":
                                if (previousActions.size() == 1) {
                                    return "run,grab,shoot,reload,end round";
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "shoot":
                                        case "end round":
                                            break;
                                        case "reload":
                                            if (previousActions.size() == 2) {
                                                return "shoot,end round";
                                            }
                                            break;
                                        case "run":
                                            if (actionMode == 3) {
                                                if (previousActions.size() == 2) {
                                                    return "run,grab,end round";
                                                } else {
                                                    switch (previousActions.get(2)) {
                                                        case "grab":
                                                        case "end round":
                                                            break;
                                                        case "run":
                                                            if (previousActions.size() == 3) {
                                                                return "run,end round";
                                                            }
                                                            break;
                                                    }
                                                }
                                            }
                                            if (actionMode == 4) {
                                                if (previousActions.size() == 2) {
                                                    return "run,grab,shoot,reload,end round";
                                                } else {
                                                    switch (previousActions.get(2)) {
                                                        case "grab":
                                                        case "shoot":
                                                        case "end round":
                                                            break;
                                                        case "reload":
                                                            if (previousActions.size() == 3) {
                                                                return "shoot,end round";
                                                            }
                                                            break;
                                                        case "run":
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
                    currentRound += 1;
                    //clientController.sendMessage("end of one turn");
                    break;
            }
            this.previousActions = new ArrayList<>();
        }
        return "end turn";
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
                case ("end round"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","end round"));
                    previousActions.add("end round");
                    actionSelected = "end round";
                    break;
                case ("end turn"):
                    clientController.sendToServer(new ClientMessage("SELECTEDACTION","end turn"));
                    actionSelected = "end turn";
                    break;
                default:
                    actionSelected = "end round";
                    clientController.reportError("not valid action!");
            }
        }
        return this;
    }

    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.getMessage().equalsIgnoreCase("ACTIONSELECTED")){
            switch (actionSelected){
                case "grab": return new GrabState(clientController, previousActions).initState();
                case "shoot": return new ShootState(clientController, previousActions).initState();
                case "run": return new WalkState(clientController, previousActions).initState();
                case "end round": return new ActionSelectState(clientController, previousActions).initState();
                case "reload": return new ReloadState(clientController, previousActions).initState();
                case "end turn": return new ReloadState(clientController,previousActions).initState();
            }
        }
        clientController.reportError(serverMessage.getMessage());
        return this;
    }

}

