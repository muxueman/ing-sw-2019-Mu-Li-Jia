package it.polimi.ingsw.se2019.Adrenaline.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketServer extends Thread {

    private final Server server;
    private ServerSocket serverSocket;

    SocketServer(Server server, int port) {
        this.server = server;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true) {
            Socket newClientConnection;
            try {
                newClientConnection = serverSocket.accept();
                Logger.getGlobal().info("A new client connected.");
                //server.addPlayer(newClientConnection);
            } catch (IOException e) {
                Logger.getGlobal().warning(e.getMessage());
            }
        }
    }
}
