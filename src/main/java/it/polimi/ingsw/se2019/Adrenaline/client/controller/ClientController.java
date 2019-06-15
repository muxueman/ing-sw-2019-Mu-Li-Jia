package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.view.View;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.PlayServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;

import java.rmi.RemoteException;

public class ClientController implements ClientInterface {
    //current view, state
    private View view;
    private ControllerState state;
    private PlayServerInterface playServer;

    protected boolean playing;

    //get value
    protected View getView() {
        return view;
    }
    protected ControllerState getState() {
        return state;
    }


    //send message to the view
    public void sendToView(String message) {
        view.showMessage(message);
    }
    //send clientMessage to a server
    protected void sendToServer(ClientMessage message) {
        try {
            playServer.update(message, this);
        } catch (RemoteException e) {
            reportError("There has been a connection problem!");
            state = state.nextState(true, false);
        }
    }

    public void reportError(String error){
        view.reportError(error);
    }
    public void sendError(String error) throws RemoteException{};

    public void updateStatus(ServerMessage serverMessage) throws RemoteException{};

    public void checkConnection() throws RemoteException{};
}
