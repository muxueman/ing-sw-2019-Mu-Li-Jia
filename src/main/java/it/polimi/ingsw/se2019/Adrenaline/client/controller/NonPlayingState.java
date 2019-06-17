package it.polimi.ingsw.se2019.Adrenaline.client.controller;

public class NonPlayingState extends ControllerState{

    //constructor
    public NonPlayingState(ClientController clientController){
        super(clientController, "");
    }

    @Override
    public ControllerState initState() {
        //clientController.getView().play(false);
        clientController.playing = false;
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
            //return new PlayingState(clientController).initState();
            return null;
        }
        return this;
    }
}
