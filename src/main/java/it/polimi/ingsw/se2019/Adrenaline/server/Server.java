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

        (new SocketServer(this, SOCKET_PORT)).start();
        //创建远程对象实例
        RMIServer rmiServer = new RMIServer(this);

        try {
            //启动RMI注册服务，指定端口为1099
            System.out.println("RMI Registry start.....");
            LocateRegistry.createRegistry(RMI_PORT);
        } catch (RemoteException e) {
            Logger.getGlobal().info("Registry already on!");
        }
        try {
            //把server实例注册到启动了RMI注册服务器的机器上
            System.out.println("RMI Server created...");
            UnicastRemoteObject.exportObject(rmiServer, RMI_PORT);
            Naming.rebind("//localhost/Server", rmiServer);
            System.out.println("RMI Server connected to Registry...");
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
        }
        lobby = new Lobby();
    }

    //addClient for RMI server
    public synchronized void addClient(ClientInterface client, ServerController controller) {
        lobby.addClient(client, controller);
    }

//    public synchronized void addClient(Socket clientConnection) throws IOException {
//         SocketController client = new SocketController(clientConnection, lobby);
//         addClient(client, client);
//    }

    public Lobby getLobby() {
        return lobby;
    }

    //start a server!
    public static void main(String[] args) {
        new Server();
    }
}
