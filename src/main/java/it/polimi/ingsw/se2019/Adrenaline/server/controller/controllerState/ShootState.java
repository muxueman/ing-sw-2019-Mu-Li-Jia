package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.PlayerStatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShootState implements GameServerInterface {
    private ActionShoot shoot;
    private MatchController matchController;
    private GameServerInterface previousState;

    public ShootState(MatchController matchController, ClientInterface client, GameServerInterface previouState) throws RemoteException {
        this.matchController = matchController;
        this.previousState = previouState;
        Logger.getGlobal().log(Level.INFO, " shoot state: ", matchController.getCurrentPlayer().getUserName());
        //client.updateStatus(new PlayMessage());
    }

    public void setShoot(ActionShoot shoot){
        this.shoot = shoot;
    }

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        String textMove = message.getTextMove();
        switch (textMove) {
            case "TARGET BASIC":{
                try{
                    ServerMessage messageShootTarget = new ServerMessage(true, "shoot done! contiue with side effect?");
                    shoot.checkIfInputValid(message.getMainParamS());
                    if (shoot.getTargetBasic().size() == 0)
                        messageShootTarget = new ServerMessage(true, "nobody to shoot! " +
                                "contiue with side effect?");

                    if(shoot.checkWeaponType() == 0){
                        messageShootTarget = new ServerMessage(true,"shoot done! finish this round?",0);
                        messageShootTarget.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                        return previousState;
                    }
                    else {
                        messageShootTarget = new ServerMessage(true, "shoot done, type in the direction with 0,1,2,3 to which " +
                                "you want your target move");
                        messageShootTarget.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                        return this;
                    }

                }
                catch (InvalidNameException e) {
                    ServerMessage errorMessage = new ServerMessage(true, "inValid target, try again?");
                    return this;
                }

            }
            case "TARGET MOVE":{
                try{
                    shoot.grenadeMove(message.getMainParamS());
                    ServerMessage messageMoveTarget = new ServerMessage(true, "move done! contiue with side effect?");
                    messageMoveTarget.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                    return previousState;
                }
                catch (InvalidRunException e){
                    ServerMessage errorMessage = new ServerMessage(true, "inValid run, try again?");
                    return this;
                }
            }
        }
        return this;
    }
}


