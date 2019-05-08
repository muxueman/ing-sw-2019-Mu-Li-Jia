package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.RMIController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.RemoteException;
//UnicastRemoteObject用于导出的远程对象和获得与该远程对象通信的存根。
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class RMIServer implements ServerInterface {

    private final Server server;

    RMIServer(Server server) {
        this.server = server;
    }
/**
    public ServerInterface addClient(ClientInterface client) {
        RMIController rmiController = new RMIController(server.getPlayBoard(), client);
        try {
            ServerInterface Server = (ServerInterface) UnicastRemoteObject.exportObject(RMIController, 0);
            addClient(client);
            return Server;
        } catch (RemoteException e) {
            Logger.getGlobal().warning(e.getMessage());
            return null;
        }
    }
    private void addClient(ClientInterface client, ServerController controller) {
        server.addClient(client, controller);
    }
 */
}