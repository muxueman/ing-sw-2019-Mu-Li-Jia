package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;

import java.util.ArrayList;

public class ActionSelectState extends ControllerState {

    private int currentRound;
    private int numRound;
    private int actionMode;
    private String additionalMessage;
    private ArrayList<String> previousActions;
    //below is only to check whether client choose the right action (CLI exclusive)
    private ArrayList<String> selectedActions;

    public ActionSelectState(ClientController clientController, ArrayList<String> previousActions) {

        super(clientController, "Please select an Action:\n");
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

    //need test!!!
    protected String addChoice() {
        String m = "";
        while (currentRound <= numRound) {
            switch (actionMode) {
                case 0:
                case 1:
                case 2:
                    if (previousActions.get(0) == null) {
                        return "select an action: run, grab, shoot, end round";
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "run":
                                if (actionMode == 2) {
                                    if (previousActions.get(1) == null) {
                                        return "run, grab, shoot, end round";
                                    } else {
                                        switch (previousActions.get(1)) {
                                            case "grab":
                                            case "shoot":
                                            case "end round":
                                                break;
                                            case "run":
                                                if (previousActions.get(2) == null) {
                                                    return "run, grab, end round";
                                                }
                                                break;
                                        }
                                    }
                                } else if (previousActions.get(1) == null) {
                                    return "run, grab, end round";
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "end round":
                                            break;
                                        case "run":
                                            if (previousActions.get(2) == null) {
                                                if (actionMode == 1) {
                                                    return "run, grab, end round";
                                                }
                                                if (actionMode == 0) {
                                                    return "run, grab";
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
                    if (previousActions.get(0) == null) {
                        return "run, grab, shoot, reload, end round";
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "reload":
                                if (previousActions.get(1) == null) {
                                    return "shoot, end round";
                                }
                                break;
                            case "run":
                                if (previousActions.get(1) == null) {
                                    return "run, grab, shoot, reload, end round";
                                } else {
                                    switch (previousActions.get(1)) {
                                        case "grab":
                                        case "shoot":
                                        case "end round":
                                            break;
                                        case "reload":
                                            if (previousActions.get(2) == null) {
                                                return "shoot, end round";
                                            }
                                            break;
                                        case "run":
                                            if (actionMode == 3) {
                                                if (previousActions.get(2) == null) {
                                                    return "run, grab, end round";
                                                } else {
                                                    switch (previousActions.get(2)) {
                                                        case "grab":
                                                        case "end round":
                                                            break;
                                                        case "run":
                                                            if (previousActions.get(3) == null) {
                                                                return "run, end round";
                                                            }
                                                            break;
                                                    }
                                                }
                                            }
                                            if (actionMode == 4) {
                                                if (previousActions.get(2) == null) {
                                                    return "run, grab, shoot, reload, end round";
                                                } else {
                                                    switch (previousActions.get(2)) {
                                                        case "grab":
                                                        case "shoot":
                                                        case "end round":
                                                            break;
                                                        case "reload":
                                                            if (previousActions.get(3) == null) {
                                                                return "shoot, end round";
                                                            }
                                                            break;
                                                        case "run":
                                                            if (previousActions.get(3) == null) {
                                                                return "grab, end round";
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
                    break;
            }
        }
        return "end turn";
    }
    public ControllerState update(String message) {
        if (selectedActions.contains(message)) {
            switch (message) {
                case ("grab"):
                    return new GrabState(clientController, previousActions);
                case ("run"):
                    return new WalkState(clientController, previousActions);
                case ("shoot"):
                    return new ShootState(clientController, previousActions);
                case ("end round"):
                    previousActions.add("end round");
                    return new ActionSelectState(clientController, previousActions);
                case ("end turn"):
                    return new NonPlayingState(clientController);
                default:
                    clientController.reportError("not valid action!");
            }
        }
        return this;
    }
}

