package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import java.util.ArrayList;

public class ActionSelectState extends ControllerState {

    private int currentRound;
    private int currentStep;
    private int numRound;
    private int actionMode;
    private ArrayList<String> previousActions;

    public ActionSelectState(ClientController clientController, ArrayList<String> previousActions) {

        super(clientController, "Please select an Action:\n");
        if (actionMode == 4) {
            numRound = 1;
        } else {
            numRound = 2;
        }
        this.currentRound = 1;
        this.currentStep = 1;
        this.previousActions = previousActions;
        this.actionMode = this.clientController.getActionMode();
        message += addChoice();
    }

    protected String addChoice() {
        while (currentRound <= numRound) {
            switch (actionMode) {
                case 0:
                case 1:
                case 2:
                    if (previousActions.get(0) == null) {
                        System.out.println("select an action: run, grab, shoot, end round");
                    } else {
                        switch (previousActions.get(0)) {
                            case "grab":
                            case "shoot":
                            case "end round":
                                break;
                            case "run":
                                if (actionMode == 2) {
                                    if (previousActions.get(1) == null) {
                                        System.out.println("run, grab, shoot, end round");
                                    } else {
                                        switch (previousActions.get(1)) {
                                            case "grab":
                                            case "shoot":
                                            case "end round":
                                                break;
                                            case "run":
                                                if (previousActions.get(2) == null) {
                                                    System.out.println("run, grab, end round");
                                                } else {
                                                    switch (previousActions.get(2)) {
                                                        case "grab":
                                                        case "run":
                                                        case "end round":
                                                            break;
                                                    }
                                                }
                                                break;
                                        }

                                    }
                                }
                        }





/*
                                        takeRunAction(player, choiceMessage2):

                                        choiceMessage3 = getActionChoice();

                                        }
                                        break;
                                }
                            } else {
                                showAvailableAction["run", "grab", "end round"];
                                choiceMessage4 = getActionChoice();
                                switch (choiceMessage4) {
                                    case "grab":
                                        takeGrabAction(player, choieMessage4);
                                        break;
                                    case "end round":
                                        break;
                                    case "run":
                                        takeRunAction(player, choiceMessage4);
                                        if (actionMode == 0) {
                                            showAvailableAction(["run", "end round"]);
                                            choiceMessage5 = getActionChoice();
                                            switch (choiceMessage5) {
                                                case "run":
                                                    takeRunAction(player, choiceMessage5);
                                                    break;
                                                case "end round":
                                                    break;
                                            }
                                        }
                                        if (actionMode == 1) {
                                            showAvailableAction(["run", "grab", "end round"]);
                                            choiceMessage6 = getActionChoice();
                                            switch (choiceMessage6) {
                                                case "run":
                                                    takeRunAction(player, choiceMessage6);
                                                    break;
                                                case "grab":
                                                    takeGrabAction(player, choiceMessage6):
                                                    break;
                                                case "end round":
                                                    break;
                                            }
                                        }
                                }
                            }
                            break;
                    }
            }
            break;
            case 3: case 4:
                showAvailableAction(["run", "grab", "shoot", "reload", "end round"]);
                choiceMessage7 = getActionChoice();
                switch (choiceMessage7) {
                    case "run":
                        takeRunAction(player, choiceMessage7);
                        showAvailableAction(["run", "grab", "shoot", "reload", "end round"]);
                        choiceMessage9 = getActionChoice();
                        switch (choiceMessage9) {
                            case "run":
                                takeRunAction(player, choiceMessage9);
                                if (actionMode == 3) {
                                    showAvailableAction(["run", "grab", "end round"]);
                                    choiceMessage11 = getActionChoice();
                                    switch (choiceMessage11) {
                                        case "run":
                                            takeRunAction(player, choiceMessage11);
                                            showAvailableAction(["run", "end round"]);
                                            choiceMessage12 = getActionChoice();
                                            switch (choiceMessage12) {
                                                case "run":
                                                    takeRunAction(player, choiceMessage12);
                                                    break;
                                                case "end round":
                                                    break;
                                            }
                                            break;
                                        case "grab":
                                            takeGrabAction(player, choiceMessage11);
                                            break;
                                        case "end round":
                                            break;
                                    }
                                }
                                if (actionMode == 4) {
                                    showAvailableAction(["run", "grab", "shoot", "reload", "end round"]);
                                    choiceMessage13 = getActionChoice();
                                    switch (choiceMessage13) {
                                        case "run":
                                            takeRunAction(player, choiceMessage13);
                                            showAvailableAction(["grab", "end round"]);
                                            choiceMessage15 = getActionChoice();
                                            switch (choiceMessage15) {
                                                case "grab":
                                                    takeGrabAction(player, choiceMessage15);
                                                    break;
                                                case "end round":
                                                    break;
                                            }
                                            break;
                                        case "grab":
                                            takeGrabAction(player, choiceMessage13);
                                            break;
                                        case "shoot":
                                            takeShootAction(player, choiceMessage13);
                                            break;
                                        case "reload":
                                            takeReloadAction(player);
                                            showAvailableAction(["shoot", "end round"]);
                                            choiceMessage14 = getActionChoice();
                                            switch (choiceMessage14) {
                                                case "shoot":
                                                    takeShootAction(player, choiceMessage14);
                                                    break;
                                                case "end round":
                                                    break;
                                            }
                                            break;
                                        case "end round":
                                            break;
                                    }
                                }
                                break;
                            case "grab":
                                takeGrabAction(player, choiceMessage9);
                                break;
                            case "shoot":
                                takeShootAction(player, choiceMessage9);
                                break;
                            case "reload":
                                takeReloadAction(player);
                                showAvailableAction(["shoot", "end round"]);
                                choiceMessage10 = getActionChoice();
                                switch (choiceMessage10) {
                                    case "shoot":
                                        takeShootAction(player, choiceMessage10);
                                        break;
                                    case "end round":
                                        break;
                                }
                                break;
                            case "end round":
                                break;
                        }
                        break;
                    case "grab":
                        takeGrabAction(player, choiceMessage7);
                        break;
                    case "shoot":
                        takeShootAction(player, choiceMessage7);
                        break;
                    case "reload":
                        takeReloadAction(player, choiceMessage7);
                        showAvailableAction(["shoot", "end round"]);
                        choiceMessaged8 = getActionChoice();
                        switch (choiceMessage8) {
                            case "shoot":
                                takeShootAction(player, choiceMessage8);
                                break;
                            case "end round":
                                break;zzZzZZZ ZZZ
                            if (actionMode == 0 || actionMode == 1 ||actionMode == 2) {
                                showAvailableAction(["reload"]);
                                takeReloadAction(player);
                            }
                            endTurn();


*/
                    }


            }
        }
        return "1";
    }

    public ControllerState update(String message) {
        return this;
    }
}

