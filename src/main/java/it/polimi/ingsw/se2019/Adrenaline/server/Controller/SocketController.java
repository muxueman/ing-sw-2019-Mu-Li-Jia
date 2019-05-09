package it.polimi.ingsw.se2019.Adrenaline.server.controller;

/**
 * The SocketController is the implementation of the ServerController, that use the RMI connection.
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayBoard;

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
    private PlayServerInterface state;
    private PlayBoard playBoard;
    //private MatchController matchController;

    public SocketController(Socket clientConnection, PlayBoard playBoard) throws IOException {
        socket = clientConnection;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        active = true;
        this.playBoard = playBoard;
        //matchController = null;
        //state = new PlayerSetupState(this, playBoard);
        (new SocketListener()).start();
    }

    // The SocketListener class implements a socket that receive the message.
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
                    //playBoard.disconnect(SocketController.this, matchController);
                }
            }
        }
    }

    // The isActive method is used to check if the SocketController is active.
    @Override
    public boolean isActive() {
        return active && !socket.isClosed();
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    // The sendError method is used to report an error to the client.
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

    // The updateStatus method elaborates the server message, if it is accepted
    // change the status of the controller, else sends error.

    @Override
    public void updateStatus(ServerMessage serverMessage) {
        try {
            out.writeObject(serverMessage);
            out.flush();
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    @Override
    public void checkConnection() throws RemoteException {
        // just checking the connection...
    }

    @Override
    public void nextState(PlayServerInterface nextState) {
        state = nextState;
    }
/*
    @Override
    public void setMatch(MatchController matchController) {
        this.matchController = matchController;
    }

    @Override
    public MatchController getMatch() {
        return matchController;
    }
    */
}
