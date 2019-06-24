package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;

import java.util.ArrayList;

public class ShootState extends ControllerState{

    private ServerController serverController;
    private MatchController matchController;
    private ArrayList<Player> availbleShootPlayers;
    private ArrayList<String> previousActions;

    public ShootState(ClientController controller, ArrayList<String> previousActions){
        super(controller, "please select your weapon cards: ");
        this.serverController = serverController;
        this.previousActions = previousActions;
        this.matchController = serverController.getMatch();
        // model.get availble weapon cards
        //message += >>>
        this.initState();
    }
    @Override
    public ControllerState update(String message){

        //
        return this;
    }


}
