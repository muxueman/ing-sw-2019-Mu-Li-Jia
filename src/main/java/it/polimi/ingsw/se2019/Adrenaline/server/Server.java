package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.SocketController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayBoard;

import java.io.IOException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class Server {
    static final int RMI_PORT = 1099;
    static final int SOCKET_PORT = 1111;

    private PlayBoard playBoard;

    public Server() {

        (new SocketServer(this, SOCKET_PORT)).start();
        RMIServer rmiServer = new RMIServer(this);
        try {
            LocateRegistry.createRegistry(RMI_PORT);
        } catch (RemoteException e) {
            Logger.getGlobal().info("Registry already on!");
        }
        try {
            //UnicastRemoteObject.exportObject(RMIServer, RMI_PORT);
            Naming.rebind("//localhost/Server", rmiServer);
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
        }
        //playBoard = new PlayBoard();
    }

    public synchronized void addPlayer(ClientInterface client, ServerController controller) {
        //playBoard.addPlayers(client, controller);
    }

    public synchronized void addClient(Socket clientConnection) throws IOException {
       // SocketController client = new SocketController(clientConnection, playBoard);
       // addClient(client, client);
    }

    public PlayBoard getPlayBoard() {
        return playBoard;
    }

    public static void main(String[] args) {
        new Server();
    }
}
