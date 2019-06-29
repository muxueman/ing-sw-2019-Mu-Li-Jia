package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.RMIServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.RMIController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.Remote;
import java.rmi.RemoteException;
//UnicastRemoteObject用于导出的远程对象和获得与该远程对象通信的存根。
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;


public class RMIServer implements RMIServerInterface {

    private final Server server;

    RMIServer(Server server) {
        this.server = server;
    }

    @Override
    public GameServerInterface addClient(ClientInterface client) {

        //针对每一个用户，创建属于他的RMIController(它继承了gameserverinterface)并把它注册到RMI
        RMIController rmiController = new RMIController(server.getLobby(), client);
        try {
            //if port num = 0, an anonymous port is chosen
            GameServerInterface gameServer = (GameServerInterface) UnicastRemoteObject.exportObject(rmiController, 0);
            addClient(client,rmiController);
            System.out.println("a client added with a RMI controller");
            return gameServer;
        } catch (RemoteException e) {
            e.printStackTrace();
            Logger.getGlobal().warning(e.getMessage());
            return null;
        }
    }

    //通过这个函数把client和server controller 一一对应的信息存在lobby里
    private void addClient(ClientInterface client, ServerController controller) {
        server.addClient(client, controller);
    }
}