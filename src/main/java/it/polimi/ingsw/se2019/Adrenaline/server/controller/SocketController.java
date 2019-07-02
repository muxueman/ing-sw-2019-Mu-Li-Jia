package it.polimi.ingsw.se2019.Adrenaline.server.controller;

/**
 * The SocketController is the implementation of the ServerController, that use the RMI connection.
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.*;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.Lobby;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState.PlayerSetupState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.logging.Logger;

public class SocketController implements ClientInterface, ServerController {

    private final Socket socket;
    private boolean active;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private GameServerInterface state;
    private Lobby lobby;
    private MatchController matchController;

    public SocketController(Socket clientConnection, Lobby lobby) throws IOException {
        socket = clientConnection;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        active = true;
        this.lobby = lobby;
        matchController = null;
        state = new PlayerSetupState(this, lobby);
        (new SocketListener()).start();
    }


    private class SocketListener extends Thread {

        @Override
        public void run() {
            ClientMessage clientMessage;
            while (isActive()) {
                try {
                    clientMessage = (ClientMessage) in.readObject();
                    state = state.update(clientMessage, SocketController.this);
                } catch (IOException | ClassNotFoundException e) {
                    Logger.getGlobal().warning(e.getMessage());
                    active = false;
                    lobby.disconnect(SocketController.this, matchController);
                }
            }
        }
    }

    @Override
    public boolean isActive() {
        return active && !socket.isClosed();
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void sendError(String error) {
        try {
            ServerMessage errorMessage = new ErrorMessage(error);
            out.writeObject(errorMessage);
            out.flush();
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    @Override
    public void updateStatus(ServerMessage serverMessage) {
        try {
            out.writeObject(serverMessage);
            out.flush();
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void checkConnection() throws RemoteException {
        // just checking the connection...
    }

    @Override
    public void nextState(GameServerInterface nextState) {
        state = nextState;
    }

    @Override
    public void setMatch(MatchController matchController) {
        this.matchController = matchController;
    }

    @Override
    public MatchController getMatch() {
        return matchController;
    }
}
