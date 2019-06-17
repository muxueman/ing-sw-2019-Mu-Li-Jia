package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.PlayServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.RMIController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.Remote;
import java.rmi.RemoteException;
//UnicastRemoteObject用于导出的远程对象和获得与该远程对象通信的存根。
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class RMIServer implements ServerInterface {

    private final Server server;

    RMIServer(Server server) {
        this.server = server;
    }

    @Override
    public PlayServerInterface addClient(ClientInterface client) {

        RMIController rmiController = new RMIController(server.getLobby(), client);
        try {
            //if port num = 0, an anonymous port is chosen
            PlayServerInterface playServer = (PlayServerInterface) UnicastRemoteObject.exportObject((Remote) rmiController, 0);
            addClient(client);
            return playServer;
        } catch (RemoteException e) {
            Logger.getGlobal().warning(e.getMessage());
            return null;
        }
    }
    private void addClient(ClientInterface client, ServerController controller) {
        server.addClient(client, controller);
    }
}