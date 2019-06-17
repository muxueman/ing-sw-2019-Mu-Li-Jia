package it.polimi.ingsw.se2019.Adrenaline.server;

/**
 * This class creates the server socket and RMI based on the port number and creates connection.
 * Always runs the server first before creates a connection.
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.SocketController;

import java.io.IOException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class Server {

    //port number (if cannot be established, possibly because current port number is being used
    static final int RMI_PORT = 1099;
    static final int SOCKET_PORT = 1100;

    private Lobby lobby;

    public Server() {

        //(new SocketServer(this, SOCKET_PORT)).start();
        RMIServer rmiServer = new RMIServer(this);

        try {
            LocateRegistry.createRegistry(RMI_PORT);
        } catch (RemoteException e) {
            Logger.getGlobal().info("Registry already on!");
        }
        try {
            UnicastRemoteObject.exportObject(rmiServer, RMI_PORT);
            Naming.rebind("//localhost/Server", rmiServer);
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
        }
        lobby = new Lobby();
    }

    public synchronized void addClient(ClientInterface client, ServerController controller) {
        //Lobby.addClient(client, controller);
    }
/*
    public synchronized void addClient(Socket clientConnection) throws IOException {
         SocketController client = new SocketController(clientConnection, lobby);
         addClient(client, client);
    }
*/
    public Lobby getLobby() {
        return lobby;
    }

    public static void main(String[] args) {
        new Server();
    }
}
