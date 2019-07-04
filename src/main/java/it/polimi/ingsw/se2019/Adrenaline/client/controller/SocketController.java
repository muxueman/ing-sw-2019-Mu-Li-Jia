package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.logging.Logger;
/**
 *
 * The SocketController class is the implementation of the GameServerInterface through a socket connection.
 *
 * @author Mu xueman
 *
 */
public class SocketController implements GameServerInterface {

    private ClientController controller;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean active;

    /**
     *
     * The constructor creates a local controller of the game that communicates with the server.
     * @param clientController is a reference to the controller.
     * @param socket is a reference to the socket used.
     * @throws IOException when the input or the output is not valid.
     *
     */

    protected SocketController(ClientController clientController, Socket socket) throws IOException {
        this.controller = clientController;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        (new SocketListener()).start();
        active = true;
    }
    /**
     *
     * The SocketListener class is a Thread that listen a socket port.
     *
     */
    private class SocketListener extends Thread {

        @Override
        public void run() {
            ServerMessage serverMessage;
            while (isActive()) {
                try {
                    serverMessage = (ServerMessage) in.readObject();
                    controller.updateStatus(serverMessage);
                }catch (IOException | ClassNotFoundException e) {
                    controller.reportError("Connection error (listening), retry.");
                    active = false;
                }
            }
        }

        /**
         * The isActive method is used to check if a socket is still active and not closed.
         * @return true if the socket is active, false if not.
         */
        private boolean isActive() {
            return active && !socket.isClosed();
        }
    }

    /**
     *
     * The update method is used to communicate with the client.
     * @param message is the message to be sent.
     * @param client that has to receive the message.
     * @return the socket controller.
     *
     */

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            controller.reportError("Connection error (sending), retry.");
        }
        return this;
    }
}
