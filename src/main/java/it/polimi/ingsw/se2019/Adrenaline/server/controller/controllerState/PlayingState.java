package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.PlayMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.BoardUpdate;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.MapUpdate;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.PlayerStatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.SpawnLocationUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionRun;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayingState implements GameServerInterface {

    private MatchController matchController;

    public PlayingState(MatchController matchController,ClientInterface client){

        this.matchController = matchController;
        Logger.getGlobal().log(Level.INFO," playing state: conroller astaet {0}", matchController.getCurrentPlayer().getUserName());
        //client.updateStatus(new PlayMessage());
    }

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        String textMove = message.getTextMove();
        switch (textMove) {
            case "SELECTEDACTION":
                Logger.getGlobal().log(Level.INFO," {0} selected an action", matchController.getCurrentPlayer().getUserName());
                ServerMessage messageS = new ServerMessage(true, "ACTIONSELECTED");
                client.updateStatus(messageS);
                Logger.getGlobal().log(Level.INFO," {0} selected an action", matchController.getCurrentPlayer().getUserName());
                ServerMessage serverMessage1 = new ServerMessage(false,"OTHER",matchController.getPlayers().get(client).getUserName() + " select to " + message.getMainParamS());
                matchController.updateNotCurrentPlayer(serverMessage1);
                return this;
            case "GRABAMMOTILE":
                System.out.println(" grab amotile send to server succ!");
                ActionGrab grab1 = new ActionGrab();
                grab1.pickAmmoTile(matchController.getCurrentPlayer());
                ServerMessage messageG = new ServerMessage(true, "GRAB");
                messageG.addStatusUpdate(new SpawnLocationUpdate(matchController.getPlayBoard(),matchController.getPlayBoard().getMap()));
                //messageG.addStatusUpdate(new MapUpdate(matchController.getPlayBoard().getMap()));
                //messageG.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                client.updateStatus(messageG);
                ServerMessage serverMessage2 = new ServerMessage(false,"UPDATE");
                serverMessage2.addStatusUpdate(new SpawnLocationUpdate(matchController.getPlayBoard(),matchController.getPlayBoard().getMap()));
                matchController.updateNotCurrentPlayer(serverMessage2);
                return this;
            case "GRABWEAPON":
                System.out.println(" grab weapon send to server succ!");
                ActionGrab grab2 = new ActionGrab();
                grab2.pickWeaponCrad(matchController.getCurrentPlayer(), message.getMainParam());
                System.out.println(matchController.getCurrentPlayer().getWeaponsOwned().size());
                ServerMessage messageGW = new ServerMessage(true, "GRAB");
                //messageGW.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                messageGW.addStatusUpdate(new SpawnLocationUpdate(matchController.getPlayBoard(),matchController.getPlayBoard().getMap()));
                client.updateStatus(messageGW);
                ServerMessage serverMessage3 = new ServerMessage(false,"UPDATE");
                serverMessage3.addStatusUpdate(new SpawnLocationUpdate(matchController.getPlayBoard(),matchController.getPlayBoard().getMap()));
                matchController.updateNotCurrentPlayer(serverMessage3);
                return this;
            case "WALK":
                ActionRun run = new ActionRun(matchController.getCurrentPlayer());
                try {
                    System.out.println(message.getMainParam());
                    run.ActionRun(message.getMainParam());
                    ServerMessage messagewalkSucc = new ServerMessage(true, "WALK");
                    messagewalkSucc.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                    client.updateStatus(messagewalkSucc);
                    ServerMessage serverMessage4 = new ServerMessage(false,"UPDATE");
                    serverMessage4.addStatusUpdate(new SpawnLocationUpdate(matchController.getPlayBoard(),matchController.getPlayBoard().getMap()));
                    //serverMessage4.addStatusUpdate(new MapUpdate(matchController.getPlayBoard().getMap()));
                    //serverMessage4.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                    matchController.updateNotCurrentPlayer(serverMessage4);
                } catch (InvalidRunException e) {
                    ServerMessage messagewalkfalse = new ServerMessage(true, "notRun");
                    messagewalkfalse.addStatusUpdate(new PlayerStatusUpdate(matchController.getCurrentPlayer()));
                    client.updateStatus(messagewalkfalse);
                }
                return this;
            case "RELOAD":


                ServerMessage serverMessage5 = new ServerMessage(false,"UPDATE");
                serverMessage5.addStatusUpdate(new BoardUpdate(matchController.getPlayBoard()));
                serverMessage5.addStatusUpdate(new MapUpdate(matchController.getPlayBoard().getMap()));
                matchController.updateNotCurrentPlayer(serverMessage5);
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