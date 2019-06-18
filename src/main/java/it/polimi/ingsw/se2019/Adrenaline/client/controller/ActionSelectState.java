package it.polimi.ingsw.se2019.Adrenaline.client.controller;

public class ActionSelectState extends ControllerState{

    private int currentRound;
    private int currentStep;
    private int numRound;

    public ActionSelectState(ClientController clientController, int currentRound, int currentStep) {

        super(clientController, "Please select a map:\n");
        if (this.clientController.getActionMode() == 4){
            numRound = 1;
        }
        else{
            numRound = 2;
        }
        this.currentRound = currentRound;
        this.currentStep = currentStep;

        if (currentRound ==1){
        }

    }

    public ControllerState update(String message) {
        return this;
    }
}
