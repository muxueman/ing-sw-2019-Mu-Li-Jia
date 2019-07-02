package it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ControllerState;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

import java.util.ArrayList;

public class PlayingState extends ControllerState {

    //private int selectedAction;
    private ClientMessage clientMessage;
    private int actionMode;

    public PlayingState(ClientController clientController, int actionMode){
        super(clientController, "");
        this.actionMode = actionMode;
        clientMessage = new ClientMessage("ACTION", actionMode);
        System.out.println("playing state!");
    }

    @Override
    public ControllerState initState(){
        clientController.setPlaying(true);
        return super.initState();
    }
    @Override
    public ControllerState update(String message) {
        //
        return initState();
    }
    //@Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        return null;
    }
    //switch sate depending on the message of actions
    private ControllerState request(String message) {
        // we have Action(step), Round and Turn to represent
        int currentRound = 1;
        int currentStep = 1;
        int numRound = 1;
        ArrayList<String> seriesOfActions = new ArrayList<>();

        if (actionMode != 4)
            numRound = 2;
/*
        while(currentRound <= numRound){
            switch(actionMode) {
                case 0:
                case 1:
                case 2:
                    clientController.sendToView("select an action: run, grab, shoot, end round");
                    //clientController.sendToView(["run", "grab", "shoot", "end round"]);
                    clientController.sendToView.sendToServer("select an action: run, grab, shoot, end round");
                    choiceMessage1 = getActionChoice();
                    switch (choiceMessage1) {
                        case "grab":
                            takeGrabAction(player, choiceMessage1);
                            break;
                        case "shoot":r
                            takeShootAction(player, choiceMessage1);
                            break;
                        case "end round":
                            break;
                        case "run":
                            takeRunAction(player, choiceMessage1);
                            if (actionMode == 2) {
                                showAvailableAction(["run", "grab", "shoot", "end round"]);
                                choiceMessage2 = getActionChoice();
                                switch (choiceMessage2) {
                                    case "grab":
                                        takeGrabAction(player, choiceMessage2);
                                        break;
                                    case "shoot":
                                        takeShootAction(player, choiceMessage2);
                                        break;
                                    case "end round":
                                        break;
                                    case "run":
                                        takeRunAction(player, choiceMessage2):
                                        showAvailableAction(["run", "grab", "end round"]);
                                        choiceMessage3 = getActionChoice();
                                        switch (choiceMessage3) {
                                            case "grab":
                                                takeGrabAction(player, choiceMessage3);
                                                break;
                                            case "run":
                                                takeRunAction(player, choiceMessage3);
                                                break;
                                            case "end round":
                                                break;
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


    }
*/
return null;
    }
}
