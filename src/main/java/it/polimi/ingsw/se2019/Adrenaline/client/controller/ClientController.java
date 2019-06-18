package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.client.view.View;
import it.polimi.ingsw.se2019.Adrenaline.network.*;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.SocketController;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientController implements ClientInterface {
    //current view, state
    private View view;
    private Model model;
    private ControllerState state;
    private GameServerInterface gameServer;

    protected boolean playing;

    //constructor
    public ClientController(View view){
        this.view = view;
        this.gameServer = null;
        playing = false;
        state = new ConnectionState(this).initState();
    }
    //get value
    protected View getView() {
        return view;
    }
    protected Model getModel() {
        return model;
    }
    protected ControllerState getState() {
        return state;
    }

    //set value
    public void setGameServer(GameServerInterface gameServer) {
        if (this.gameServer == null) {
            this.gameServer = gameServer;
        }
    }

    //send message to the view
    public void sendToView(String message) {
        view.showMessage(message);
    }
    //send clientMessage to a server
    protected void sendToServer(ClientMessage message) {
        try {
            gameServer.update(message, this);
        } catch (RemoteException e) {
            reportError("There has been a connection problem!");
            state = state.nextState(true, false);
        }
    }
    public void reportError(String error){
        view.reportError(error);
    }
    public void sendError(String error) throws RemoteException{};
    public void nextView(boolean wpc) {
        view.nextView(wpc);
    }

    public void updateStatus(ServerMessage serverMessage) throws RemoteException{};

    public void checkConnection() throws RemoteException{};

    //connection between client and server, 1 for RMI, 2 for socket
    protected boolean connect(int selectedConnection, String host, int port) {
        if (gameServer == null) {
            switch (selectedConnection) {
                case 1: // RMI Connection
                    return connectToRMI(host, port);
                case 2: // Socket Connection, not developed yet
                    //return connectToSocket(host, port);
                    return false;
                default:
                    return false;
            }
        }
        return false;
    }

    //connect this client to RMIServer
    private boolean connectToRMI(String host, int port) {
        try {
            RMIServerInterface rmiServer = (RMIServerInterface) Naming.lookup("//" + host + ":" + port + "/Server");
            ClientInterface client = (ClientInterface) UnicastRemoteObject.exportObject(this, 0);
            GameServerInterface thisGameServer = rmiServer.addClient(client);
            if (thisGameServer == null) {
                return false;
            }
            setGameServer(thisGameServer);
        } catch (MalformedURLException e) {
            reportError("Wrong host or port, retry!");
            return false;
        } catch (NotBoundException | RemoteException e) {
            reportError("An RMI related error, try again.");
            return false;
        } catch (Exception e) {
            reportError("An error, try again.");
            return false;
        }
        return true;
    }

    /*
    private boolean connectToSocket(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            setGameServer(new SocketController(this, socket));
        } catch (UnknownHostException e) {
            reportError("Wrong host, retry!");
            return false;
        } catch (IOException e) {
            reportError("Network error, please retry.");
            return false;
        }
        return true;
    }
    */

}
