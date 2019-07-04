package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.PlayMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.PlayerStatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionRun;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayingState implements GameServerInterface {

    private MatchController matchController;

    public PlayingState(MatchController matchController,ClientInterface client) throws RemoteException{

        this.matchController = matchController;
        Logger.getGlobal().log(Level.INFO," playing state: conroller astaet", matchController.getCurrentPlayer().getUserName());
        //client.updateStatus(new PlayMessage());
    }

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        String textMove = message.getTextMove();
        switch (textMove) {
            case "SELECTEDACTION":
                ServerMessage messageS = new ServerMessage(true, "ACTIONSELECTED");
                client.updateStatus(messageS);
                return this;
            case "GRABAMMOTILE":
                ActionGrab grab1 = new ActionGrab();
                grab1.pickAmmoTile(matchController.getCurrentPlayer());
                ServerMessage messageG = new ServerMessage(true, "GRAB");
                messageG.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                client.updateStatus(messageG);
                return this;
            case "GRABWEAPON":
                System.out.println("send to server succ!");
                ActionGrab grab2 = new ActionGrab();
                grab2.pickWeaponCrad(matchController.getCurrentPlayer(), message.getMainParam());
                System.out.println(matchController.getCurrentPlayer().getWeaponsOwned().size());
                ServerMessage messageGW = new ServerMessage(true, "GRAB");
                messageGW.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                client.updateStatus(messageGW);
                return this;
            case "WALK":
                ActionRun run = new ActionRun(matchController.getCurrentPlayer());
                try {
                    run.ActionRun(message.getMainParam());
                    ServerMessage messagewalkSucc = new ServerMessage(true, "WALK");
                    messagewalkSucc.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                } catch (InvalidRunException e) {
                    ServerMessage messagewalkfalse = new ServerMessage(true, "notRun");
                    messagewalkfalse.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                }
                return this;
            case "SHOOT":
                matchController.getCurrentPlayer().useWeapon(message.getMainParamS());
                ActionShoot shoot = new ActionShoot(matchController.getCurrentPlayer());
                ServerMessage messageShootTarget = new ServerMessage(true, shoot.getTargetNameBasic());
                messageShootTarget.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                ShootState shootState = new ShootState(matchController, client);
                shootState.setShoot(shoot);
                return shootState;
            default:
                client.updateStatus(new ErrorMessage("Wait for your turn!"));
                return this;

//        } else if (textMove.equalsIgnoreCase("CANCEL")) {
//            //matchController.resetToolCard(message.getMainParam() - 1);
//            return this;
//        } else if (textMove.equalsIgnoreCase("PASS")) {
//            matchController.nextTurn();
//            //return matchController.isPlaying(client) ? this : new NonPlayingState();
//        }
        }
    }
}