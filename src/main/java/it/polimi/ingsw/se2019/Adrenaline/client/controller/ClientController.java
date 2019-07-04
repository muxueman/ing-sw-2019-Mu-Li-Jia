package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.ConnectionState;
import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.client.view.View;
import it.polimi.ingsw.se2019.Adrenaline.network.*;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * The Controller class represent the controller of the game logic.
 *
 * @author Mu xueman
 *
 */
public class ClientController implements ClientInterface, Observer<String> {
    //current view, state
    private View view;
    private Model model;
    private ControllerState state;
    private GameServerInterface gameServer;
    private String playerID;
    private int actionMode;
    protected boolean playing;

    /**
     *
     * The constructor create the ClientController Initializing all its attributes.
     * @param view is the reference to the view.
     *
     */

    public ClientController(View view){
        this.view = view;
        this.gameServer = null;
        model = new Model();
        playerID = null;
        view.register(this);
        model.register(view);
        playing = false;
        actionMode = 0;
        state = new ConnectionState(this).initState();
    }
    /**
     *
     * The getView method is used to get the reference to the View.
     * @return the reference to the view.
     *
     */
    public View getView() {
        return view;
    }
    /**
     *
     * The getModel method is used to get the reference to the Model
     * @return the reference to the model.
     *
     */
    public Model getModel() {
        return model;
    }
    /**
     *
     * The getState method is used to get the current State of the Controller.
     * @return the current state of the Controller.
     *
     */
    protected ControllerState getState() {
        return state;
    }

    /**
     * The getActionMode is used to get the current actionmode of controller
     * @return the current actionmode of the controller
     *
     */
    public int getActionMode(){return actionMode;}

    /**
     *
     * The setGameServer is used to set the interface of the server.
     * @param gameServer is the interface to be set.
     *
     */
    public void setGameServer(GameServerInterface gameServer) {
        if (this.gameServer == null) {
            this.gameServer = gameServer;
        }
    }

    /**
     *
     * The setPlayerID is use to set the String playerID
     * @param playerID
     */
    public void setPlayerID(String playerID){
        this.playerID = playerID;

    }

    /**
     * The getPlayerID is used to send message to the view
     *
     * @return
     */
    public String getPlayerID(){return playerID;}
    public void sendMessage(String message) {
        view.showMessage(message);
    }
    /**
     *
     * The sendToServer method is used to send a ClientMessage to the server.
     * @param message is the message to be sent to the server.
     *
     */
    public void sendToServer(ClientMessage message) {
        try {
            gameServer.update(message, this);
        } catch (RemoteException e) {
            reportError("There has been a connection problem!");
            state = state.nextState(true, false);
        }
    }
    /**
     *
     * The reportError method is used to send the error to the view.
     * @param error is the string representing the error.
     *
     */
    public void reportError(String error){
        view.reportError(error);
    }
    /**
     * The sendError method is used to send and error message.
     * @param error is the string representing the error.
     * @throws RemoteException is the exception thrown by RMI.
     */
    public void sendError(String error) throws RemoteException{};

    /**
     * The isPlaying is use to show the player still playing or not
     *
     * @return
     */
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing){
        this.playing = playing;
    }

    public void nextView(boolean next) {
        view.nextView(next);
    }

    /**
     *
     * The update method is used to call the update of one state of the Controller.
     * @param message is the message to be elaborate.
     *
     */
    @Override
    public synchronized void update(String message) {
        state = state.update(message);
    }

    @Override
    public synchronized void updateStatus(ServerMessage serverMessage) {
        state = state.updateStatus(serverMessage);
        System.out.println(serverMessage.getMessage());
    }
    public void checkConnection() throws RemoteException{};

    /**
     *
     * The connect method is used to establish a connection between client and server.
     * @param selectedConnection is the kind of connection, 1 for RMI, 2 for Socket
     * @param host is the address of the host.
     * @param port is the port of the host.
     * @return true if the connection is established, false if not.
     *
     */

    public boolean connect(int selectedConnection, String host, int port) {
        if (gameServer == null) {
            switch (selectedConnection) {
                case 1: // RMI Connection
                    return connectToRMI(host, port);
                case 2: // Socket Connection, not developed yet
                    return connectToSocket(host, port);
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     *
     * The connectToRmi method is used establish a RMI connection between client and server.
     * @param host is the address of the host.
     * @param port is the port of the host.
     * @return true if the connection is established, false if not.
     *
     */

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
            e.printStackTrace();
            reportError("An RMI related error, try again.");
            return false;
        } catch (Exception e) {
            reportError("An error, try again.");
            return false;
        }
        return true;
    }

    /**
     *
     * The connectToRmi method is used establish a Socket connection between client and server.
     * @param host is the address of the host.
     * @param port is the port of the host.
     * @return true if the connection is established, false if not.
     *
     */

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



}