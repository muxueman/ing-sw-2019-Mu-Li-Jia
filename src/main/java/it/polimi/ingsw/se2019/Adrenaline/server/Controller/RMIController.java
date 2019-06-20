package it.polimi.ingsw.se2019.Adrenaline.server.controller;

/**
 * The RMIController is the implementation of the ServerController, that use the RMI connection.
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;

import it.polimi.ingsw.se2019.Adrenaline.server.Lobby;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class RMIController implements GameServerInterface, ServerController {

    //also implement serverController
    private ClientInterface client;
    private Lobby lobby;
    private MatchController matchController;
    private GameServerInterface state;
    private boolean active;

    public RMIController(Lobby lobby, ClientInterface client) {
        state = new PlayerSetupState(this, lobby);
        this.lobby = lobby;
        this.client = client;
        active = true;
        (new Timer()).scheduleAtFixedRate(new RMIPing(), 0, 1000);
    }


    // The update method is used to send to the server an update.
    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException {
        (new AsyncUpdate(message, client)).start();
        return null;
    }

    // The isActive method is used to check if the RMIController is active.
    //@Override
    public boolean isActive() {
        return active;
    }

    // the setActive method is used to set active or not the RMIController.
    public void setActive(boolean active) {
        this.active = active;
    }

    public void nextState(GameServerInterface nextState) {
        state = nextState;
    }

    //@Override
    public void setMatch(MatchController matchController) {
        this.matchController = matchController;
    }

    //@Override
    public MatchController getMatch() {
        return matchController;
    }

    private class AsyncUpdate extends Thread {

        private ClientMessage message;
        private ClientInterface client;

        AsyncUpdate(ClientMessage message, ClientInterface client) {
            this.message = message;
            this.client = client;
        }

        @Override
        public void run() {
            try {
                state = state.update(message, client);
            } catch (RemoteException e) {
                Logger.getGlobal().warning("There has been a problem with a client.");
            }
        }
    }

    private class RMIPing extends TimerTask {

        private int seconds;

        private RMIPing() {
            seconds = 10;
        }

        @Override
        public void run() {
            if(seconds == 0) {
                ping();
                seconds = 10;
            }
            seconds--;
        }

        private void ping() {
            try {
                client.checkConnection();
            } catch (RemoteException e) {
                Logger.getGlobal().warning("There has been a problem with a client.");
                if (isActive()) {
                    setActive(false);
                    lobby.disconnect(RMIController.this, matchController);
                    //playBoard.disconnect(RMIController.this, matchController);
                }
                this.cancel();
            }
        }
    }
}
