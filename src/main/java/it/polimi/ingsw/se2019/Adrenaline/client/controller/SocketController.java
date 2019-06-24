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

public class SocketController implements GameServerInterface {

    private ClientController controller;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean active;

    protected SocketController(ClientController clientController, Socket socket) throws IOException {
        this.controller = clientController;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        (new SocketListener()).start();
        active = true;
    }

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

        private boolean isActive() {
            return active && !socket.isClosed();
        }
    }

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
